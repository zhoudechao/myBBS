package com.service.type;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.mapper.type.TypeMapper;
import com.model.type.Type;
import com.service.base.BaseService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Service
@Transactional
public class TypeService extends BaseService<Type> {
	@Autowired
	private TypeMapper typeMapper;
	
	public PageInfo<Type> selectType(int page,int limit,Type type){
		Example example=new Example(Type.class);
		Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(type.getTypeName())){
			String typeName="%"+type.getTypeName()+"%";
			criteria.andLike("typeName", typeName);
		}
		return this.selectByExample(page, limit, example);
	}
	
	//查询出帖子的类型
	public List<Map<String, Object>> selectAllForMap(){
		 return typeMapper.selectAllForMap();
	}
}
