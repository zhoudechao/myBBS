package com.controller.type;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.base.BaseController;
import com.github.pagehelper.PageInfo;
import com.model.board.Board;
import com.model.buser.Buser;
import com.model.type.Type;
import com.service.type.TypeService;

@Controller
@RequestMapping("/type")
public class TypeController extends BaseController<Type> {
	@Autowired
	private TypeService typeService;
	
	@SuppressWarnings("all")
	@ResponseBody
	@RequestMapping(value="/typeData")
	public Map<String, Object> typeData(Model model,int page,int limit,Type type){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			PageInfo<Type> info=typeService.selectType(page,limit,type);
			map.put("code", 0);
	        map.put("msg", "");
	        map.put("count", info.getTotal());
	        map.put("data", info.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Type type){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			int status=typeService.delete(type.getTypeId());
			map.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/setUse")
	public Map<String, Object> setUse(Type type){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			type=typeService.queryone(type);
			if(type.getZt().equals("0")){
				type.setZt("1");
			}else{
				type.setZt("0");
			}
			int status=typeService.updateNotNull(type);
			map.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Map<String, Object> save(Type type){
		int status=0;
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			if(type.getTypeId()!=null && !type.getTypeId().equals("")){
				status = typeService.updateNotNull(type);
				map.put("status", String.valueOf(status));
			}else{
				type.setZt("1");
				//把当前系统时间转为datetime的格式存入数据库
				Date date=new Date();
				Timestamp timestamp=new Timestamp(date.getTime());
				type.setTypeCreatetime(timestamp);
				status = typeService.save(type);
				map.put("status", String.valueOf(status));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//前台主页面中查询出帖子的类型
	@ResponseBody
	@RequestMapping(value="/listType",method=RequestMethod.POST,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public List<Type> listType(){
		List<Type> selectAll = typeService.selectAllbyExample();
		return selectAll;
	}
}
