package com.itheima.springmvc.dao;

import java.util.List;

import com.itheima.springmvc.pojo.Backup;

public interface BackupMapper {

	//添加备份
	public void addBackup(Backup backup);
	//查询备份记录
	public List<Backup> findBackupHistory();
}
