package com.jumore.jmbi.common.util.export;

import java.net.SocketTimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class HttpUtilForExport {

	private static HttpParams params = new BasicHttpParams();
	static {
		HttpConnectionParams.setConnectionTimeout(params, 5000); // 设置请求超时
		HttpConnectionParams.setSoTimeout(params, 10000); // 设置响应超时
	}

	public static void main(String[] args) {
		// ResultList<InOutCountryBean> result = HttpUtils
		// .doGET("http://192.168.23.142:8080/v1/inout/getInOutCountryAmountExport/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/inout/in",
		// InOutCountryBean.class);

		  JsonNode result = HttpUtilForExport
				.doGetForExport("http://192.168.23.142:8080/v1/inout/getInOutCountryAmountExport/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/inout/in");

		if (null == result) {

		} else {
			// System.out.println("Status" + "||" + result.getStatus().getCode()
			// + "list.size" + "||" + result.getList().size());
//			List<InOutCountryBean> resultList = new ArrayList<InOutCountryBean>();
//			for (Map<String, Object> map : result) {
//				InOutCountryBean bean = new InOutCountryBean();
//				BeanUtil.transMap2Bean(map, bean);
//				resultList.add(bean);
//			}
//
//			for (int i = 0; i < resultList.size(); i++) {
//				InOutCountryBean bean = resultList.get(i);
//				System.out.println(bean.getIndex() + "||"
//						+ bean.getCountryCode() + "||" + bean.getCountryName()
//						+ "||" + bean.getCustomsCode() + "||"
//						+ bean.getTotalAmount());
//			}
		}
	}

	
	/**
	 * 该方法只为导出； 有数据则返回，没有数据
	 * @param url
	 * @return
	 */
	public static JsonNode doGetForExport(String url) {// , List<InOutCountryBean> clazz
		// JSONObject resJSON = new JSONObject();

		HttpClient client = new DefaultHttpClient();
//		ResultList<InOutCountryBean> resultData = new ResultList<InOutCountryBean>();
		try {

			HttpGet request = new HttpGet(url);
			request.setParams(params);
			request.addHeader("Accept", "application/json;charset=UTF-8");
			request.addHeader("Content-Type", "application/json;charset=UTF-8"
					+ ";charset=utf-8");
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			// 处理返回=================================
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, HTTP.UTF_8);
			}else{
				return null;
			}
			
			EntityUtils.consume(entity);
			JsonNode jsonNode = JsonParams.formJsonNode(result);
			if (jsonNode.has("status")) {//在导出的service中，如果发生错误返回的是带有该属性的ResultList<T>对象。正确饭的话则返回的是List<T>
				return null;
			}
//			List<InOutCountryBean> mapList = JsonParams.formJson(jsonNode,
//					new TypeReference<List<InOutCountryBean>>() {
//					});
//			for (int i = 0; i < mapList.size(); i++) {
//				InOutCountryBean bean = mapList.get(i);
//				System.out.println(bean.getIndex() + "||"
//						+ bean.getCountryCode() + "||" + bean.getCountryName()
//						+ "||" + bean.getCustomsCode() + "||"
//						+ bean.getTotalAmount());
//			}
			return jsonNode;
			// ==================================================
			// processServiceERR(resJSON);

		} catch (ConnectTimeoutException e) {
			// processTimeoutExc(resJSON, e);
		} catch (SocketTimeoutException e) {
			// processTimeoutExc(resJSON, e);
		} catch (Exception e) {
			// try {
			// resJSON.put(Constant.ERR_MSG,
			// context.getString(R.string.server_err));
			// System.err.println(ExceptionUtil.getActMessage(e));
			// } catch (JSONException e1) {
			// System.err.println(ExceptionUtil.getActMessage(e));
			// e1.printStackTrace();
			// }
		} finally {
			client.getConnectionManager().shutdown();
		}

		return null;  //
	}
 
	// public static JSONObject doGET(String url) {
	// JSONObject resJSON = new JSONObject();
	//
	// HttpClient client = new DefaultHttpClient();
	// try {
	//
	// HttpGet request = new HttpGet(url);
	// request.setParams(params);
	// request.addHeader("Accept", "application/json");
	// HttpResponse response = client.execute(request);
	// HttpEntity entity = response.getEntity();
	// // 处理返回=================================
	// String result = null;
	// if (entity != null) {
	// result = EntityUtils.toString(entity, "utf-8");
	// }
	// // EntityUtils.consume(entity);
	// JsonNode jsonNode = JsonParams.formJsonNode(result);
	//
	// String code = ServiceConst.SUCCESS_CODE;
	// String message = "";
	// JsonNode status = null;// 请求返回状态对象
	// JsonNode data = null;
	// if (jsonNode.has("status")) {
	// status = jsonNode.get("status");
	// if (status.has("code")) {
	// code = status.get("code").asText();
	// }
	// if (status.has("message")) {
	// message = status.get("message").asText();
	// }
	//
	// }
	// if (jsonNode.has("list")) {
	// data = jsonNode.get("list");//获取返回结果
	// JsonParams.formJson(data, new TypeReference<List<MyBean>>() {});
	// }
	//
	// // ==================================================
	// processServiceERR(resJSON);
	//
	// } catch (ConnectTimeoutException e) {
	// processTimeoutExc(resJSON, e);
	// } catch (SocketTimeoutException e) {
	// processTimeoutExc(resJSON, e);
	// } catch (Exception e) {
	// // try {
	// // resJSON.put(Constant.ERR_MSG,
	// // context.getString(R.string.server_err));
	// // System.err.println(ExceptionUtil.getActMessage(e));
	// // } catch (JSONException e1) {
	// // System.err.println(ExceptionUtil.getActMessage(e));
	// // e1.printStackTrace();
	// // }
	// } finally {
	// client.getConnectionManager().shutdown();
	// }
	// return resJSON;
	// }

	public static JSONObject doPOST(String url, JSONObject sendData) {
		JSONObject resJSON = new JSONObject();
		HttpClient client = new DefaultHttpClient();
		try {

			HttpPost request = new HttpPost(url);
			request.setParams(params);
			request.setEntity(new StringEntity(sendData.toString(), "utf-8"));
			request.addHeader("Accept", "application/json");
			request.setHeader(HTTP.CONTENT_TYPE, "text/json");
			HttpResponse response;
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			// resJSON = new JSONObject(EntityUtils.toString(entity));
			// processServiceERR(resJSON, context);

		} catch (ConnectTimeoutException e) {
			processTimeoutExc(resJSON, e);
		} catch (SocketTimeoutException e) {
			processTimeoutExc(resJSON, e);
		} catch (Exception e) {
			// try {
			// resJSON.put(Constant.ERR_MSG,
			// context.getString(R.string.server_err));
			// System.err.println(ExceptionUtil.getActMessage(e));
			// } catch (JSONException e1) {
			// System.err.println(ExceptionUtil.getActMessage(e));
			// e1.printStackTrace();
			// }
		} finally {
			client.getConnectionManager().shutdown();
		}
		return resJSON;
	}

	public static JSONObject doPut(String url, JSONObject sendData) {
		JSONObject resJSON = new JSONObject();
		HttpClient client = new DefaultHttpClient();
		try {

			HttpPut request = new HttpPut(url);
			request.setParams(params);
			request.setEntity(new StringEntity(sendData.toString(), "utf-8"));
			request.addHeader("Accept", "application/json");
			request.setHeader(HTTP.CONTENT_TYPE, "text/json");
			HttpResponse response;
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			// resJSON = new JSONObject(EntityUtils.toString(entity));
			processServiceERR(resJSON);

		} catch (ConnectTimeoutException e) {
			processTimeoutExc(resJSON, e);
		} catch (SocketTimeoutException e) {
			processTimeoutExc(resJSON, e);
		} catch (Exception e) {
			// try {
			// resJSON.put(Constant.ERR_MSG,
			// context.getString(R.string.server_err));
			// System.err.println(ExceptionUtil.getActMessage(e));
			// } catch (JSONException e1) {
			// System.err.println(ExceptionUtil.getActMessage(e));
			// e1.printStackTrace();
			// }
		} finally {
			client.getConnectionManager().shutdown();
		}
		return resJSON;
	}

	private static void processTimeoutExc(JSONObject resJSON, Exception e) {
		// try {
		// resJSON.put(Constant.ERR_MSG, Constant.CONNECT_OUTTIME);
		// System.err.println(ExceptionUtil.getActMessage(e));
		// } catch (JSONException e1) {
		// System.err.println(ExceptionUtil.getActMessage(e1));
		// e1.printStackTrace();
		// }
	}

	private static void processServiceERR(JSONObject resJSON)
			throws JSONException {
		// 500
		// if
		// (Constant.ERROR_CODE.equals(resJSON.getJSONObject(Constant.SERVICE_KEY).getString(Constant.SERVICE_CODE)))
		// {
		// // resJSON.put(Constant.ERR_MSG, Constant.SERVICE_ERR);
		// resJSON.put(Constant.ERR_MSG,
		// context.getString(R.string.server_err));
		// }
		// 401
		// if
		// (Constant.OTHER_CODE.equals(resJSON.getJSONObject(Constant.SERVICE_KEY).getString(Constant.SERVICE_CODE)))
		// {
		// resJSON.put(Constant.ERR_MSG,
		// resJSON.getJSONObject(Constant.SERVICE_KEY).getString(Constant.SERVICE_MSG));
		// }
	}

//	private static ResponseResult buildResponseResult(String code,
//			String message) {
//		ResponseResult result = new ResponseResult();
//		result.setCode(code);
//		result.setMessage(message);
//		return result;
//	}

}
