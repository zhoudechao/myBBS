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
	public Map<String, Object> boardData(HttpServletRequest request,Model model,int page,int limit){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			PageInfo<Board> info=boardService.selectAll(page,limit);
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
				status = boardService.save(board);
				map.put("status", String.valueOf(status));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
