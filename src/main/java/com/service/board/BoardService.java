package com.service.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.mapper.board.BoardMapper;
import com.model.board.Board;
import com.service.base.BaseService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;
@Service
@Transactional
public class BoardService extends BaseService<Board> {
	@Autowired
	private BoardMapper boardMapper;
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
		Example example=new Example(Board.class);
		Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(board.getBoardName())){
			String username="%"+board.getBoardName()+"%";
			criteria.andLike("boardName", username);
		}
		PageInfo<Board> info =this.selectByExample(page,limit,example);
		return info;
		/*PageInfo<Board> info=null;
		if(board.getBoardName() !=null){
			info=this.select(board,page,limit);
		}else{
			info=this.selectAll(page, limit);
		}
		return info;*/
	}
	
	public List<Map<String, Object>> selectAllForMap(){
		 return boardMapper.selectAllForMap();
	}
}
