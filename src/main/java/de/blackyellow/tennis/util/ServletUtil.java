package de.blackyellow.tennis.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServletUtil {

	/**
	 * 
	 * @param req
	 * @param param
	 * @return liefert den angegebenen Parameter (falls vorhanden) in utf-8
	 * @throws UnsupportedEncodingException
	 */
	public static String getParameter(HttpServletRequest req, String param) throws UnsupportedEncodingException {
		String parameter = req.getParameter(param);
		if(parameter != null)
		{
			return new String(parameter.getBytes("ISO-8859-1"),"UTF-8");
		}
		return null;
	}
	
	/**
	 * Erzeugt aus dem übergebenen Parameter ein Objekt vom angegebenen Typ.
	 * @param modellParam
	 * @param type
	 * @return aus Json generiertes Objekt
	 */
	public static <T> T createObjectFromJson(String modellParam, Class<T> type) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		T fromJson = gson.fromJson(modellParam, type);
		return fromJson;
	}
	
	public static void prepareResponse(HttpServletResponse response)
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "http://tennis.blackyellow.de");
	}
}
