package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayChargeCompanyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayChargeOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayChargePullOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBargainRechargeOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMoneyStreamMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargePullOrderMapper;
import com.joiest.jpf.dto.MarchingDataRequest;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargePullOrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ChargePullOrderServiceFacadeImpl implements ChargePullOrderServiceFacade {

    @Autowired
    private PayChargePullOrderCustomMapper payChargePullOrderCustomMapper;

    @Autowired
    private PayShopBargainRechargeOrderCustomMapper payShopBargainRechargeOrderCustomMapper;

    @Autowired
    private PayChargeCompanyCustomMapper payChargeCompanyCustomMapper;

    @Autowired
    private PayChargeOrderCustomMapper payChargeOrderCustomMapper;

    @Autowired
    private PayChargeCompanyMoneyStreamMapper payChargeCompanyMoneyStreamMapper;

    private volatile List<PayShopBargainRechargeOrder> matchaingList;

    //用户传入的金额
    private volatile  String incomingAmount="";
    //匹配到的总金额
    private volatile  String totalMoney="";

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String,Object>  savePayOrder(String merchNo, String money, ChargeCompanyInfo companyInfo) throws Exception {
        PayChargePullOrder order  =new PayChargePullOrder();
        String orderNo = "PU"+System.currentTimeMillis()+ ToolUtils.getRandomInt(100000,999999);
        order.setAddtime(new Date());
        order.setOrderNo(orderNo);
        order.setCompanyId(companyInfo.getId());
        order.setMoney(new BigDecimal(money));
        order.setMatchRecordsAmount(0);
        order.setMerchNo(merchNo);
        payChargePullOrderCustomMapper.insertSelective(order);
        //String totalMoney ="0";
        //int startNum = 0;
        //int endNum = 500;
        // String lastMoney = "0";
        //做匹配数据操作,并且保存数据库，返回匹配的数据和匹配的金额
        //totalMoney = recursionSub(merchNo, money, companyInfo, orderNo, totalMoney,startNum,endNum,lastMoney);

        matchingDataCountDownLatch(merchNo,money,companyInfo,orderNo);
        //批量更新数据
        payShopBargainRechargeOrderCustomMapper.batchUpdatePayShopBro(matchaingList);
        //更改定单的匹配金额和匹配的条数
        order.setMatchMoney(new BigDecimal(totalMoney));
        order.setMatchRecordsAmount(matchaingList.size());
        payChargePullOrderCustomMapper.updateByPrimaryKeySelective(order);

        //更新用户的余额
        PayChargeCompany chargeCompany = payChargeCompanyCustomMapper.selectByPrimaryKey(companyInfo.getId());
        chargeCompany.setMoney(ArithmeticUtils.sub(chargeCompany.getMoney().toString(),totalMoney));
        chargeCompany.setMoneyCode(Md5Encrypt.md5(chargeCompany.getId()+chargeCompany.getMoney()+ ConfigUtil.getValue("MERCH_VALIDE_CODE")));
        chargeCompany.setUpdatetime(new Date());
        payChargeCompanyCustomMapper.updateByPrimaryKeySelective(chargeCompany);
        //返回值
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("data",order);
        map.put("matchData",matchaingList);
        return map;
    }


    /**
     * 递归匹配记录
     * @param merchNo 商户号
     * @param money 商户输入金额
     * @param companyInfo 商户信息
     * @param orderNo 商户下单单号
     */
    private void matchingDataCountDownLatch(String merchNo, final String money,  ChargeCompanyInfo companyInfo, String orderNo) throws Exception{
        int threadNum=Integer.valueOf(ArithmeticUtils.div(money,"10000",0));
        if(threadNum<=0){
            threadNum=1;
        }
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        matchaingList=new ArrayList<>();
        totalMoney="0";
        incomingAmount=money;
        ExecutorService exec = Executors.newCachedThreadPool();
        //开启多个子任务，每完成一个任务，调用cdl.countDown()将计数减一，当计数变为0时，被阻塞的主任务将得到执行
        //在它的技术变为0之前，cdl.await()方法会导致当前线程一直阻塞
        System.out.println("需要等待" + cdl.getCount() + "个子任务完成！");

        for (int i = 1; i <=threadNum; i++) {
            final int count = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    Map<String,Object> param = new HashMap<String,Object>();
                    param.put("startNum", 200*(count-1));
                    param.put("endNum",200);
                    List<PayShopBargainRechargeOrder> orderList = payShopBargainRechargeOrderCustomMapper.getOrderListByNum(param);
                    for (PayShopBargainRechargeOrder payShopBargainRechargeOrder:orderList) {
                        //如果客户的剩余额小于10元了，或者剩余的钱为0了不查了
                        if(new BigDecimal(incomingAmount).compareTo(new BigDecimal(0))<0||new BigDecimal(incomingAmount).compareTo(new BigDecimal(0))==0){
                            break;
                        }
                        //如果记录的金额小于订单的金额，表示不够
                        if(payShopBargainRechargeOrder.getAmount().compareTo(new BigDecimal(incomingAmount))<0){
                            //扣除金额
                            incomingAmount = ArithmeticUtils.sub(incomingAmount,payShopBargainRechargeOrder.getAmount().toString()).toString();
                            //匹配的金额数相加
                            totalMoney = ArithmeticUtils.add(totalMoney,payShopBargainRechargeOrder.getAmount().toString()).toString();
                            setPayShopBro(merchNo,companyInfo,orderNo,payShopBargainRechargeOrder);
                            continue;
                        }else if(payShopBargainRechargeOrder.getAmount().compareTo(new BigDecimal(money))==0){
                            //扣除金额
                            incomingAmount = ArithmeticUtils.sub(incomingAmount,payShopBargainRechargeOrder.getAmount().toString()).toString();
                            //匹配的金额数相加
                            totalMoney = ArithmeticUtils.add(totalMoney,payShopBargainRechargeOrder.getAmount().toString()).toString();
                            //设置数据
                            setPayShopBro(merchNo,companyInfo,orderNo,payShopBargainRechargeOrder);
                            break;
                        }else {
                            //表示本次记录的值已经大于客户的剩余的值，此记录放过
                            continue;
                        }
                    }
                    System.out.println("第" + count + "个任务已经完成");
                    cdl.countDown();
                }
            });
        }
        exec.shutdown();
        cdl.await();
        System.out.println("主线程允许执行，任务完成！");

    }
   /* private String recursionSub(String merchNo, String money,  ChargeCompanyInfo companyInfo, String orderNo, String totalMoney,int startNum,int endNum,String lastMoney) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("startNum",startNum);
        param.put("endNum",endNum);
        List<PayShopBargainRechargeOrder> orderList = payShopBargainRechargeOrderCustomMapper.getOrderListByNum(param);
        for (PayShopBargainRechargeOrder payShopBargainRechargeOrder:orderList) {
            //如果客户的剩余额小于10元了，或者剩余的钱为0了不查了
            if(new BigDecimal(money).compareTo(new BigDecimal(0))<0||new BigDecimal(money).compareTo(new BigDecimal(0))==0){
                break;
            }
            //如果记录的金额小于订单的金额，表示不够
            if(payShopBargainRechargeOrder.getAmount().compareTo(new BigDecimal(money))<0){
                //扣除金额
                money = ArithmeticUtils.sub(money,payShopBargainRechargeOrder.getAmount().toString()).toString();
                //匹配的金额数相加
                totalMoney = ArithmeticUtils.add(totalMoney,payShopBargainRechargeOrder.getAmount().toString()).toString();
                setPayShopBro(merchNo,companyInfo,orderNo,payShopBargainRechargeOrder);
                 continue;
            }else if(payShopBargainRechargeOrder.getAmount().compareTo(new BigDecimal(money))==0){
                //扣除金额
                money = ArithmeticUtils.sub(money,payShopBargainRechargeOrder.getAmount().toString()).toString();
                //匹配的金额数相加
                totalMoney = ArithmeticUtils.add(totalMoney,payShopBargainRechargeOrder.getAmount().toString()).toString();
                //设置数据
                setPayShopBro(merchNo,companyInfo,orderNo,payShopBargainRechargeOrder);
                break;
            }else {
                //表示本次记录的值已经大于客户的剩余的值，此记录放过
                continue;
            }
        }
        //证明客户剩余的钱还大于10.必须要重新查
         if(!lastMoney.equals(money)){
             lastMoney =money;
             recursionSub(merchNo, money,companyInfo, orderNo, totalMoney,endNum,endNum,lastMoney);
         }else{
             //批量更新数据
             payShopBargainRechargeOrderCustomMapper.batchUpdatePayShopBro(matchaingList);
         }

        return totalMoney;
    }*/

    /**
     * 根据金额匹配数据库的记录，并且对敬恒的记录进行更新，生成订单，生成交易流水
     * @param merchNo   商户号
     * @param companyInfo  商户
     * @param orderNo 商户下的订单号
     * @param payShopBargainRechargeOrder  敬恒的记录
     */
    private void setPayShopBro(String merchNo, ChargeCompanyInfo companyInfo, String orderNo, PayShopBargainRechargeOrder payShopBargainRechargeOrder) {
        // 标记该条记录已经被使用，并且保存订单号
        payShopBargainRechargeOrder.setPullCompanyId(companyInfo.getId());
        payShopBargainRechargeOrder.setPullMerchNo(merchNo);
        payShopBargainRechargeOrder.setPullOrderNo(orderNo);
        payShopBargainRechargeOrder.setUpdatetime(new Date());
        matchaingList.add(payShopBargainRechargeOrder);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void matchingDataByThread(MarchingDataRequest marchingDataRequest, HttpServletResponse httpResponse) {
        System.out.println("线程调用进入");
        String companyName=marchingDataRequest.getCompanyName();
        BigDecimal companyMoney  =marchingDataRequest.getMoney();
        //之前匹配的数据
        List<PayShopBargainRechargeOrder> dataList = marchingDataRequest.getList();
        for (PayShopBargainRechargeOrder payShopBargainRechargeOrder: dataList) {
           //生成订单号
            PayChargeOrder order = new PayChargeOrder();
            String orderId = savePayChargeOrder(companyName, payShopBargainRechargeOrder, order);
            order.setId(orderId);
            //给每条记录保存流水
            savePayChargeMoneyStream(order,companyMoney);
        }
    }

    /**
     * 新生成系统的订单
     * @param companyName  商户
     * @param payShopBargainRechargeOrder  敬恒的订单流水
     * @param order 新生成的订单
     */
    private String savePayChargeOrder(String companyName,PayShopBargainRechargeOrder payShopBargainRechargeOrder, PayChargeOrder order) {
        String payChargeOrderNo="CH"+ ToolUtils.getRandomInt(100,999)+System.currentTimeMillis()+ToolUtils.getRandomInt(100,999);
        order.setOrderNo(payChargeOrderNo);
        order.setAddtime(new Date());
        order.setChargePhone(payShopBargainRechargeOrder.getChargeNo());
        order.setCompanyId(payShopBargainRechargeOrder.getPullCompanyId());
        order.setCompanyName(companyName);
        order.setForeignOrderNo(payShopBargainRechargeOrder.getForeignOrderNo());
        order.setInterfaceType((byte)2);
        order.setMerchNo(payShopBargainRechargeOrder.getPullMerchNo());
        order.setNotifyUrl("");
        order.setProductAmount(payShopBargainRechargeOrder.getAmt());
        order.setProductId("0");
        order.setProductName(payShopBargainRechargeOrder.getItemName());
        order.setProductPrice(payShopBargainRechargeOrder.getPrice());
        if(payShopBargainRechargeOrder.getOrderType()==3){
            order.setProductType(1);
        }else if(payShopBargainRechargeOrder.getOrderType()==1){
            order.setProductType(2);
        }else{
            order.setProductType(3);
        }
        order.setProductValue(payShopBargainRechargeOrder.getFacePrice());
        order.setStatus((byte)0);
        order.setTotalMoney(payShopBargainRechargeOrder.getAmount());
        payChargeOrderCustomMapper.insertSelective(order);
        return order.getId();
    }


    /**
     * 保存交易的流水
     * @param order  新生成的系统订单
     */
    private void savePayChargeMoneyStream(PayChargeOrder order,BigDecimal companyMoney) {
        PayChargeCompanyMoneyStream payChargeCompanyMoneyStream = new PayChargeCompanyMoneyStream();
        String moneyStramNo = "MS"+System.currentTimeMillis()+ ToolUtils.getRandomInt(100,999);
        payChargeCompanyMoneyStream.setMerchNo(order.getMerchNo()); //商户号
        payChargeCompanyMoneyStream.setStreamNo(moneyStramNo);//流水号
        payChargeCompanyMoneyStream.setCompanyId(order.getCompanyId());
        payChargeCompanyMoneyStream.setCompanyName(order.getCompanyName());
        payChargeCompanyMoneyStream.setAddtime(new Date());
        payChargeCompanyMoneyStream.setOrderId(order.getId());
        payChargeCompanyMoneyStream.setOrderNo(order.getOrderNo());
        payChargeCompanyMoneyStream.setInterfaceType((byte)2);
        payChargeCompanyMoneyStream.setIsDel((byte)0);
        payChargeCompanyMoneyStream.setNewMoney(ArithmeticUtils.sub(companyMoney.toString(),order.getTotalMoney().toString()));
        payChargeCompanyMoneyStream.setProductAmount(order.getProductAmount());
        payChargeCompanyMoneyStream.setProductName(order.getProductName());
        payChargeCompanyMoneyStream.setProductSalePrice(order.getProductPrice());
        payChargeCompanyMoneyStream.setProductValue(order.getProductValue());
        payChargeCompanyMoneyStream.setStatus((byte)1);
        payChargeCompanyMoneyStream.setStreamType((byte)1);
        payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());

        payChargeCompanyMoneyStreamMapper.insertSelective(payChargeCompanyMoneyStream);
    }



    public static void main(String[] args) {
       String str = Md5Encrypt.md5("merchNo=MC1541126548324168863&money=50000.00imyHcZOzMmhukCqB").toUpperCase();
        System.out.println(str);
        String newCode = Md5Encrypt.md5("11" + "100000.00" + "Pwztib3qtekopERJ","UTF-8");
        System.out.println(newCode);
        String money = "100.00";
        String i=ArithmeticUtils.div(money,"5000",0);
        System.out.println(i);
    }
}
