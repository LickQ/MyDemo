package com.itheima.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itheima.springmvc.dao.ExpertMapper;
import com.itheima.springmvc.pojo.Expert;

@Service
@Transactional
public class ExpertServiceImpl implements ExpertService{
	@Autowired
	private ExpertMapper expertMapper;

	//注册
		public void register(Expert expert) {
			expertMapper.addExpert(expert);
		}

	    /**
	     * 登录
	     * 根据用户名和密码进行查询
	     */
		//登录
	    public Expert login(String expertname, String password) {
	    	return expertMapper.findByExpertNameAndPassword(expertname, password);
	    }
	    //根据用户名查询
		public Expert findByExpertName(String expertname) {
			// TODO Auto-generated method stub
			return expertMapper.findByExpertName(expertname);
		}
		//查询所有专家信息
		public List<Expert> findExpert(){
			return expertMapper.findExpert();
		}
		//修改
		public void updateAuthorById(Expert expert) {
			expertMapper.updateExpertById(expert);
		}
		
}
