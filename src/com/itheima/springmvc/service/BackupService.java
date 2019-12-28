package com.itheima.springmvc.service;

import java.util.List;

import com.itheima.springmvc.pojo.Backup;


public interface BackupService {

	 //添加历史纪录
    public void AddBackup(Backup backup);
    //获取所有历史记录
    public List<Backup> findBackupHistory();
}
