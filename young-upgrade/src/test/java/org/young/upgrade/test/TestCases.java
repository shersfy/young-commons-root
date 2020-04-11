package org.young.upgrade.test;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.young.upgrade.Upgrade;

public class TestCases {
    
    @Test
    public void test01() throws Exception {
    	System.out.println(Upgrade.class.getSimpleName().toLowerCase());
    }
    
    @Test
    public void test02() throws Exception {
    	System.out.println(getDatabaseName("jdbc:mysql://xxx.com:3306/datahub_admin_dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false"));
    }
    
    protected String getDatabaseName(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		
		String[] arr  = url.split("[;|?]");
		String tmp    = arr[0].trim();
		String dbName = tmp.substring(tmp.lastIndexOf("/")+1);
		return dbName;
	}

}
