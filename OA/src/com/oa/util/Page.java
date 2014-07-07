
package com.oa.util;

import java.util.List;


public class Page {
	
	private int currentIndex;
	private int totalPages;
	private int pageSize;
	private long totalRecords;
	private List list;
	private int pageStartNum;

	public Page() {
		currentIndex = Constant.PAGE_DEFAULT_INDEX;
		pageSize = Constant.PAGE_DEFAULT_SIZE;
		pageStartNum = Constant.PAGE_DEFAULT_HEADER;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		if (currentIndex < 1) {
			this.currentIndex = Constant.PAGE_DEFAULT_INDEX;
		} else if (this.totalPages > 0 && currentIndex > this.totalPages) {
			this.currentIndex = this.totalPages;
		} else {
			this.currentIndex = currentIndex;
		}

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
		setTotalPages();
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	private void setTotalPages() {
		if (this.pageSize != 0 && this.totalRecords != 0)
			this.totalPages = (int) Math.ceil(this.totalRecords
					/ (float) this.pageSize);
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getPageStartNum() {
		return this.pageStartNum = (this.currentIndex - 1) * this.pageSize + 1;
	}

}
