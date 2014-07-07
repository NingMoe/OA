package com.oa.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsUtil {

	public static List<Column> sort(List<Column> list) {

		List<Column> newCList = null;
		if (list != null) {
			ColumnComparator cc = new ColumnComparator();
			Collections.sort(list, cc);
			newCList = new ArrayList<Column>();
			for (int i = 0, size = list.size(); i < size; i++) {

				Column c1 = list.get(i);
				StringBuffer sub = new StringBuffer();
				if (c1 != null && c1.getDepth() != null && c1.getPid() != null) {
					for (int j = 0, len = c1.getDepth(); j < len; j++) {
						sub.append("&nbsp;&nbsp;&nbsp;&nbsp;");
						if (j == len - 1) {
							sub.append("|___ ");
						}
					}

					c1.setSubIdent(sub.toString());
					if (c1.getPid() == 0) {
						c1.setIndex(i);
						newCList.add(c1);
					} else {
						int newSize = newCList.size();
						int k = 0;
						while (k < newSize) {
							Column ck = newCList.get(k);

							if (ck.getId().intValue() == c1.getPid().intValue()) {
								c1.setIndex(ck.getIndex() + 0.1);
								newCList.add((int) c1.getIndex(), c1);
								break;
							}
							k++;

						}
					}
				}
			}
			Collections.sort(newCList);

		}
		return newCList;

	}
	
	public static void main(String[] args) {
		
		List<Column> columns = new ArrayList<Column>();
		Column c1 = new Column();
		c1.setId(1);
		c1.setPid(0);
		c1.setDepth(0);
		c1.setTitle("c1");
		
		columns.add(c1);
		
		Column c11 = new Column();
		c11.setId(11);
		c11.setPid(1);
		c11.setDepth(1);
		c11.setTitle("c11");
		columns.add(c11);
		
		Column c2 = new Column();
		c2.setId(2);
		c2.setPid(0);
		c2.setDepth(0);
		c2.setTitle("c2");
		columns.add(c2);
		
		Column c21 = new Column();
		c21.setId(21);
		c21.setPid(2);
		c21.setDepth(1);
		c21.setTitle("c21");
		columns.add(c21);
		
		Column c2111 = new Column();
		c2111.setId(2111);
		c2111.setPid(211);
		c2111.setDepth(3);
		c2111.setTitle("c2111");
		columns.add(c2111);
		
		
		Column c211 = new Column();
		c211.setId(211);
		c211.setPid(21);
		c211.setDepth(2);
		c211.setTitle("c211");
		columns.add(c211);
		
		List<Column> cs  = CollectionsUtil.sort(columns);
		
		for (int i = 0; i < cs.size(); i++) {
			System.out.println(cs.get(i).getSubIdent() + cs.get(i).getTitle());
		}
		
	}

}
