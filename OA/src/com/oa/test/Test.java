package com.oa.test;

import java.io.UnsupportedEncodingException;

public class Test {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {


		/*
		 * List<Column> columns = new ArrayList<Column>(); Column c1 = new
		 * Column(); c1.setId(1); c1.setPid(0); c1.setDepth(0);
		 * c1.setTitle("c1");
		 * 
		 * columns.add(c1);
		 * 
		 * Column c11 = new Column(); c11.setId(11); c11.setPid(1);
		 * c1.setDepth(1); c11.setTitle("c11"); columns.add(c11);
		 * 
		 * Column c2 = new Column(); c2.setId(2); c2.setPid(0); c1.setDepth(0);
		 * c2.setTitle("c2"); columns.add(c2);
		 * 
		 * Column c21 = new Column(); c21.setId(21); c21.setPid(2);
		 * c1.setDepth(1); c21.setTitle("c21"); columns.add(c21);
		 * 
		 * Column c211 = new Column(); c211.setId(211); c211.setPid(21);
		 * c1.setDepth(2); c211.setTitle("c211"); columns.add(c211);
		 * 
		 * List<Column> cs = CollectionsUtil.sort(columns);
		 * 
		 * for (int i = 0; i < cs.size(); i++) {
		 * System.out.println(cs.get(i).getTitle()); }
		 */

		/*
		 * ApplicationContext ctx = new
		 * ClassPathXmlApplicationContext("beans*.xml");
		 * 
		 * DepartServiceImpl deptService = (DepartServiceImpl)
		 * ctx.getBean("departService");
		 * 
		 * Page page = new Page();
		 * 
		 * deptService.getPage(page);
		 * 
		 * for (Departinfo depart : (List<Departinfo>)page.getList()) {
		 * System.out.println(depart.getDepartName()); }
		 * 
		 * SessionFactory sf = new
		 * Configuration().configure().buildSessionFactory();
		 */
		/*
		 * Fileinfo fileinfo = new Fileinfo(); fileinfo.setFileId(12);
		 * fileinfo.setUserinfo(new Userinfo("zhan")); try { Method m =
		 * fileinfo.getClass().getMethod("getFileId", null);
		 * System.out.println(m.invoke(fileinfo, null));
		 * 
		 * Method m1 = fileinfo.getClass().getMethod("getUserinfo", null);
		 * Object o = m1.invoke(fileinfo, null);
		 * System.out.println(o.getClass().getMethod("getUserId",
		 * null).invoke(o, null)); } catch (Exception e) { // TODO: handle
		 * exception e.printStackTrace(); }
		 * 
		 * StringBuilder sb = new StringBuilder("12.34.45.");
		 * sb.appendCodePoint(0); System.out.println(sb.toString());
		 * 
		 * System.out.println("contains: "+sb.toString().contains("."));
		 * 
		 * char[] chars = sb.toString().toCharArray(); int count = 0; for (int
		 * i=0;i<chars.length;i++) { char c = chars[i]; if(c=='.'){ count++;
		 * System.out.println("��"+count+"��'.',���±�["+i+"]��!"); } }
		 * 
		 * String p = sb.toString().trim();
		 * 
		 * System.out.println("p:"+p+",starts: "+p.startsWith(".")+",ends: "+p.
		 * endsWith(".")); for(int i=0;i<100000;i++){ String[] ss =
		 * p.split("\\."); if(ss[0].equals("12") &&
		 * ss[2].equals("45"))continue;else System.err.println("˳�����@"); }
		 * 
		 * 
		 * p = "123 "; p.trim(); System.out.print(p); System.out.println("456");
		 * 
		 * Map<String, Object> node1 = new HashMap<String, Object>();
		 * node1.put("a", 1); Map<String, Object> node2 = new HashMap<String,
		 * Object>(); node2.put("b",2); node2.put("c",3); node1.putAll(node2);
		 * for (String n : node1.keySet()) {
		 * System.out.println("key:"+n+", value:"+node1.get(n)); }
		 */

		/* org.apache.commons.io.FileUtils.deleteQuietly() */

		/*
		 * String path =
		 * "F:/ʵ����Ŀ/Y2_Final_Project/PROJ_OA/OA/build/classes.jsppp/";
		 * System.out.println(path); if(!path.trim().endsWith("/")){
		 * System.out.println(path.substring(0, path.lastIndexOf("/")+1));
		 * }else{ System.out.println(path); }
		 */

		/*
		 * String root =
		 * Thread.currentThread().getContextClassLoader().getResource
		 * (".").getPath(); System.out.println(root); String sL = "/"; String sR
		 * = "\\"; System.out.println(File.separator.equals(sL));
		 */
		/*File[] parts = File.listRoots();

		for (File part : parts) {

			System.out.println(part.getAbsolutePath());

>>>>>>> .r660
		}*/
		//java��ȡ��ǰ������

		/*String projectname = System.getProperty("user.dir");
		System.out.println(projectname+"    "+MyFileHelper.check_ends(projectname)); 
		
		String pn = projectname.substring(projectname.lastIndexOf('\\')+1);
		System.out.println(pn); */
	
		/*String s = "123\\456\\789/110\\123/456//";
		//System.out.println("\\789");
		System.out.println(MyFileHelper.getNameFromPath(s));*/
		
		/*Pattern p = Pattern.compile("\\d");
		System.out.println(p.matcher("8"));*/
		
		/*Map<String,Object> m = new HashMap<String,Object>();
		System.out.println((String)m.get("11")==null);*/
		
		/*Calendar c = Calendar.getInstance();
		c.set(2014,10,21);
		
		Calendar a = Calendar.getInstance();
		a.set(2014,10,21);
		
		System.out.println(c.compareTo(a));*/
		/*SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		c.add(Calendar.DAY_OF_MONTH, -45);*/
		
		new Object();
	}

	 /**
	  * �ַ����ת����ʵ�ַ���
	  * @param str  ��ת��������ַ�
	  * @param oldCharset ԭ����
	  * @param newCharset Ŀ�����
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 public static String changeCharset(String str, String oldCharset, String newCharset)
	   throws UnsupportedEncodingException {
	  if (str != null) {
	   //�þɵ��ַ��������ַ�������ܻ�����쳣��
	   byte[] bs = str.getBytes(oldCharset);
	   //���µ��ַ��������ַ�
	   return new String(bs, newCharset);
	  }
	  return null;
	 }
}
