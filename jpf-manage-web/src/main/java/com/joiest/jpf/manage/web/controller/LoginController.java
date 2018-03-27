package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.manage.web.interceptor.LoginInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	/**
	 * 跳转到登陆页面
	 * @return
	 */
//	@RequestMapping(value={"/", "/index"})
//	public String showLoginPage() {
//		return "login";
//	}
	@RequestMapping(value={"/", "/index"})
	public ModelAndView showLoginPage() {
		return new ModelAndView("redirect:/backIndex");
	}
	
	@RequestMapping(value={"/logout"})
	public ModelAndView logout(HttpServletRequest request, HttpSession httpSession) {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value={"/backIndex"}, method=RequestMethod.GET)
	public ModelAndView backIndex(HttpServletRequest request, ModelMap modelMap) {
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
		return new ModelAndView("backIndex", modelMap);
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			HttpSession httpSession, ModelMap modelMap) {
//		logger.debug("**********invoke login start ***********************************");
//		String loginName = request.getParameter("user");
//		String password = request.getParameter("pwd");
//
//		UserByLoginNameReq userByLoginNameReq = new UserByLoginNameReq();
//		userByLoginNameReq.setLoginName(loginName);
//		userByLoginNameReq.setPassword(password);
//		userByLoginNameReq.setType(MerchUserType.TOP_MERCH_USER.getValue());
//		UserByLoginNameRep userByLoginNameRep = mgrMerchUserService.getUserByLoginName(userByLoginNameReq);
//		String retCode = userByLoginNameRep.getRetCode();
//		if (retCode.equals(UnipayRetInfo.OPERATION_COMPLETED.getValue())) {
//			UserInfo userInfo = new UserInfo();
//			userInfo.setId(userByLoginNameRep.getId());
//			userInfo.setUserName(userByLoginNameRep.getUserName());
//			userInfo.setLoginName(userByLoginNameRep.getLoginName());
//			userInfo.setPassword(userByLoginNameRep.getPassword());
//			httpSession.setAttribute(Constant.SESSION_OPERATORUSER, userInfo);
			return new ModelAndView("redirect:/backIndex");
//		}
//		modelMap.put("notice", "用户名或密码错误，登录失败！");
//		logger.debug("**********invoke login end ***********************************");
//		return new ModelAndView("common/notice", modelMap);
	}
}
