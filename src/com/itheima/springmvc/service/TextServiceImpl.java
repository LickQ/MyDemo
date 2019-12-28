package com.itheima.springmvc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.springmvc.dao.HistoryMapper;
import com.itheima.springmvc.dao.TextMapper;
import com.itheima.springmvc.pojo.History;
import com.itheima.springmvc.pojo.PageBean;
import com.itheima.springmvc.pojo.Text;

@Service
public class TextServiceImpl implements TextService {
	@Autowired
	private TextMapper textMapper;
	@Autowired
	private HistoryMapper historymapper;

	public List<Text> findAll() {
		// TODO Auto-generated method stub
		return textMapper.findAll();
	}

//修改
	public void updateTextById(Text text) {
		
		textMapper.updateById(text);
	}

	public void updatecheckMark(Text text) {
		textMapper.updatecheckMark(text);
	}
//根据主键查询
	public Text selectTextById(Integer id) {
		return textMapper.selectByPrimaryKey(id);
	}

	// 添加
	public void addText(Text text) {
		text.setCreatetime(new Date());
		text.setStatus(0);
		text.setCheckMark("未审核");
		textMapper.addText(text);
		History history= new History();
		history.setTname(text.getTitle());
		history.setLatestTime(text.getCreatetime());
		history.setState("待审核");
		historymapper.addHisroy(history);
	}

	// 删除
	public void deleteById(Integer id) {
		
		textMapper.deleteById(id);
		
	}

	// 模糊查询
	public List<Text> findTextByTextname(String title) {
		return textMapper.findTextByTextname(title);
	}

	public List<Text> findTextByAuthorName(String authorname) {
		return textMapper.findTextByAuthorName(authorname);
	}

	public List<Text> findTextByCKandCid(String checkMark,Integer cid) {
		Map<Object, Object> map = new HashMap<>();
		map.put("cid", cid);
		map.put("checkMark", checkMark);
		
		return textMapper.findTextByCKandCid(map);
	}
	
	public List<Text> findTextByCheckMark (String checkMark) {

		return textMapper.findTextByCheckMark(checkMark);
	}

	
	

//根据cid 和checkmark分类查询
	public PageBean findProductListByCid(Integer cid, Integer currentPage, Integer currentCount) {
		// 封装一个PageBean 返回web层
		PageBean<Text> pageBean = new PageBean<Text>();

		// 1、封装当前页
		pageBean.setCurrentPage(currentPage);
		// 2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		// 3、封装总条数
		int totalCount = 0;
		totalCount = textMapper.getCount(cid);
		pageBean.setTotalCount(totalCount);
		// 4、封装总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		// 5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage - 1) * currentCount;
		String checkMark = "合格";
		Map<Object, Object> map = new HashMap<>();
		map.put("cid", cid);
		map.put("checkMark", checkMark);
		map.put("index", index);
		map.put("currentCount", currentCount);
		List<Text> list = textMapper.findTextBycid(map);
		pageBean.setList(list);
		return pageBean;
	}
	

}
