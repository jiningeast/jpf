package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.GetWeixinMpRequest;
import com.joiest.jpf.dto.GetWeixinMpResponse;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.entity.WeixinMenuInfo;
import com.joiest.jpf.entity.WeixinMpInfo;
import com.joiest.jpf.facade.WeixinMenuServiceFacade;
import com.joiest.jpf.facade.WeixinMpServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理公司管理
 */
@Controller
@RequestMapping("/weixinMp")
public class WeixinMpController {

    @Autowired
    private WeixinMpServiceFacade weixinMpServiceFacade;

    @Autowired
    private WeixinMenuServiceFacade weixinMenuServiceFacade;

    @RequestMapping("/index")
    public String index() {

        return "weixinMp/mplist";

    }

    /**
     * 公众号列表
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(GetWeixinMpRequest request) {

        GetWeixinMpResponse response = weixinMpServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 添加公众号信息页面
     */
    @RequestMapping("/addpage")
    public ModelAndView addView() {
        return new ModelAndView("weixinMp/weixinAdd");
    }

    /**
     * 添加公众号信息
     */
    @RequestMapping("/addsub")
    @ResponseBody
    public JpfResponseDto addsub(@Validated GetWeixinMpRequest request){

        return weixinMpServiceFacade.addsub(request);

    }

    /**
     * 修改公众号信息页面
     */
    @RequestMapping("/editpage")
    public ModelAndView editView(String id, ModelMap modelMap) {

        WeixinMpInfo weixinMpInfo =weixinMpServiceFacade.getOne(id);
        modelMap.addAttribute("weixinMpInfo",weixinMpInfo);
        return new ModelAndView("weixinMp/weixinEdit",modelMap);
    }
    /**
     * 修改公众号信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public JpfResponseDto edit(@Validated GetWeixinMpRequest request){

        return weixinMpServiceFacade.edit(request);

    }
    /**
     * 自定义菜单管理
     * */
    @RequestMapping("/customMenu")
    @ResponseBody
    public ModelAndView customMenu(String id,ModelMap modelMap) {

        //return "1";
        WeixinMpInfo weixinMpInfo =weixinMpServiceFacade.getOne(id);

        WeixinMenuInfo weixinMenuInfo = weixinMenuServiceFacade.selectWeixinMenuByMpid(new Integer(id));
        JSONObject wxMenu = null;
        if(weixinMenuInfo != null && !weixinMenuInfo.getMenu().isEmpty()){

            wxMenu = JSONObject.fromObject(weixinMenuInfo.getMenu());
        }
        modelMap.addAttribute("weixinMpInfo",weixinMpInfo);
        modelMap.addAttribute("weixinMenuInfo",wxMenu);
        return new ModelAndView("weixinMp/customMenu",modelMap);
    }
    /**
     * 自定义菜单编辑 or 添加
     * */
    @RequestMapping("/customMenuOp")
    @ResponseBody
    public String customMenuOp(HttpServletRequest request,HttpSession httpSession) {

        //获取登录帐号
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        int uid = userInfo.getId();

        JSONObject menue = JSONObject.fromObject(request.getParameter("menu"));
        String id = request.getParameter("id");
        Map<String, Object> map = new HashMap<>();

        WeixinMpInfo weixinMpInfo = weixinMpServiceFacade.getOne(id);

        String access_token = weixinMpServiceFacade.getAccessToken(weixinMpInfo);

        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
        JSONObject res = JSONObject.fromObject(OkHttpUtils.postJson(url,menue.get("menu").toString()));

        Map<String,String> mapMenu = new HashMap<>();
        mapMenu.put("menu",menue.toString());
        mapMenu.put("opid",userInfo.getId().toString());

        WeixinMenuInfo getWxMenu = weixinMenuServiceFacade.selectWeixinMenuByMpid(new Integer(id));
        if(getWxMenu == null){

            mapMenu.put("mpid",id);
            //菜单信息入库
            int wxMenu = weixinMenuServiceFacade.addWeixinMenu(mapMenu);

        }else{

            mapMenu.put("id",getWxMenu.getId().toString());
            int wxMenu = weixinMenuServiceFacade.upWeixinMenuByMpid(mapMenu);
        }
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求类型：微信公众号创建自定义菜单");
        sbf.append("\n请求地址："+url);
        sbf.append("\n操作人信息："+userInfo.getUserName()+"【"+userInfo.getId()+"】");
        sbf.append("\n公众号标识："+weixinMpInfo.getName()+"【"+weixinMpInfo.getId()+"】");
        sbf.append("\n获取接口参数："+res);
        String fileName = "WeixinCreateMenuLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);


        JSONObject resu = new JSONObject();
        if(res.get("errcode").toString().equals("0")){

            resu.put("code", JpfInterfaceErrorInfo.SUCCESS.getCode());
            resu.put("info","发布成功");
        }else{
            resu.put("code", JpfInterfaceErrorInfo.FAIL.getCode());
            resu.put("info",res.get("errmsg"));
        }
        return resu.toString();
    }

}
