package com.service.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.model.board.Board;
import com.service.base.BaseService;
@Service
@Transactional
public class BoardService extends BaseService<Board> {
	/**
	 * @Description: 根据JS中传过来的实体类是否有值来进行查询，
	 * @param @param board
	 * @param @param page
	 * @param @param limit
	 * @param @return   
	 * @return PageInfo<Board>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月26日
	 */
	public PageInfo<Board> selectBoard(Board board,int page,int limit){
		PageInfo<Board> info=null;
		if(board.getBoardName() !=null){
			info=this.select(board,page,limit);
		}else{
			info=this.selectAll(page, limit);
		}
		return info;
	}
}
