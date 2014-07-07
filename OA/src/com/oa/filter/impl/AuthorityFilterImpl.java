package com.oa.filter.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.oa.entity.Loginlog;
import com.oa.entity.Operatelog;
import com.oa.entity.Sysfun;
import com.oa.entity.Userinfo;
import com.oa.filter.AuthorityFilter;
import com.oa.service.ILogService;
import com.oa.service.IOperateService;
import com.oa.service.ISysfunService;
import com.oa.service.IUserService;
import com.oa.util.SessionManager;

public class AuthorityFilterImpl implements AuthorityFilter {
	private ISysfunService sysfunService;
	private ILogService logService;
	private IOperateService operateService;
	private IUserService userService;

	/**
	 * 重定向的URL
	 */
	private String redirectURl = null;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest r, ServletResponse re,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) r;
		HttpServletResponse resp = (HttpServletResponse) re;

		HttpSession session = req.getSession();

		// 此二句为解决flash player丢失session
		String jsessionid = req.getParameter("jsessionid");
		if (jsessionid != null) {
			HttpSession oldSession = SessionManager.getInstance().getSession(
					jsessionid);
			if (oldSession != null)
				session.setAttribute("user", oldSession.getAttribute("user"));
		}

		String project_name = req.getContextPath(); // 工程名

		Userinfo userinfo = (Userinfo) session.getAttribute("user");// session中对象

		String home_page_name = project_name + "/pages/main.jsp";// 主页

		redirectURl = project_name + "/pages/login.jsp";// 重定向的地址

		String login_action = project_name + "/user/ajaxLogin"; // 登录的ACTION
		String logout_action = project_name + "/user/logout";

		String uri = req.getRequestURI();// 请求的URI

		String remoteHost = req.getRemoteHost();// 客户机地址

		String pageUri = "";// 比对请求页面的条件
		String actionUri = "";// 比对请求Action条件

		// 初始化当前用户所有 功能对象的集合
		List<Sysfun> sysfuns = getSysfuns(userinfo);

		// 截取 当前请求的一部分用来比比对是否有权限
		pageUri = subPageUri(uri);
		actionUri = subAction(uri);

		// 取得是否有权限
		boolean isPage = this.checkReqPage(pageUri, sysfuns);
		boolean isAction = this.checkReAction(actionUri, sysfuns, userinfo);

		// 写入登录日志
		if (login_action.equals(uri)) {
			this.saveLoginlog(req, resp);
		}

		// 改变跳转的页面
		if (login_action.equals(uri) || redirectURl.equals(uri)
				|| home_page_name.equals(uri)
				|| (project_name + "/").equals(uri)) {
			redirectURl = project_name + "/pages/login.jsp";
		} else {
			if (userinfo == null) {
				redirectURl = project_name + "/noSession.jsp";
			} else if (!(isPage || isAction)) {
				redirectURl = project_name + "/noSysfun.jsp";
			}
		}

		// 登录页面 和 登录请求
		// 第一个条件 登录action请求
		// 第二个条件 登录页面请求
		// 第三个条件 main页面
		// 第四个条件 退出系统
		if (login_action.equals(uri) || redirectURl.equals(uri)
				|| home_page_name.equals(uri) || logout_action.equals(uri)) {
			System.out.println("第一个 　IF　放行。。。。。。。");
			filterChain.doFilter(req, resp);
		}
		// 第一个条件 请求相关页面
		// 第二个条件 请求相关action
		// 第三个条件 当前会话中 用户是否存在
		// 写入操作日志
		else if ((isPage || isAction) && (userinfo != null)) {
			System.out.println("第二个　IF　放行。。。。。。。");
			filterChain.doFilter(req, resp);
		}
		// 返回登录页面
		else {
			System.out.println("第三个　IF　转发。。。。。。");
			if (userinfo != null) {
				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());
				Operatelog operatelog = new Operatelog();
				operatelog.setUserinfo(userinfo);
				operatelog.setOperateDesc("用户请求路径" + actionUri);
				operatelog.setOperateTime(timestamp);
				operatelog.setObjectId(userinfo.getUserName());
				operatelog.setOperateName("非法操作");
				operateService.saveLog(operatelog);
			}
			resp.sendRedirect(redirectURl); // 返回登录页面
		}

	}

	/**
	 * @param req
	 * @param resp
	 */
	private void saveLoginlog(HttpServletRequest req, HttpServletResponse resp) {
		String userid = req.getParameter("userinfo.userId");
		String pwd = req.getParameter("userinfo.passWord");
		Userinfo userinfo = (Userinfo) userService.getOtherObjById(
				Userinfo.class, userid);
		if (userinfo != null) {
			Loginlog loginlog = new Loginlog();
			loginlog.setUserinfo(userinfo);
			loginlog.setLoginDesc(userinfo.getUserName() + "登录");
			loginlog.setIfSuccess((userinfo.getPassWord().equals(pwd) ? 1 : 0));
			loginlog.setLoginUserIp(req.getRemoteHost());
			loginlog.setLoginTime(new Timestamp(new Date().getTime()));
			logService.save(loginlog);
		}
	}

	/**
	 * 是不有访问Action权限 && 在这个方法里同时写入操作日志
	 * 
	 * @return
	 */
	private boolean checkReAction(String actionUri, List<Sysfun> sysfuns,
			Userinfo userinfo) {
		boolean isOk = false;
		if (!actionUri.isEmpty() && actionUri != null && sysfuns != null
				&& !sysfuns.isEmpty()) {

			for (Sysfun sysfun : sysfuns) {
				String sysfunAttr = sysfun.getNodeUrl();
				if (sysfunAttr.equals(actionUri)) {
					isOk = true;
					try {
						//写入操作日志
						if (userinfo != null) {
							Date date = new Date();
							Timestamp timestamp = new Timestamp(date.getTime());
							Operatelog operatelog = new Operatelog();
							operatelog.setUserinfo(userinfo);
							operatelog.setOperateDesc("用户请求"
									+ sysfun.getDisplayName());
							operatelog.setOperateTime(timestamp);
							operatelog.setObjectId(userinfo.getUserName());
							operatelog.setOperateName("操作成功");
							operateService.saveLog(operatelog);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		return isOk;
	}

	/**
	 * 截取 当前请求的一部分用来比比对是否有action权限
	 * 
	 * @param uri
	 * @return
	 */
	private String subAction(String uri) {
		String actionUri = "";
		if (!uri.isEmpty()) {
			// 截取 当前请求的一部分用来比比对是否有权限
			String[] temps = uri.split("/");
			if (temps.length >= 3) {
				actionUri = temps[2];
			}
		}
		return actionUri;
	}

	/**
	 * 检查是不有页面的权限
	 * 
	 * @param pageUri
	 * @param sysfuns
	 * @return
	 */
	private boolean checkReqPage(String pageUri, List<Sysfun> sysfuns) {
		boolean isOk = false;

		if (!pageUri.isEmpty() && pageUri != null && sysfuns != null
				&& !sysfuns.isEmpty()) {

			for (Sysfun sysfun : sysfuns) {
				String sysfunAttr = sysfun.getAttributes();
				if (sysfunAttr.equals(pageUri)) {
					isOk = true;
					break;
				}
			}
		}
		return isOk;
	}

	/**
	 * 截取 当前请求的一部分用来比比对是否有请求页面权限
	 * 
	 * @param uri
	 * @return
	 */
	private String subPageUri(String uri) {
		String pageUri = "";
		if (!uri.isEmpty()) {
			// 截取 当前请求的一部分用来比比对是否有权限
			String pages = "pages";
			int idx = uri.indexOf(pages);

			if (idx != -1) {
				idx = idx + pages.length() + 1;
				pageUri = uri.substring(idx);
				System.out.println(pageUri);
			}
		}
		return pageUri;
	}

	/**
	 * 初始化当前用户所有 功能对象的集合
	 * 
	 * @param userinfo
	 * @return
	 */
	private List<Sysfun> getSysfuns(Userinfo userinfo) {
		List<Sysfun> sysfuns = null;
		if (userinfo != null) {
			// 初始化当前用户所有 功能对象的集合
			sysfuns = sysfunService.getAll(userinfo);
		}
		return sysfuns;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		sysfunService = (ISysfunService) ctx.getBean("sysfunService");
		logService = (ILogService) ctx.getBean("logService");
		operateService = (IOperateService) ctx.getBean("operateService");
		userService = (IUserService) ctx.getBean("userinfoService");
	}

	public ISysfunService getSysfunService() {
		return sysfunService;
	}

	public void setSysfunService(ISysfunService sysfunService) {
		this.sysfunService = sysfunService;
	}

	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	public IOperateService getOperateService() {
		return operateService;
	}

	public void setOperateService(IOperateService operateService) {
		this.operateService = operateService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
