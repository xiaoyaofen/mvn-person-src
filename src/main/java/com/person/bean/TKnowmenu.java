package com.person.bean;

public class TKnowmenu   {

	private Long id;

	private String menuname;

	private String url;

	private String pid;

	private Long scope;

	public TKnowmenu() {
	}

	public TKnowmenu(Long id, String menuname, String url, String pid, Long scope) {
		this.id = id;
		this.menuname = menuname;
		this.url = url;
		this.pid = pid;
		this.scope = scope;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Long getScope() {
		return scope;
	}

	public void setScope(Long scope) {
		this.scope = scope;
	}
}
