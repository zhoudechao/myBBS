package com.mapper.buser;

import com.model.buser.Buser;
import com.model.buser.BuserExtend;

import tk.mybatis.mapper.common.Mapper;

public interface BuserMapper extends Mapper<Buser> {
	public BuserExtend selectUserAndPost(int id);
	
	public String selectUserNameById(int id);
	
	
}