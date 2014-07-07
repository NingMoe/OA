package com.oa.util;

import java.util.Comparator;

public class ColumnComparator implements Comparator<Column>{

	@Override
	public int compare(Column o1, Column o2) {
		if(o1.getId()>=o2.getId())
		{
			return 1;
		}
		return -1;
	}

}
