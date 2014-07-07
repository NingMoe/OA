package com.oa.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.oa.dao.IFileinfoDao;
import com.oa.entity.Fileinfo;
import com.oa.util.FileTypeConstant;

public class FileinfoDaoImpl extends BaseDaoIocImpl<Fileinfo> implements
		IFileinfoDao {

	@Override
	public Fileinfo findAvatar(String userId) {
		Criteria c = getCurrSession().createCriteria(Fileinfo.class);

		c.add(Restrictions.eq("parentId", FileTypeConstant.AVATAR_FOLDER_ID))
				.add(Restrictions.not(Restrictions.eq(
						"filetypeinfo.fileTypeId",
						FileTypeConstant.FOLDER_TYPE_ID)))
				.add(Restrictions.eq("userinfo.userId", userId));

		Fileinfo result = null;
		try {
			List list = c.list();
			result = (Fileinfo) ((list != null) ? list.get(0) : null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@Override
	public List<Fileinfo> findByCriteria(Map<String, Object> m) {
		
		Object[] objs = m.keySet().toArray();
		for (Object o : objs) {
			String k=(String) o;
			//��valueֵ
			if(m.get(k).getClass().isArray()){
				m.put(k,((String[])m.get(k))[0]);
			}
			if(m.get(k)==null || m.get(k).toString().trim().equals("")){
				m.remove(k);
			}
		}
		
		/*StringBuffer hql = new StringBuffer("SELECT f FROM Fileinfo f WHERE 1=1");*/
		Criteria criteria = getCurrSession().createCriteria(Fileinfo.class);
		
		if(m.get("scope")!=null){
			int id = Integer.valueOf((String) m.get("scope"));
			if(id!=FileTypeConstant.ROOT_FOLDER){
				criteria.add(Restrictions.like("pids","%,"+id+",%"));
			}
		}
		
		/* �ĵ����ص�Ĭ�Ϲ���:�Ǹ�Ŀ¼�������ļ����û�ͷ��Ŀ¼ */
		criteria.add(Restrictions.not(Restrictions.eq("id",
				FileTypeConstant.ROOT_FOLDER)))
		.add(Restrictions.not(Restrictions.eq("ifDelete",
				FileTypeConstant.DELETED)))
		.add(Restrictions.not(Restrictions.eq("parentId",
				FileTypeConstant.AVATAR_FOLDER_ID)));
		
		if(m.get("fileName")!=null){
			/*hql.append(" AND f.fileName LIKE '%:fileName%'");*/
			criteria.add(Restrictions.like("fileName", "%"+m.get("fileName")+"%"));
		}
		if(m.get("remark")!=null){
			/*hql.append(" AND f.remark LIKE '%:remark%'");*/
			criteria.add(Restrictions.like("remark", "%"+m.get("remark")+"%"));
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if(m.get("monAgo")!=null){
			
			Integer mon = Integer.valueOf((String) m.get("monAgo"));
			Date now = c.getTime();
			c.add(Calendar.MONTH, -mon);
			Date ago = c.getTime();
			/*String date = sf.format(c.getTime());*/
			
			/*hql.append(" AND f.createDate < '"+date+"'");*/
			criteria.add(Restrictions.between("createDate", ago,now));
		}else if(m.get("dayAgo")!=null){
			Integer day = Integer.valueOf((String) m.get("dayAgo"));
			Date now = c.getTime();
			c.add(Calendar.DAY_OF_YEAR, -day);
			Date ago = c.getTime();
			/*String date = sf.format(c.getTime());*/
			
			/*hql.append(" AND f.createDate < '"+date+"'");*/
			criteria.add(Restrictions.between("createDate", ago,now));
		}else if(m.get("beginDate")!=null && m.get("endDate")!=null){
			
			/*hql.append(" AND f.createDate BETWEEN ':beginDate' AND ':endDate'");*/
			Date begin=null,end=null;
			try {
				 begin = sf.parse((String) m.get("beginDate"));
				 end = sf.parse((String) m.get("endDate"));
				 criteria.add(Restrictions.between("createDate",begin,end));
			} catch (Exception e) {
				System.err.println("�쳣:�ַ��޷�����Ϊ��������!");
			}
		}
		if(m.get("typeId")!=null){
			/*hql.append(" AND f.filetypeinfo.fileTypeId = :typeId");*/
			criteria.add(Restrictions.eq("filetypeinfo.fileTypeId", Integer.valueOf((String) m.get("typeId"))));
		}
		if(m.get("userId")!=null){
			/*hql.append(" AND f.userinfo.userId = ':userId'");*/
			criteria.add(Restrictions.eq("userinfo.userId", m.get("userId")));
		}
		
		/*Query q = getDao().getCurrSession().createQuery(hql.toString());*/
		
		/*return q.list();*/
		
		List<Fileinfo> l=null;
		try {
			l = criteria.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}

}
