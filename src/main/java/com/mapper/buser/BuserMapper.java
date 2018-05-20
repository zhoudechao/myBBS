package com.mapper.buser;

import java.util.List;
import java.util.Map;

import com.model.buser.Buser;
import tk.mybatis.mapper.common.Mapper;

public interface BuserMapper extends Mapper<Buser> {
	public List<Map<String, Object>> selectUserAndPost(int id);
	
	public String selectUserNameById(int id);
}