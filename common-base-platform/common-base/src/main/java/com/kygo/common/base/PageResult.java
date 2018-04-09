package com.kygo.common.base;

import java.util.List;

/**
 * 分页结果
 * @date 2017年12月14日
 * @version 1.0
 */
public class PageResult<T> {
	
	private int totalSize;//总页数
	
	private int pageNum;//当前页
	
	private List<T> list;

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
