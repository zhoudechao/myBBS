package com.controller.mark;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.model.mark.Mark;
import com.service.mark.MarkService;

@RestController
@RequestMapping(value="/mark")
public class MarkController {
	@Autowired
	private MarkService markService;
	
	/**
	 * @Description: 根据用户id查询出用户的收藏夹
	 * @param @param id
	 * @param @param curr
	 * @param @param limit
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月20日
	 */
	@RequestMapping(value="/getMarkByUserId/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public Map<String,Object> getMarkByUserId(@PathVariable(value="id") Integer id,
			int curr,int limit){
		Map<String, Object> map=new HashMap<String, Object>();
		if(id !=null){
			PageInfo<Mark> info = markService.selectMarkByUserId(id,curr,limit);
			map.put("count", info.getTotal());
			map.put("data", info.getList());
			return map;
		}
		return null;
	}
	
	@RequestMapping(value="/addMark",method=RequestMethod.POST)
	public Map<String, Object> addMark(Mark mark){
		Map<String, Object> map=new HashMap<String, Object>();
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		mark.setMarkCreatetime(timestamp);
		int i = markService.save(mark);
		map.put("status", i);
		return map;
	}
}
