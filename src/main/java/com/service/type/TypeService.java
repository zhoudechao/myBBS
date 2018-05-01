package com.service.type;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.model.type.Type;
import com.service.base.BaseService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Service
@Transactional
public class TypeService extends BaseService<Type> {
	public PageInfo<Type> selectType(int page,int limit,Type type){
		Example example=new Example(Type.class);
		Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(type.getTypeName())){
			String typeName="%"+type.getTypeName()+"%";
			criteria.andLike("typeName", typeName);
		}
		return this.selectByExample(page, limit, example);
	}
}
