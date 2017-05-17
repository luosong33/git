package com.jumore.jmbi.common.util.export;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * JSON工具类，采用.JsonBinder处理JSON
 * @author 
 *
 */
public class JsonParams {
  private static final Logger logger = LoggerFactory.getLogger(JsonParams.class);

  private static JsonBinder binder = JsonBinder.getNonNull();

  public synchronized static <T> T formJson(String jsonString, Class<T> clazz) {
    try {
      return binder.getMapper().readValue(formJsonNode(jsonString), clazz);
    } catch (Exception e) {
      logger.error("JSON参数解析错误！", e);
      return null;
    }
  }

  public synchronized static <T> T formJson(JsonNode jsonRequest, Class<T> clazz) {
    try {
      return binder.getMapper().readValue(jsonRequest, clazz);
    } catch (Exception e) {
      logger.error("JSON参数解析错误！", e);
      return null;
    }
  }

  public synchronized static JsonNode formJsonNode(String jsonString) {
    try {
      return binder.getMapper().readTree(jsonString);
    } catch (Exception e) {
      logger.error("JSON参数解析错误！", e);
      return null;
    }
  }

  public static <T> T formJson(JsonNode jsonNode, TypeReference<T> t) {
    return binder.fromJson(jsonNode, t);

  }

  public static String toJson(Object object) {
    return binder.toJson(object);
  }

  public static ObjectMapper getMapper() {
    return binder.getMapper();
  }

}
