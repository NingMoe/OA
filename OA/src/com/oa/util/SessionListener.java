package com.oa.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**

 * ����session
 * Application Lifecycle Listener implementation class SessionListener
 */

public class SessionListener implements HttpSessionListener {

	private SessionManager sessionManager = SessionManager.getInstance();

	/**
	 * Default constructor.
	 */
	public SessionListener() {
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		sessionManager.AddSession(se.getSession());
		se.getSession().getServletContext()
				.setAttribute("online", sessionManager.getAllSession());
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		sessionManager.DelSession(se.getSession());
		se.getSession().getServletContext()
				.setAttribute("online", sessionManager.getAllSession());
	}

}