package com.joiest.jpf.facade;

import com.joiest.jpf.dto.AddCloudDfTaskRequest;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.entity.CloudDfOrderInterfaceInfo;

import java.util.List;

public interface CloudDfOrderInterfaceServiceFacade {

    /**
     * 添加打款订单信息
     * @param list
     * @return
     */
    public int addOrder(String batchid, String dfid, List<CloudDfMoneyInterfaceInfo> list, String batchid_self);

    /**
<<<<<<< HEAD
     *根据外来单号查询代付数据  request_orderid
     * **/
    public CloudDfMoneyInterfaceInfo getDfOrderByRequestOrderid(String request_orderid);
=======
     * 获取批次下的代付信息
     * @param batchid 批次号
     * @return
     */
    public List<CloudDfOrderInterfaceInfo> getOrdersList(String batchid);

    /**
     * 更新信息---请求接口之后
     * @param info
     * @return
     */
    public int updateDfOrderStatus(CloudDfOrderInterfaceInfo info);
>>>>>>> 94a18e5ddd51051becac646992cb52f1a059b8d4
}
