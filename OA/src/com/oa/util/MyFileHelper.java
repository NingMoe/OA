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

	/** 静态创建根目录 */
	static {
		try {
			init();
		} catch (Exception e) {
			rebuild_env();
		}
	}

	/**
	 * 初始化文件环境
	 */
	public static void init() {
		File f = new File(getAbsolutePath());
		if (!f.exists())
			f.mkdirs();
	}

	/**
	 * 重建文件环境
	 */
	public static void rebuild_env() {
		// 列出可用的文件系统根
		File[] parts = File.listRoots();
		for (File part : parts) {
			if (part.canRead() && part.canWrite()) {
				DISK = part.getAbsolutePath();
				init();
			}
		}
	}

	/**
	 * 将文件存入指定路径
	 * 
	 * @param srcFile
	 * @param destPath
	 * @param finalPath
	 *            t:最终路径 f:相对路径
	 */
	public static void put_file(File srcFile, String destPath, boolean finalPath) {

		String p = finalPath ? destPath : getAbsolutePath()
				+ check_starts(destPath);

		File destFile = new File(p);
		// 如果文件目录不存在
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
	 * 创建文件目录(文件夹)
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
	 * 检查路径当中的分隔符
	 * 
	 * @param path
	 * @return1 path 统一化分隔符后的路径
	 */
	public static String check_separator(String path) {
		String s = File.separator;
		return path.trim().replace("/", s).replace("\\", s);
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean delete(String path) {
		File f = new File(getAbsolutePath() + check_starts(path));
		if (f.exists()) {
			return f.delete();
		}
		System.err.println("文件已不存在!!!");
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
	 * 检查路径首字母分隔符
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
	 * 检查路径末尾分隔符
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
	 * 从路径中取出文件名
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
	 * 文件重命名,规则(用户名+文件后缀名)
	 * 
	 * @param fileName
	 * @param currUserName
	 * @return
	 */
	public static String fileRename(String fileName, String currUserName) {
		if (fileName == null || currUserName == null || fileName.isEmpty()
				|| currUserName.isEmpty()) {
			System.err.println("参数无效!");
			return null;
		}
		int pos = fileName.lastIndexOf(".");
		if(pos<0){
			System.err.println("文件名参数无效!");
			return null;
		}
		return currUserName+fileName.substring(pos+1);
	}

	public static String getAbsolutePath() {
		return DISK + PRO_NAME;
	}
}
