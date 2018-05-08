package com.mapper.type;

import java.util.List;
import java.util.Map;

import com.model.type.Type;
import tk.mybatis.mapper.common.Mapper;

public interface TypeMapper extends Mapper<Type> {
	public List<Map<String, Object>> selectAllForMap();
}