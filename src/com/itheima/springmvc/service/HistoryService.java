package com.itheima.springmvc.service;

import java.util.List;

import com.itheima.springmvc.pojo.History;

public interface HistoryService {
	 //添加历史纪录
    public void record(History history);
    //获取所有历史记录
    public List<History> findAllHistory();
}
