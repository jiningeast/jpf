package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.facade.UserServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	private UserServiceFacade userServiceFacade;
	
	/**
	 * 跳转到登陆页面
	 * @return
	 */
//	@RequestMapping(value={"/", "/index"})
//	public String showLoginPage() {
//		return "login";
//	}

    @RequestMapping(value={"/", "/index"})
    public String showLoginPage() {
        return "backIndex";
    }
	
	@RequestMapping(value={"/logout"})
	public ModelAndView logout(HttpServletRequest request, HttpSession httpSession) {
        httpSession.removeAttribute(ManageConstants.USERINFO_SESSION);
		return new ModelAndView("index");
	}
	
//	@RequestMapping(value={"/backIndex"}, method=RequestMethod.GET)
//	public ModelAndView backIndex(HttpServletRequest request, ModelMap modelMap) {
//		JSONObject obj = new JSONObject();
//
//		JSONArray basic = new JSONArray();
//
//		JSONObject menuObj1 = new JSONObject();
//		menuObj1.put("menuid", "1");
//		menuObj1.put("icon", "icon-sys");
//		menuObj1.put("menuname", "商户管理");
//		JSONArray menuObj1Menus = new JSONArray();
//		JSONObject menuObj1Menu = new JSONObject();
//		menuObj1Menu.put("menuid", "2");
//		menuObj1Menu.put("icon", "icon-sys");
//		menuObj1Menu.put("menuname", "查询商户");
//		menuObj1Menu.put("url", "memberPublic/toMemberPublicQuery");
//		menuObj1Menus.add(menuObj1Menu);
//		menuObj1.put("menus", menuObj1Menus);
//		basic.add(menuObj1);
//		obj.put("basic", basic);
//
//		modelMap.put("menu", obj.toJSONString());
//		return new ModelAndView("backIndex", modelMap);
//	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			HttpSession httpSession, ModelMap modelMap) {
		String loginName = request.getParameter("user");
		String password = request.getParameter("pwd");
        LoginVerifyResponse loginVerifyResponse = userServiceFacade.loginVerify(loginName, password);
        if(loginVerifyResponse==null||!loginVerifyResponse.getRetCode().equals("0000")){
            modelMap.put("notice", "用户名或密码错误！");
            return new ModelAndView("common/notice", modelMap);
        }

        httpSession.setAttribute(ManageConstants.USERINFO_SESSION,loginVerifyResponse.getUserInfo());
        return new ModelAndView("backIndex");
	}
}
