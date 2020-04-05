package org.young.commons.tests;

import java.util.Date;

import org.junit.Test;
import org.young.commons.constant.Const;
import org.young.commons.utils.CamelCaseUtils;
import org.young.commons.utils.DateUtil;

public class TestCases {
    
    @Test
    public void test01() throws Exception {
    	
    }
    
    @Test
    public void test02() throws Exception {
    	Date date = DateUtil.getDateByStr("2018-10-09T05:36:47.000+0000", "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    	System.out.println(DateUtil.format(date, Const.FORMAT_TIMESTAMP));
    }

    @Test
    public void test03() {
    	System.out.println(CamelCaseUtils.toUnderlineString("ISOCertifiedStaff"));
		System.out.println(CamelCaseUtils.toUnderlineString("CertifiedStaff"));
		System.out.println(CamelCaseUtils.toUnderlineString("UserID"));
		System.out.println(CamelCaseUtils.toCamelCaseString("iso_certified_staff", false));
		System.out.println(CamelCaseUtils.toCamelCaseString("certified_staff", false));
		System.out.println(CamelCaseUtils.toCamelCaseString("user_id", false));
		System.out.println(CamelCaseUtils.toCamelCaseString("user_id", true));
    }
    
}
