package com.joiest.jpf.facade;

import com.joiest.jpf.entity.WeixinMenuInfo;

import java.util.Map;

public interface WeixinMenuServiceFacade {

    /**
     * 获取公众号管理菜单
     * */
    public WeixinMenuInfo selectWeixinMenuByMpid(Integer id);

    /**
     * 添加公众号管理菜单
     * */
    public int addWeixinMenu(Map<String,String> map);


    /**
     * 修改公众号管理菜单
     * */
    public int upWeixinMenuByMpid(Map<String,String> map);
}
