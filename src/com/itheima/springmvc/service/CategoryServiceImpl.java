package com.itheima.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.springmvc.dao.CategoryMapper;
import com.itheima.springmvc.pojo.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		return categoryMapper.findAllCategory();
	}
	public void addCategory(String cname){
		categoryMapper.addCategory(cname);
	}
	public void deleteCategoryById(Integer cid) {
		categoryMapper.deleteCategoryById(cid);
	}
}
