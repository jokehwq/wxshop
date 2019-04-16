package com.weixin.pay.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;







import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.weixin.entity.WxSetting;
import com.weixin.service.WxSettingService;

import net.sf.json.JSONObject;

public class RefundUtil {

	/**
	 * main函数，实现非部署情况下退款（仅限于全额退款）
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("boolean:" + wechatRefund("IOS7Y32VI99E0E2", 30));// 第一个参数是商户订单号，第二个参数是总金额（double型，以“元”为单位）
		BigDecimal a = new BigDecimal("0.01");
		a = a.multiply(new BigDecimal("100"));
		System.out.println(a.stripTrailingZeros().toPlainString());
	}

	/**
	 * 退款函数，该方法可以对曾经部分退款的订单进行再次退款
	 * 
	 * @param out_trade_no
	 *            商户订单号
	 * @param total_fee1
	 *            退款对应的订单的总金额（以“元”为单位）
	 * @param refund_fee1
	 *            计划退款的金额（以“元”为单位）
	 * @return
	 */
	public static boolean wechatRefund1(String out_trade_no, BigDecimal total_fee1,
			BigDecimal refund_fee1, String netnum) {
		Map<String, Object> m = new HashMap<String, Object>();
		String out_refund_no = UUID.randomUUID().toString().substring(0, 32);// 退款单号，随机生成
																				// ，但长度应该跟文档一样（32位）(卖家信息校验不一致，请核实后再试)
//		int total_fee = (int) (total_fee1 * 100 + 0.5);// 订单的总金额,以分为单位（填错了貌似提示：同一个out_refund_no退款金额要一致）
//		int refund_fee = (int) (refund_fee1 * 100 + 0.5);
		String total_fee = total_fee1.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
		String refund_fee = refund_fee1.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext.xml" });
		WxSettingService wxSettingService = (WxSettingService) ctx
				.getBean("wxSettingService");
		WxSetting w = new WxSetting();
		w.setNetnum(netnum);
		WxSetting wxSetting = wxSettingService.selectByPrimaryKey(w);
//		System.out.println("total_fee============="+total_fee);
//		System.out.println("refund_fee============="+refund_fee);
		
		// 退款金额，以分为单位（填错了貌似提示：同一个out_refund_no退款金额要一致）
		String nonce_str = "3125539068";// 随机字符串
		// 微信公众平台文档：“基本配置”--》“开发者ID”
		String appid = wxSetting.getAppid();
		// 微信公众平台文档：“基本配置”--》“开发者ID”
		String appsecret = wxSetting.getAppsecret();
		// 商户号
		// 微信公众平台文档：“微信支付”--》“商户信息”--》“商户号”，将该值赋值给partner
		String mch_id = wxSetting.getPartner();
		String op_user_id = mch_id;// 就是MCHID
		String refund_account = "REFUND_SOURCE_UNSETTLED_FUNDS";
		// 微信公众平台："微信支付"--》“商户信息”--》“微信支付商户平台”（登录）--》“API安全”--》“API密钥”--“设置密钥”（设置之后的那个值就是partnerkey，32位）
		String partnerkey = wxSetting.getPartnerkey();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		System.out.println(out_trade_no);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", total_fee + "");
		packageParams.put("refund_fee", refund_fee + "");
		packageParams.put("op_user_id", op_user_id);
		packageParams.put("refund_account", refund_account);
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "<out_refund_no>" + out_refund_no + "</out_refund_no>"
				+ "<total_fee>" + total_fee + "</total_fee>" + "<refund_fee>"
				+ refund_fee + "</refund_fee>" + "<op_user_id>" + op_user_id + "</op_user_id>" 
				+ "<refund_account>" + refund_account + "</refund_account>" +
				"</xml>";
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		boolean flg = false;
		try {
			System.out.println("wxSetting.getP12name():"+wxSetting.getP12name());
			String refundResult = ClientCustomSSL.doRefund(createOrderURL, xml, wxSetting.getP12name(), wxSetting.getPartner());

			System.out.println("微信   退款产生的json字符串：" + refundResult);
			String str = JsonKit.xmlToJSON(refundResult, 1);
			JSONObject mg = JSONObject.fromObject(str);
			JSONObject xm = JSONObject.fromObject(mg.get("xml"));
			/*
			 * if(xm.get("total_fee") != null){ flg = true; }
			 */

			Object obj = xm.get("result_code");

			if (obj != null) {
				if (obj.toString().contains("SUCCESS") || obj.toString().contains("success")) {
					flg = true;
				}else{
					flg = false;
				}
//				Object obj_error_code_des = xm.get("error_code_des");
//
//				if (obj_error_code_des != null && obj_error_code_des.toString().contains("已全额退款")) {
//
//					flg = true;
//				}
			}
//			m.put("flg", flg);
//			String msg = flg == true ? "已全额退款" : xm.get("err_code_des")+"";
//			m.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flg;
	}

	/**
	 * 该方法默认全额退款，但如果该订单曾经退款一部分，那么就不可使用该方法
	 * 
	 * @param out_trade_no
	 *            商户订单号
	 * @param total_fee1
	 *            总的退款金额（以“元”为单位）
	 */
//	public static boolean wechatRefund(String out_trade_no, double total_fee1) {
//
//		return wechatRefund1(out_trade_no, total_fee1, total_fee1);
//	}
}
