package com.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.model.common.Common;
import com.service.base.BaseService;
@Service
@Transactional
public class CommonService extends BaseService<Common> {
	
	public PageInfo<Common> selectCommon(Common common,int page,int limit){
		PageInfo<Common> info=null;
		if(common.getCommonTitle() !=null && !common.getCommonTitle().equals("")){
			info=this.select(common,page,limit);
		}else{
			info=this.selectAll(page, limit);
		}
		return info;
	}
}
