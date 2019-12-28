package com.itheima.springmvc.service;

import java.util.List;

import com.itheima.springmvc.pojo.Author;

public interface AuthorService {
	// 通过用户名及密码核查用户登录
	Author login(String authorname, String password);

	// 增加用户
	void register(Author author);

	// 根据用户名查询
	public Author findByAuthorName(String authorname);

	public List<Author> findAuthor();

	public void updateAuthorById(Author author);
}
