package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderCpsingleMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderMapper;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.dto.OrderCpsingleResponse;
import com.joiest.jpf.dto.UnionPayRefundRequest;
import com.joiest.jpf.entity.OrderCpsingleInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.OrderCpsingleServiceFacade;
import com.joiest.jpf.facade.SystemlogServiceFacade;
import com.mysql.jdbc.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
public class OrderCpsingleServiceFacadeImpl implements OrderCpsingleServiceFacade {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private PayOrderCpsingleMapper payOrderCpsingleMapper;

    @Autowired
    private SystemlogServiceFacade systemlogServiceFacade;

    private static final Logger logger = LogManager.getLogger(OrderCpsingleServiceFacadeImpl.class);

    @Override
    public int getCpsCount(){
        PayOrderCpsingleExample e = new PayOrderCpsingleExample();
        return payOrderCpsingleMapper.countByExample(e);
    }

    @Override
    public OrderCpsingleResponse getCps(OrderCpsingleRequest request, UserInfo userInfo, String IP){
        OrderCpsingleResponse response = new OrderCpsingleResponse();

        PayOrderCpsingleExample e = new PayOrderCpsingleExample();
        PayOrderCpsingleExample.Criteria c = e.createCriteria();
        e.setPageNo( request.getPage() );
        e.setPageSize( request.getRows() );
        if (org.apache.commons.lang3.StringUtils.isNotBlank( request.getTdorderid() ) ){
            c.andTdorderidEqualTo( request.getTdorderid() );
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank( request.getOrderid() )){
            c.andOrderidEqualTo( request.getOrderid() );
        }
        if ( request.getMtsid() != null ){
            c.andMtsidEqualTo(request.getMtsid());
        }
        if ( request.getSingletype() != null ){
            c.andSingletypeEqualTo( request.getSingletype() );
        }
        if ( request.getSinglestatus() != null ){
            c.andSinglestatusEqualTo( request.getSinglestatus() );
        }
        Date addtimeStart = new Date();
        if ( org.apache.commons.lang3.StringUtils.isNotBlank(request.getAddtimeStart()) ){
            addtimeStart = DateUtils.getFdate( request.getAddtimeStart(), DateUtils.DATEFORMATLONG );
            c.andAddtimeGreaterThanOrEqualTo( addtimeStart );
        }
        Date addtimeEnd = new Date();
        if ( org.apache.commons.lang3.StringUtils.isNotBlank( request.getAddtimeEnd() ) ){
            addtimeEnd = DateUtils.getFdate( request.getAddtimeEnd(), DateUtils.DATEFORMATLONG );
            c.andAddtimeLessThanOrEqualTo(addtimeEnd);
        }
        if ( org.apache.commons.lang3.StringUtils.isNotBlank(request.getAddtimeStart()) && org.apache.commons.lang3.StringUtils.isNotBlank( request.getAddtimeEnd() ) ){
            c.andAddtimeBetween(addtimeStart,addtimeEnd);
        }

        List<PayOrderCpsingleExample.Criterion> cList = c.getCriteria();    // 获取查询参数
        StringBuilder sb = new StringBuilder();
        for (PayOrderCpsingleExample.Criterion criterion:cList ){
            sb.append(criterion.getCondition()+criterion.getValue()+',');
        }


        List<PayOrderCpsingle> list = payOrderCpsingleMapper.selectByExample(e);

        // 插入日志记录
//        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_spsingle","查询数据", "SELECT * FROM `pay_order_cpsingle` LIMIT ");

        List<OrderCpsingleInfo> infos = new ArrayList<>();
        for (PayOrderCpsingle payOrderCpsingle:list){
            OrderCpsingleInfo orderCpsingleInfo = new OrderCpsingleInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrderCpsingle.class, OrderCpsingleInfo.class, false);
            beanCopier.copy(payOrderCpsingle, orderCpsingleInfo, null);
            infos.add(orderCpsingleInfo);
        }

