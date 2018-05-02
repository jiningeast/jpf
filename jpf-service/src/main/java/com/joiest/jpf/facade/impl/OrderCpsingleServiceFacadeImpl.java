package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderCpsingleMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PaySystemlogMapper;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.dto.OrderCpsingleResponse;
import com.joiest.jpf.entity.OrderCpsingleInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.OrderCpsingleServiceFacade;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;

public class OrderCpsingleServiceFacadeImpl implements OrderCpsingleServiceFacade {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private PayOrderCpsingleMapper payOrderCpsingleMapper;

    @Autowired
    private PaySystemlogMapper paySystemlogMapper;

    @Override
    public int getCpsCount(){
        PayOrderCpsingleExample e = new PayOrderCpsingleExample();
        return payOrderCpsingleMapper.countByExample(e);
    }

    @Override
    public OrderCpsingleResponse getCps(OrderCpsingleRequest request){
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
        List<PayOrderCpsingle> list = payOrderCpsingleMapper.selectByExample(e);
        List<OrderCpsingleInfo> infos = new ArrayList<>();
        for (PayOrderCpsingle payOrderCpsingle:list){
            OrderCpsingleInfo orderCpsingleInfo = new OrderCpsingleInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrderCpsingle.class, OrderCpsingleInfo.class, false);
            beanCopier.copy(payOrderCpsingle, orderCpsingleInfo, null);
            infos.add(orderCpsingleInfo);
        }

        response.setList(infos);
        response.setCount(payOrderCpsingleMapper.countByExample(e));
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
    public JpfResponseDto checkOk(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo){

        // 查询此单的原operate_content审核内容字段
        PayOrderCpsingle ordinaryRec = payOrderCpsingleMapper.selectByPrimaryKey(orderCpsingleRequest.getId());
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

        int res_orderCpsingle = payOrderCpsingleMapper.updateByPrimaryKeySelective(newRec);
        if ( res_orderCpsingle == 1 ){
            // 更新订单表order->singlestatus字段为5：退款处理完成
            PayOrderExample payOrderExample = new PayOrderExample();
            PayOrderExample.Criteria payOrderC = payOrderExample.createCriteria();
            payOrderC.andOrderidEqualTo(orderCpsingleRequest.getOrderid());

            PayOrder payOrder = new PayOrder();
            payOrder.setSinglestatus((byte)5);
            int res_order = payOrderMapper.updateByExampleSelective(payOrder, payOrderExample);
            if ( res_order == 1 ){
                return new JpfResponseDto();
            }else{
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新订单表失败，请联系管理员");
            }
        }else{
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单记录表失败，请联系管理员");
        }
    }

    @Override
    public JpfResponseDto checkNo(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo){
        // 查询此单的原operate_content审核内容字段
        PayOrderCpsingle payOrderCpsingle = payOrderCpsingleMapper.selectByPrimaryKey(orderCpsingleRequest.getId());
        String json = payOrderCpsingle.getOperateContent();

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
    public void sysLog(Integer logtype, UserInfo userInfo, String ip, String ip1, Integer clients, String tablename, String action, String content){

        PaySystemlog paySystemlog = new PaySystemlog();
        paySystemlog.setLogtype(logtype);
        paySystemlog.setOperatorUid(userInfo.getId());
        paySystemlog.setOperatorName(userInfo.getUserName());
        paySystemlog.setIp(ip);
        paySystemlog.setIp1(ip1);
        paySystemlog.setClients(clients);
        paySystemlog.setTablename(tablename);
        String recordStr = Integer.toString(userInfo.getId());
        paySystemlog.setRecord(recordStr);
        paySystemlog.setAction(action);
        paySystemlog.setContent(content);
        Date date = new Date();
        paySystemlog.setCreated(date);

        paySystemlogMapper.insertSelective(paySystemlog);
    }
}
