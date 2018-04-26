package com.service.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.model.board.Board;
import com.service.base.BaseService;
@Service
@Transactional
public class BoardService extends BaseService<Board> {
	public PageInfo<Board> selectBoard(Board board,int page,int limit){
		PageInfo<Board> info=null;
		if(board.getBoardName() !=null && !board.getBoardName().equals("")){
			info=this.select(board,page,limit);
		}else{
			info=this.selectAll(page, limit);
		}
		return info;
	}
}
