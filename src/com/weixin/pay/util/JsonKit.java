package com.weixin.pay.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class JsonKit {
	
//	public static void print(Object object) throws IOException{
//		JSONObject json = JSONObject.fromObject(object);
//		
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		//
//		out.print(json);
//		out.flush();
//		out.close();
//	}
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public static void printflg(Object object) throws IOException{
//		Map m = new HashMap();
//		m.put("flg", object);
//		JsonKit.print(m);
//	}
	public static String getStringMoney(Double dd){
		DecimalFormat df = new DecimalFormat("0.00");				
		return df.format(dd);
	}
	public static String getEndTime(Date date,int days) throws ParseException {
		/*Date date = new Date();//取时间 
		 */
		Calendar calendar  =   Calendar.getInstance();	 
	    calendar.setTime(date); //需要将date数据转移到Calender对象中操作
	    calendar.add(calendar.DATE, days);//把日期往后增加n天.正数往后推,负数往前移动 
	    date=calendar.getTime();   //这
	    Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	    return f.format(date);
	}
	public static long getLongTime(String time) throws ParseException {
		/*	String id = "2017-11-08";*/
			System.out.println(time);
			Format f = new SimpleDateFormat("yyyy-MM-dd");
			Date d = (Date) f.parseObject(time);
			long addtimelong = d.getTime();
			System.out.println(addtimelong);
			return addtimelong;
	}
	public static String xmlToJSON(String xml,int jsonType) {  
        JSONObject obj = new JSONObject();  
        try {  
            InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));  
            SAXBuilder sb = new SAXBuilder();  
            Document doc = sb.build(is);  
            Element root = doc.getRootElement();  
            Map map=iterateElement(root);  
           obj.put(root.getName(),map);  
           return obj.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
	private static Map iterateElement(Element root) {
		List childrenList = root.getChildren();  
        Element element = null;  
        Map map = new HashMap();  
        List list = null;  
        for (int i = 0; i < childrenList.size(); i++) {  
            list = new ArrayList();  
            element = (Element) childrenList.get(i);  
            if(element.getChildren().size()>0){  
                if(root.getChildren(element.getName()).size()>1){  
                    if (map.containsKey(element.getName())) {  
                        list = (List) map.get(element.getName());  
                    }  
                    list.add(iterateElement(element));  
                    map.put(element.getName(), list);  
                }else{  
                    map.put(element.getName(), iterateElement(element));  
                }  
            }else {  
                if(root.getChildren(element.getName()).size()>1){  
                    if (map.containsKey(element.getName())) {  
                        list = (List) map.get(element.getName());  
                    }  
                    list.add(element.getTextTrim());  
                    map.put(element.getName(), list);  
                }else{  
                    map.put(element.getName(), element.getTextTrim());  
                }  
            }  
        }  
          
        return map;
	}
	public static void main(String[] args) {
		 String xml = "<xml>"
		 		     +"<total_fee>1</total_fee>"
				     +"<cash_fee>1</cash_fee>"
				     + "<refund_id><![CDATA[50000305232017121402684582923]]></refund_id>"
				     +"</xml>";
		 String str = xmlToJSON(xml,1);  
		 JSONObject mg = JSONObject.fromObject(str);
		 JSONObject xm = JSONObject.fromObject(mg.get("xml"));
	     System.out.println(xm.get("total_fee") != null);
	}
}
