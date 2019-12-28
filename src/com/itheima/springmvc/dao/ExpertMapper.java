package com.itheima.springmvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itheima.springmvc.pojo.Expert;


public interface ExpertMapper {
	 //增加用户
    void addExpert(Expert expert);
   //根据用户名查询
    Expert  findByExpertName(String expertname);
  //根据用户名和密码查找。mybatis中有多个参数时，需要使用@Param注解
    Expert  findByExpertNameAndPassword(@Param("expertname")String expertname,@Param("password")String password);
    //查询专家信息
    List<Expert> findExpert();
    //更新专家
    void updateExpertById(Expert expert);
}
