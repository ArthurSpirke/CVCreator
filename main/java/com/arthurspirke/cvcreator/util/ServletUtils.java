package com.arthurspirke.cvcreator.util;

import javax.servlet.http.HttpServletResponse;

public class ServletUtils {

	public static void setHeaders(HttpServletResponse response, String contentType){
		response.setCharacterEncoding("UTF-8");
		response.setContentType(contentType);
	}
}
