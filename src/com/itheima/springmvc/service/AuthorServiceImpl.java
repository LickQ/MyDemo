package com.itheima.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itheima.springmvc.dao.AuthorMapper;
import com.itheima.springmvc.pojo.Author;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorMapper authorMapper;

	// 注册
	@Override
	public void register(Author author) {
		authorMapper.addAuthor(author);
	}

	/**
	 * 登录 根据用户名和密码进行查询
	 */
	// 登录
	public Author login(String authorname, String password) {
		return authorMapper.findByAuthorNameAndPassword(authorname, password);
	}

    //根据作者名查询
	public Author findByAuthorName(String authorname) {
		// TODO Auto-generated method stub
		return authorMapper.findByAuthorName(authorname);
	}

	// 查询作者
	public List<Author> findAuthor() {
		return authorMapper.findAuthor();
	}

	// 修改作者信息
	public void updateAuthorById(Author author) {
		authorMapper.updateAuthorById(author);
	}

}
