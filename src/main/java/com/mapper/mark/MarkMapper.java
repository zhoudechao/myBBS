package com.mapper.mark;

import java.util.List;

import com.model.mark.Mark;
import tk.mybatis.mapper.common.Mapper;

public interface MarkMapper extends Mapper<Mark> {
	List<Mark> selectMarkByUserId(int id);
}