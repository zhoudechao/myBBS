package com.service.mark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.mark.MarkMapper;
import com.model.mark.Mark;

@Service
@Transactional
public class MarkService{
	@Autowired
	private MarkMapper markMapper;
	
	public PageInfo<Mark> selectMarkByUserId(Integer id, int curr, int limit) {
		PageHelper.startPage(curr, limit);
		List<Mark> list = markMapper.selectMarkByUserId(id);
		return new PageInfo<Mark>(list);
	}

}
