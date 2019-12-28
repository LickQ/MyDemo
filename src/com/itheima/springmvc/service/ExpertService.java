package com.itheima.springmvc.service;

import java.util.List;

import com.itheima.springmvc.pojo.Expert;

public interface ExpertService {
	// 通过用户名及密码核查用户登录
	Expert  login(String expertrname, String password);

	// 增加用户
	void register(Expert expert);

	// 根据用户名查询
	Expert findByExpertName(String expertname);
	//查询所有专家信息
	public List<Expert> findExpert();
	//修改
	public void updateAuthorById(Expert expert);
}

