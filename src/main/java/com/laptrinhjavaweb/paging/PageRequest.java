package com.laptrinhjavaweb.paging;

public class PageRequest implements Pageable{
	private Integer page;
	private Integer limit;	
	
	public PageRequest(Integer page, Integer limit) {
		if(page == null) {
			this.page = 1;
		}else {
			this.page = page;
		}
		if(limit == null) {
			this.limit = 5;
		}else {
			this.limit = limit;
		}				
	}
	@Override
	public Integer getPage() {	
		return this.page;
	}
	@Override
	public void setPage(Integer page) {
		this.page = page;
	}
	@Override
	public Integer getOffset() {		
		if(this.page != null && this.limit != null) {
			return (this.page - 1) * this.limit;	
		}
		return null;
	}

	@Override
	public Integer getLimit() {		
		return this.limit;
	}	

}
