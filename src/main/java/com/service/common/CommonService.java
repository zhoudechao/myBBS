package com.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.model.common.Common;
import com.service.base.BaseService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;
@Service
@Transactional
public class CommonService extends BaseService<Common> {
	
	public PageInfo<Common> selectCommon(Common common,int page,int limit){
		Example example=new Example(Common.class);
		Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(common.getCommonTitle())){
			String username="%"+common.getCommonTitle()+"%";
			criteria.andLike("commonTitle", username);
		}
		PageInfo<Common> info =this.selectByExample(page,limit,example);
		return info;
		/*PageInfo<Common> info=null;
		if(common.getCommonTitle() !=null && !common.getCommonTitle().equals("")){
			info=this.select(common,page,limit);
		}else{
			info=this.selectAll(page, limit);
		}
		return info;*/
	}
}
