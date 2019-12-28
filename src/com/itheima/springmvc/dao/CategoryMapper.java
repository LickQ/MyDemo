package com.itheima.springmvc.dao;

import java.util.List;

import com.itheima.springmvc.pojo.Category;
import com.itheima.springmvc.pojo.Text;

public interface CategoryMapper {
	public List<Category>  findAllCategory();
	public void addCategory(String cname);
	public void deleteCategoryById(Integer cid);
	public int findCidByCName(String cname);
}
