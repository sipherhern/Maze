package com.shop.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class JacksonUtil {
	public ObjectMapper mapper = new ObjectMapper();
	
	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public void setNullValueFitter()
	{
		// 过滤对象的null属性.
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		// 过滤map中的null值
		mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
	}
	public void setNullValueFitterInArray()
	{
		// 过滤对象的null属性.
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		// 过滤map中的null值
		mapper.configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false);
	}
}
