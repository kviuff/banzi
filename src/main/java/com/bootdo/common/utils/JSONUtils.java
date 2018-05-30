package com.bootdo.common.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.service.impl.JsonValueProcessorImpl;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import java.util.HashMap;
import java.util.Map;

public class JSONUtils {
	/**
	 * Bean对象转JSON
	 * 
	 * @param object
	 * @param dataFormatString
	 * @return
	 */
	public static String beanToJson(Object object, String dataFormatString) {
		if (object != null) {
			if (StringUtils.isEmpty(dataFormatString)) {
				return JSONObject.toJSONString(object);
			}
			return JSON.toJSONStringWithDateFormat(object, dataFormatString);
		} else {
			return null;
		}
	}

	/**
	 * Bean对象转JSON
	 * 
	 * @param object
	 * @return
	 */
	public static String beanToJson(Object object) {
		if (object != null) {
			return JSON.toJSONString(object);
		} else {
			return null;
		}
	}

	/**
	 * String转JSON字符串
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String stringToJsonByFastjson(String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>(16);
		map.put(key, value);
		return beanToJson(map, null);
	}

	/**
	 * 将json字符串转换成对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Object jsonToBean(String json, Object clazz) {
		if (StringUtils.isEmpty(json) || clazz == null) {
			return null;
		}
		return JSON.parseObject(json, clazz.getClass());
	}

	/**
	 * json字符串转map
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		return JSON.parseObject(json, Map.class);
	}

	/**
	 * 设置过滤值为空的属性，使得生成的 json 字符串只包含非空的值
	 *
	 * @return
	 */
	public static JsonConfig getJsonConfig() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonValueProcessorImpl());
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				return value == null;
			}
		});

		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
		jsonConfig.setExcludes(new String[] {"dbName", "isDel" }); // 此处是亮点，只要将所需忽略字段加到数组中即可，在实际测试中，我发现在所返回数组中，存在大量无用属性，

		return jsonConfig;
	}
}
