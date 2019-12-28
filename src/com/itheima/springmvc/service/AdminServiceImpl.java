package com.itheima.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.springmvc.dao.AdminMapper;
import com.itheima.springmvc.pojo.Admin;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;
	public Admin Adminlogin(String adminname,String password) {
		return adminMapper.Adminlogin(adminname, password);
	}
}
