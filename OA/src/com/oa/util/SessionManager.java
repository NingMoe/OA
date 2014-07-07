package com.oa.util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * ����session
 */
public class SessionManager {

	/**
	 * ��ȡ��ʼ������
	 */
	private static SessionManager instance;

	/**
	 * session�������
	 */
	private HashMap<String, HttpSession> mymap;

	/**
	 * ˽�й�����
	 */
	private SessionManager() {
		mymap = new HashMap<String, HttpSession>();
	}

	/**
	 * ��ȡ�������
	 * @return �������
	 */
	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}

	/**
	 * ����session
	 * @param session �Ự
	 */
	public synchronized void AddSession(HttpSession session) {
		if (session != null) {
			mymap.put(session.getId(), session);
		}
	}

	/**
	 * 
	 * �Ƴ�һ��session
	 * @param session  �Ự
	 */
	public synchronized void DelSession(HttpSession session) {
		if (session != null) {
			mymap.remove(session.getId());
		}
	}

	/**
	 * 
	 * ͨ��sessionid��ȡsession����
	 * @param session_id �ỰID sessionid
	 * @return session����
	 */
	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return mymap.get(session_id);

	}
	
	/**
	 * ��ȡ����session
	 * @return
	 */
	public HashMap<String, HttpSession> getAllSession(){
		return this.mymap;
	}

}