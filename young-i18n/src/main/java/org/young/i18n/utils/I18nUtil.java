package org.young.i18n.utils;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class I18nUtil {
	
	private I18nUtil() {}
	
	/**
	 * 生成I18nCodes.java
	 * @param propertiesFile i18n/messages_en_US.properties位置
	 * @return
	 * @throws Exception 
	 */
	public static String generateI18nCodesJava(String propertiesFile) throws Exception {
		
		File file = new File("I18nCodes.java");
		propertiesFile = StringUtils.isBlank(propertiesFile) 
				?"i18n/messages_zh_CN.properties" :propertiesFile;
		
		String linesep = "\n";
		String tabstr  = "\t";
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(I18nUtil.class.getClassLoader().getResourceAsStream(propertiesFile), "UTF8");
			Properties prop = new Properties();
			prop.load(reader);
			
			// 写JAVA文件
			StringBuffer content = new StringBuffer();
			content.append("package org.young.template.service;").append(linesep);
			content.append(linesep);
			content.append("/** I18n Message codes **/").append(linesep);
			content.append("public interface I18nCodes {").append(linesep);
			content.append(tabstr).append(linesep);
			
			// codes start
			List<String> keys = new ArrayList<>();
			prop.keySet().forEach(key->keys.add(key.toString()));
			keys.sort((o1, o2)->o1.compareTo(o2));
			
			for(String key :keys) {
				if(!StringUtils.startsWithIgnoreCase(key, "young.")) {
					continue;
				}
				
				content.append(tabstr).append("/** ").append(prop.getProperty(key, ""))
				.append(" **/").append(linesep);
				
				String arr[] = key.split("\\.");
				content.append(tabstr).append("String ").append(arr[arr.length-1]);
				content.append(" = \"");
				content.append(key);
				content.append("\";").append(linesep);
				content.append(tabstr).append(linesep);
			}
			// codes end
			content.append("}").append(linesep);
			
			FileUtils.writeStringToFile(file, content.toString());
		} finally {
			
			IOUtils.closeQuietly(reader);
		}
		
		return file.getAbsolutePath();
	}
	
	public static void main(String[] args) throws Exception {
		generateI18nCodesJava(null);
	}
	
}
