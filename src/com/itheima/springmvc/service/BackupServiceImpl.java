package com.itheima.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.springmvc.dao.BackupMapper;
import com.itheima.springmvc.pojo.Backup;

@Service
public class BackupServiceImpl implements BackupService {
	@Autowired
	private BackupMapper backupmapper;

	// 添加备份
	@Override
	public void AddBackup(Backup backup) {

		backupmapper.addBackup(backup);
	}

   //查询所有备份
	@Override
	public List<Backup> findBackupHistory() {
		return backupmapper.findBackupHistory();
	}

}
