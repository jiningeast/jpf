package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudIdcard;
import com.joiest.jpf.common.po.PayWeixinMenu;
import com.joiest.jpf.common.po.PayWeixinMenuExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayWeixinMenuMapper;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.entity.WeixinMenuInfo;
import com.joiest.jpf.facade.WeixinMenuServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeixinMenuServiceFacadeImpl implements WeixinMenuServiceFacade {

    @Autowired
    private PayWeixinMenuMapper payWeixinMenuMapper;


    /**
     * 获取公众号管理菜单
     * */
    public WeixinMenuInfo selectWeixinMenuByMpid(Integer id){

        PayWeixinMenuExample example = new PayWeixinMenuExample();
        PayWeixinMenuExample.Criteria c = example.createCriteria();
        c.andMpidEqualTo(id);

        List<PayWeixinMenu> getPayWeixinMenu = payWeixinMenuMapper.selectByExample(example);

        if(getPayWeixinMenu == null || getPayWeixinMenu.isEmpty()){

            return null;
        }
        PayWeixinMenu payWeixinMenu = getPayWeixinMenu.get(0);

        WeixinMenuInfo weixinMenuInfo = new WeixinMenuInfo();

        BeanCopier beanCopier = BeanCopier.create( PayWeixinMenu.class, WeixinMenuInfo.class, false);
        beanCopier.copy(payWeixinMenu, weixinMenuInfo, null);

        return weixinMenuInfo;
    }

    /**
     * 添加公众号管理菜单
     * */
    public int addWeixinMenu(Map<String,String> map){

        PayWeixinMenu payWeixinMenu = new PayWeixinMenu();

        payWeixinMenu.setMpid(new Integer(map.get("mpid")));
        payWeixinMenu.setMenu(map.get("menu"));
        payWeixinMenu.setOpid(new Integer(map.get("opid")));
        payWeixinMenu.setCreated(new Date());

        return payWeixinMenuMapper.insertSelective(payWeixinMenu);
    }
    /**
     * 修改公众号管理菜单
     * */
    public int upWeixinMenuByMpid(Map<String,String> map){

        PayWeixinMenu payWeixinMenu = new PayWeixinMenu();

        payWeixinMenu.setId(new Integer(map.get("id")));
        payWeixinMenu.setMenu(map.get("menu"));
        payWeixinMenu.setOpid(new Integer(map.get("opid")));
        payWeixinMenu.setUpdated(new Date());

        return payWeixinMenuMapper.updateByPrimaryKeySelective(payWeixinMenu);
    }
}
