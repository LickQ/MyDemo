package com.itheima.springmvc.dao;

import java.util.List;
import com.itheima.springmvc.pojo.History;

public interface HistoryMapper{
    //增加记录
    public void addHisroy(History history);
    //获取所有历史清单表
    public List<History> findAllHistory();
}