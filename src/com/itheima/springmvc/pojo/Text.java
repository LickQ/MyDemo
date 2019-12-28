package com.itheima.springmvc.pojo;

import java.util.Date;

public class Text {
private Integer tid;
private String title;
private String detail;
private Date createtime;
private String  authorname;
private String  checkMark;
private String  summary;
private Integer cid;
private Integer status;
private Integer eid;



public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}

public Integer getTid() {
	return tid;
}
public void setTid(Integer tid) {
	this.tid = tid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public Date getCreatetime() {
	return createtime;
}
public void setCreatetime(Date createtime) {
	this.createtime = createtime;
}
public String getAuthorname() {
	return authorname;
}
public void setAuthorname(String authorname) {
	this.authorname = authorname;
}
public String getCheckMark() {
	return checkMark;
}
public void setCheckMark(String checkMark) {
	this.checkMark = checkMark;
}
public String getSummary() {
	return summary;
}
public void setSummary(String summary) {
	this.summary = summary;
}


}
