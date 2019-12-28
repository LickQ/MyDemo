package com.itheima.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.springmvc.dao.HistoryMapper;
import com.itheima.springmvc.pojo.History;
@Service
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	private HistoryMapper  historymapper;
	@Override
	// 添加记录
	public void record(History history) {
		 historymapper.addHisroy(history);
	}

	@Override
	public List<History> findAllHistory() {
		// TODO Auto-generated method stub
		return historymapper.findAllHistory();
	}

}
