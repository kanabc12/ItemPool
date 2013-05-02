package cn.javass.commons.json.util;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonParser {

	private static ObjectMapper mapper = new ObjectMapper();
	private static JsonParser parser = new JsonParser();

	private JsonParser() {
	}

	public static JsonParser getJsonParser() {
		return parser;
	}

	public static String getJsonString(Object object)
			throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(object);
	}

	public Map<String, Object> readJson2Map(String jsonString) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> maps = mapper.readValue(
					jsonString, Map.class);
			return maps;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
