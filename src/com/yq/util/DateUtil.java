package com.yq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String dateFormat = "yyyy-MM-dd";
	public static String timeFormat = "yyyy-MM-dd HH:mm:ss";

	public static String getDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date currentTime = new Date();
		return formatter.format(currentTime);
	}

	public static String genQuestNum() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddhhssSSSS");
		Date currentTime = new Date();
		return formatter.format(currentTime);

	}
	/** 
     * ���ڸ�ʽ�ַ�ת����ʱ��� 
     * @param date �ַ����� 
     * @param format �磺yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  

}
