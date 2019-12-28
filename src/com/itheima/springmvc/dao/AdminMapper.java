package com.itheima.springmvc.dao;

import org.apache.ibatis.annotations.Param;

import com.itheima.springmvc.pojo.Admin;

public interface AdminMapper {
public Admin Adminlogin(@Param("adminname")String adminname,@Param("password")String password);
}
