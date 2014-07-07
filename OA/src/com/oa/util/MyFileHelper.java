package com.oa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharSequenceUtils;
import org.apache.struts2.views.xslt.StringAdapter;

public class MyFileHelper {

	private static String DISK = "D:\\";
	private static String PRO_NAME = Constant.WEB_NAME;

	/** ��̬������Ŀ¼ */
	static {
		try {
			init();
		} catch (Exception e) {
			rebuild_env();
		}
	}

	/**
	 * ��ʼ���ļ�����
	 */
	public static void init() {
		File f = new File(getAbsolutePath());
		if (!f.exists())
			f.mkdirs();
	}

	/**
	 * �ؽ��ļ�����
	 */
	public static void rebuild_env() {
		// �г����õ��ļ�ϵͳ��
		File[] parts = File.listRoots();
		for (File part : parts) {
			if (part.canRead() && part.canWrite()) {
				DISK = part.getAbsolutePath();
				init();
			}
		}
	}

	/**
	 * ���ļ�����ָ��·��
	 * 
	 * @param srcFile
	 * @param destPath
	 * @param finalPath
	 *            t:����·�� f:���·��
	 */
	public static void put_file(File srcFile, String destPath, boolean finalPath) {

		String p = finalPath ? destPath : getAbsolutePath()
				+ check_starts(destPath);

		File destFile = new File(p);
		// ����ļ�Ŀ¼������
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����ļ�Ŀ¼(�ļ���)
	 */
	public static void mkdirs(String path, boolean realPth) {

		path = realPth ? path : MyFileHelper.getAbsolutePath()
				+ check_starts(path);
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

	/**
	 * ���·�����еķָ���
	 * 
	 * @param path
	 * @return1 path ͳһ���ָ������·��
	 */
	public static String check_separator(String path) {
		String s = File.separator;
		return path.trim().replace("/", s).replace("\\", s);
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param path
	 * @return
	 */
	public static boolean delete(String path) {
		File f = new File(getAbsolutePath() + check_starts(path));
		if (f.exists()) {
			return f.delete();
		}
		System.err.println("�ļ��Ѳ�����!!!");
		return false;
	}

	public static InputStream get_file_stream(String path) {
		InputStream is = null;
		try {
			is = new FileInputStream(getAbsolutePath() + check_starts(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return is;
	}

	/**
	 * ���·������ĸ�ָ���
	 * 
	 * @param path
	 * @return
	 */
	public static String check_starts(String path) {
		path = check_separator(path);
		if (!path.startsWith(File.separator))
			path = File.separator + path;
		return path;
	}

	/**
	 * ���·��ĩβ�ָ���
	 * 
	 * @param path
	 * @return
	 */
	public static String check_ends(String path) {
		path = check_separator(path);
		if (!path.endsWith(File.separator))
			path = path + File.separator;
		return path;
	}

	/**
	 * ��·����ȡ���ļ���
	 * 
	 * @param path
	 * @return name
	 */
	public static String getNameFromPath(String path) {
		String s = check_separator(path);
		if (!s.contains(File.separator)) {
			return path;
		} else {
			int i = s.lastIndexOf(File.separator);
			if (s.endsWith(File.separator)) {
				String sub1 = s.substring(0, i);
				return sub1.substring(sub1.lastIndexOf(File.separator) + 1);
			} else {
				return s.substring(i + 1);
			}
		}

	}

	/**
	 * �ļ�������,����(�û���+�ļ���׺��)
	 * 
	 * @param fileName
	 * @param currUserName
	 * @return
	 */
	public static String fileRename(String fileName, String currUserName) {
		if (fileName == null || currUserName == null || fileName.isEmpty()
				|| currUserName.isEmpty()) {
			System.err.println("������Ч!");
			return null;
		}
		int pos = fileName.lastIndexOf(".");
		if(pos<0){
			System.err.println("�ļ���������Ч!");
			return null;
		}
		return currUserName+fileName.substring(pos+1);
	}

	public static String getAbsolutePath() {
		return DISK + PRO_NAME;
	}
}
