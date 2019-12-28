package com.itheima.springmvc.dao;

import java.util.List;
import java.util.Map;

import com.itheima.springmvc.pojo.Text;

public interface TextMapper {

	// 查询所有信息
	public List<Text> findAll();

	//修改
	int updateById(Text record);
	//根据主键查询
	public Text selectByPrimaryKey(Integer tid);
	//投稿
    void addText(Text text);
    //根据id删除
    int deleteById(Integer tid);
    //模糊查询
    public List<Text> findTextByTextname(String title);
    //根据用户名查询
    List<Text> findTextByAuthorName(String authorname);
    List<Text> findTextByCheckMark (String checkMark);
    
    int updatecheckMark(Text record);
    //分页
    public List<Text> findTextByPage(Integer cid, Integer index, Integer currentCount);
    //查询类别总个数
    public int getCount(Integer cid);
    //根据cid 和checkmark查询
    public List<Text> findTextBycid(Map<Object, Object> map);
    //根据cid 和checkmark查询
    public List<Text> findTextByCKandCid(Map<Object, Object> map);
}
