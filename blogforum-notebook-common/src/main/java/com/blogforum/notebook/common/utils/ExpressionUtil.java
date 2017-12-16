package com.blogforum.notebook.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class ExpressionUtil {

	public static Matcher getMatching(String content,String exp){
		Pattern pattern = Pattern.compile(exp);
		Matcher matcher = pattern.matcher(content);
		return matcher;
		
	}
	
}
