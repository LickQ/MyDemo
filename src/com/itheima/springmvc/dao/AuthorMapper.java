package com.itheima.springmvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itheima.springmvc.pojo.Author;

public interface AuthorMapper {
	 //增加用户
    void addAuthor(Author author);
   //根据用户名查询
    public Author findByAuthorName(String authorname);
  //根据用户名和密码查找。mybatis中有多个参数时，需要使用@Param注解
    Author findByAuthorNameAndPassword(@Param("authorname")String username,@Param("password")String password);
    //查询所有作家
    List<Author> findAuthor(); 
    //更新作者
    void updateAuthorById(Author author);
    
}
