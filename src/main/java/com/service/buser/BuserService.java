package com.service.buser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.model.buser.Buser;
import com.model.buser.BuserExtend;
import com.service.base.BaseService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Service
@Transactional
public class BuserService extends BaseService<Buser> {
	public PageInfo<Buser> selectBuser(BuserExtend buserExtend){
		Example example=new Example(Buser.class);
		Criteria criteria=example.createCriteria();
		Criteria criteria2=example.createCriteria();
		if(StringUtil.isNotEmpty(buserExtend.getUserName())){
			String username="%"+buserExtend.getUserName()+"%";
			criteria.andLike("userName", username);
		}
		if(buserExtend.getStartTime()!=null && buserExtend.getEndTime() !=null){
			criteria2.andGreaterThanOrEqualTo("startTime", buserExtend.getStartTime())
			.andLessThanOrEqualTo("endTime", buserExtend.getEndTime());
			example.or(criteria2);
		}
		PageInfo<Buser> info =this.selectByExample(buserExtend.getPage(),buserExtend.getLimit(),example);
		return info;
		
		/*PageInfo<Buser> info =null;
		if(buserExtend.getUserName()!=null){
			info=this.select(buserExtend,buserExtend.getPage(),buserExtend.getLimit());
		}else{
			info=this.selectAll(buserExtend.getPage(),buserExtend.getLimit());
		}
		return info;*/
	}
}
