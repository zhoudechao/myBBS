package com.mapper.board;

import java.util.List;
import java.util.Map;

import com.model.board.Board;
import tk.mybatis.mapper.common.Mapper;

public interface BoardMapper extends Mapper<Board> {
	public List<Map<String, Object>> selectAllForMap();
}