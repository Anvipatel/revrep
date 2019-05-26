package com.org.revrep.backend.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ObjectMapperUtil {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static <T> T toObject(String jsonObject, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonObject, clazz);
	}

}
