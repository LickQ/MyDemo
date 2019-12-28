package com.itheima.springmvc.service;

import java.util.List;

import com.itheima.springmvc.pojo.PageBean;
import com.itheima.springmvc.pojo.Text;

public interface TextService {
	// 查询所有信息
	public List<Text> findAll();

	// 修改
	public void updateTextById(Text text);
	public void updatecheckMark(Text text);
	// 根据主键查询
	public Text selectTextById(Integer id);

	// 投稿
	public void addText(Text text);

	// 删除
	public void deleteById(Integer id);
	//模糊查询
	public List<Text> findTextByTextname(String title);
	public List<Text> findTextByAuthorName(String authorname);
	public List<Text> findTextByCheckMark(String checkMark);
	public List<Text> findTextByCKandCid(String checkMark,Integer cid);
	public PageBean findProductListByCid(Integer cid, Integer currentPage, Integer currentCount);
}