        response.setList(infos);
        response.setCount(payOrderCpsingleMapper.countByExample(e));

        // 插入日志记录

        return response;
    }

    @Override
    public int insRecord(OrderCpsingleInfo info){
        PayOrderCpsingle payOrderCpsingle = new PayOrderCpsingle();

        payOrderCpsingle.setTdorderid(info.getTdorderid());
        payOrderCpsingle.setOrderid(info.getOrderid());
        payOrderCpsingle.setTdorderprice(info.getTdorderprice());
        payOrderCpsingle.setMtsid(info.getMtsid());
        payOrderCpsingle.setSingletype(info.getSingletype());
        payOrderCpsingle.setSinglestatus(info.getSinglestatus());
        payOrderCpsingle.setAddtime(new Date());

        int res = payOrderCpsingleMapper.insertSelective(payOrderCpsingle);
        return res;
    }

    @Override
    public JpfResponseDto checkOk(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo, String IP){

        // 查询此单的原operate_content审核内容字段
        PayOrderCpsingle ordinaryRec = payOrderCpsingleMapper.selectByPrimaryKey(orderCpsingleRequest.getId());

        // 插入日志记录
        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_spsingle","查询数据", "SELECT * FROM `pay_order_cpsingle` WHERE id="+orderCpsingleRequest.getId());
        String json = ordinaryRec.getOperateContent();

        // 判断这个单是否已审核
        byte singlestatus = ordinaryRec.getSinglestatus();
        if ( singlestatus != 0 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "此订单已处理，请不要重复处理");
        }

        // 根据请求构建新的json记录
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userInfo.getId());
        map.put("username", userInfo.getUserName());
        map.put("content", "<span style='color:blue'>审核通过</span>");
        map.put("date", new Date());

        JsonUtils jsonUtils = new JsonUtils();
        List<Object> list = new ArrayList<>();
        // 判断原审核内容是否为空
        if ( org.apache.commons.lang3.StringUtils.isBlank(json) ){
            // 建立一个空的list用来存储本次驳回操作的json记录
            list.add(map);
        }else
        {
            // 把原JSON转换成list
            list = jsonUtils.toObject(json, List.class);

            // 把新的json记录添加进原json
            list.add(map);
        }

        // 得到新拼接的JSON数据
        String newJson = jsonUtils.toJson(list);
        PayOrderCpsingle newRec = new PayOrderCpsingle();
        newRec.setId(orderCpsingleRequest.getId());
        newRec.setOperateContent(newJson);
        newRec.setSinglestatus((byte)1);

        payOrderCpsingleMapper.updateByPrimaryKeySelective(newRec);

        // 插入日志记录
        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_cpsingle","更新数据","UPDATE `pay_order_cpsingle` SET operate_content="+newJson+",singlestatus=1 WHERE id="+orderCpsingleRequest.getId());

        // 更新订单表order->singlestatus字段为5：财务已审核，银联退款中
        PayOrderExample payOrderExample = new PayOrderExample();
        PayOrderExample.Criteria payOrderC = payOrderExample.createCriteria();
        payOrderC.andOrderidEqualTo(orderCpsingleRequest.getOrderid());

        PayOrder payOrder = new PayOrder();
        payOrder.setSinglestatus((byte)5);
        payOrderMapper.updateByExampleSelective(payOrder, payOrderExample);

        // 插入日志记录
        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order","更新数据","UPDATE `pay_order` SET singlestatus=5 WHERE orderid="+orderCpsingleRequest.getOrderid());
        // 财务处理完毕，等待银联退款
        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto checkNo(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo, String IP){
        // 查询此单的原operate_content审核内容字段
        PayOrderCpsingle payOrderCpsingle = payOrderCpsingleMapper.selectByPrimaryKey(orderCpsingleRequest.getId());
        String json = payOrderCpsingle.getOperateContent();

        // 插入日志记录
        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_spsingle","查询数据", "SELECT * FROM `pay_order_cpsingle` WHERE id="+orderCpsingleRequest.getId());

        // 判断这个单是否已审核
        byte singlestatus = payOrderCpsingle.getSinglestatus();
        if ( singlestatus != 0 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "此订单已处理，请不要重复处理");
        }

        // 根据请求构建新的json记录
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userInfo.getId());
        map.put("username", userInfo.getUserName());
        map.put("content", "<span style='color:red'>"+orderCpsingleRequest.getOperateContent()+"</span>");
        map.put("date", new Date());

        JsonUtils jsonUtils = new JsonUtils();
        List<Object> list = new ArrayList<>();

        if ( StringUtils.isEmptyOrWhitespaceOnly( json )){
            // 建立一个空的list用来存储本次驳回操作的json记录
            list.add(map);

        }else{
            // 把原JSON转换成list
            list = jsonUtils.toObject(json, List.class);

            // 把新的json记录添加进原json
            list.add(map);
        }

        // 得到新拼接的JSON数据
        String newJson = jsonUtils.toJson(list);
        PayOrderCpsingle rec = new PayOrderCpsingle();
        rec.setId(orderCpsingleRequest.getId());
        rec.setOperateContent(newJson);
        rec.setSinglestatus((byte)2);
        int res = payOrderCpsingleMapper.updateByPrimaryKeySelective(rec);
        if ( res != 1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单表失败，请联系管理员");
        }

        // 插入日志记录
        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_cpsingle","更新数据","UPDATE `pay_order_cpsingle` SET operate_content="+newJson+",singlestatus=2 WHERE id="+orderCpsingleRequest.getId());

        // 更新订单表状态
        PayOrderExample payOrderExample = new PayOrderExample();
        PayOrderExample.Criteria payOrderC = payOrderExample.createCriteria();
        payOrderC.andOrderidEqualTo(orderCpsingleRequest.getOrderid());

        PayOrder payOrder = new PayOrder();
        payOrder.setSinglestatus((byte)6);
        int res_order = payOrderMapper.updateByExampleSelective(payOrder, payOrderExample);
        if ( res_order != 1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新订单表失败，请联系管理员");
        }

        // 插入日志记录
        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order","更新数据","UPDATE `pay_order` SET singlestatus=2 WHERE orderid="+orderCpsingleRequest.getOrderid());

        return new JpfResponseDto();
    }

    @Override
    public Map<String, Object> getPosRequest(String orderid){
        // 查询此订单详细信息
        PayOrderExample orderE = new PayOrderExample();
        PayOrderExample.Criteria orderC = orderE.createCriteria();
        orderC.andOrderidEqualTo(orderid);
        List<PayOrder> orderList = payOrderMapper.selectByExample(orderE);

        Map<String, Object> posRequest = new HashMap<>();
        posRequest.put("orderid",orderid);
        posRequest.put("paytype", orderList.get(0).getPaytype());
        posRequest.put("money", orderList.get(0).getOrderprice());
        posRequest.put("refundmoney", orderList.get(0).getOrderprice());

        return posRequest;
    }

    @Override
    public void unionPayRefund(UnionPayRefundRequest request, String IP){
        logger.info("======================================接收银联返回信息 start=======================================================");
        logger.info(request.toString());
        // 获取该退单原先的记录
        PayOrderCpsingleExample e = new PayOrderCpsingleExample();
        PayOrderCpsingleExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(request.getOrderid());

        List<PayOrderCpsingle> list = payOrderCpsingleMapper.selectByExample(e);

        //插入日志记录，因为没有登录，所以创建一个空的userInfo
        UserInfo userInfo = new UserInfo();
        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_cpsingle","查询数据","SELECT * FROM `pay_order_cpsingle` WHERE orderid="+request.getOrderid());

        if ( list.isEmpty() ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到订单信息");
        }
        PayOrderCpsingle oldRec = list.get(0);
        PayOrderCpsingle newRec = new PayOrderCpsingle();
        String oldJson = oldRec.getRefundContent();
        String newJson = "";
        if (org.apache.commons.lang3.StringUtils.isNotBlank(oldJson) ){
            // 如果原先记录的refundContent字段不为空
            oldJson = org.apache.commons.lang3.StringUtils.stripStart(oldJson,"[");
            oldJson = org.apache.commons.lang3.StringUtils.stripEnd(oldJson,"]");
            newJson = '[' + oldJson+ ',' + request.getRefund_content() + ']';
            newRec.setRefundContent(newJson);
        }else{
            newJson = request.getRefund_content();
            newRec.setRefundContent(newJson);
        }

        // 将银联返回信息中的json更新到此订单的refund_content字段
        payOrderCpsingleMapper.updateByExampleSelective(newRec,e);

        // 插入日志记录
        systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_cpsingle","更新数据","UPDATE `pay_order_cpsingle` SET refund_content="+newJson+" WHERE orderid="+request.getOrderid());

        // 如果返回退款成功
        if ( request.getCode().equals("10000") ){
            // 更新此退单记录singlestatus字段为3：银联退款成功
            PayOrderCpsingle payOrderCpsingle = new PayOrderCpsingle();
            payOrderCpsingle.setSinglestatus((byte)3);
            payOrderCpsingleMapper.updateByExampleSelective(payOrderCpsingle,e);

            // 插入日志记录
            systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_cpsingle","更新数据","UPDATE `pay_order_cpsingle` SET singlestatus=3 WHERE orderid="+request.getOrderid());

            // 更新订单表order->singlestatus字段为5：退款处理完成
            PayOrderExample payOrderExample = new PayOrderExample();
            PayOrderExample.Criteria payOrderC = payOrderExample.createCriteria();
            payOrderC.andOrderidEqualTo(request.getOrderid());

            PayOrder payOrder = new PayOrder();
            payOrder.setSinglestatus((byte)7);
            int res_order = payOrderMapper.updateByExampleSelective(payOrder, payOrderExample);

            // 插入日志记录
            systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order","更新数据","UPDATE `pay_order` SET singlestatus=5 WHERE orderid="+request.getOrderid());

            if ( res_order == 1 ){
                // do nothing.
            }else{
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新订单表失败，请联系管理员");
            }
        }else if ( request.getCode().equals("10008") ){
            // 如果返回退款失败
            // 更新此退单记录singlestatus字段为4：银联退款失败
            PayOrderCpsingle payOrderCpsingle = new PayOrderCpsingle();
            payOrderCpsingle.setSinglestatus((byte)4);
            payOrderCpsingleMapper.updateByExampleSelective(payOrderCpsingle,e);

            // 插入日志记录
            systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order_cpsingle","更新数据","UPDATE `pay_order_cpsingle` SET singlestatus=4 WHERE orderid="+request.getOrderid());

            // 更新订单表order->singlestatus字段为8：银联退款失败
            PayOrderExample payOrderExample = new PayOrderExample();
            PayOrderExample.Criteria payOrderC = payOrderExample.createCriteria();
            payOrderC.andOrderidEqualTo(request.getOrderid());

            PayOrder payOrder = new PayOrder();
            payOrder.setSinglestatus((byte)8);
            int res_order = payOrderMapper.updateByExampleSelective(payOrder, payOrderExample);

            // 插入日志记录
            systemlogServiceFacade.sysLog(1,userInfo,IP,"",32,"pay_order","更新数据","UPDATE `pay_order` SET singlestatus=8 WHERE orderid="+request.getOrderid());

            if ( res_order == 1 ){
                // do nothing.
            }else{
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新订单表失败，请联系管理员");
            }
        }
        logger.info("======================================接收银联返回信息 end=======================================================");
    }
}
