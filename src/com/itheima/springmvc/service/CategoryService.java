package com.itheima.springmvc.service;

import java.util.List;

import com.itheima.springmvc.pojo.Category;

public interface CategoryService {
	public List<Category> findAllCategory();
	public void addCategory(String cname);
	public void deleteCategoryById(Integer cid);
}
