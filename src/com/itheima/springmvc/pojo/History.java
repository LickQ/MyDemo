package com.itheima.springmvc.pojo;

import java.util.Date;

///历史清单model
public class History {
	private Integer hid;
	private String tname;
	private Date latestTime;
	private String state;
	public Integer getHid() {
		return hid;
	}
	public void setHid(Integer hid) {
		this.hid = hid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Date getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
}
