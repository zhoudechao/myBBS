package com.controller.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.model.board.Board;
import com.model.user.User;
import com.service.board.BoardService;
import com.service.user.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardService boardService;
	/**
	 * 
	 * @Description: 版块管理跳转页面
	 * @param @param request
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月25日
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model){
		//获取当前用户
		User user=(User) request.getSession().getAttribute("user");
		user=userService.get(user);
		model.addAttribute("user",user);
		return "views/board/boardList";
	}
	@SuppressWarnings("all")
	@ResponseBody
	@RequestMapping(value="/boardData")
	public Map<String, Object> boardData(HttpServletRequest request,Model model,int page,int limit,Board board){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			PageInfo<Board> info=boardService.selectBoard(board,page,limit);
			map.put("code", 0);
	        map.put("msg", "");
	        map.put("count", info.getTotal());
	        map.put("data", info.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * @Description: 跳转到版块信息添加页面
	 * @param @param board
	 * @param @param request
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月25日
	 */
	@RequestMapping("/form")
	public String form(Board board,HttpServletRequest request,Model model){
		return "views/board/boardAdd";
	}
	/**
	 * @Description: 保存版块添加或者更新保存
	 * @param @param board
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月26日
	 */
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Map<String, Object> save(Board board){
		int status=0;
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			if(board.getBoardId()!=null && !board.getBoardId().equals("")){
				status = boardService.updateNotNull(board);
				map.put("status", String.valueOf(status));
			}else{
				//默认添加板块的时候为不可用
				board.setBoardZt("1");
				status = boardService.save(board);
				map.put("status", String.valueOf(status));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * @Description:跳转到版块弹出页面
	 * @param @param board
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月26日
	 */
	@RequestMapping("/edit")
	public String edit(Board board,Model model){
		board=boardService.queryone(board);
		model.addAttribute("board", board);
		return "views/board/boardAdd";
	}
	/**
	 * @Description:删除版块信息列表
	 * @param @param board
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月26日
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Board board){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			int status=boardService.delete(board.getBoardId());
			map.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * @Description: 设置版块信息为可用或者不可用
	 * @param @param board
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月26日
	 */
	@ResponseBody
	@RequestMapping("/setUse")
	public Map<String, Object> sertUse(Board board){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			board=boardService.queryone(board);
			if(board.getBoardZt().equals("0")){
				board.setBoardZt("1");
			}else{
				board.setBoardZt("0");
			}
			int status=boardService.updateNotNull(board);
			map.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/deleteBatch")
	public String deleteBatch(Model model,String ids){
		String result="0";
		try {
			String[] idarr=ids.split(",");
			for (String id : idarr) {
				if(boardService.delete(Integer.parseInt(id))!=1){
					break;
				}
			}
			result="1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
