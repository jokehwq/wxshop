package com.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
















import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.weixin.entity.WxSetting;
import com.weixin.service.WxSettingService;
//import com.wxutil.auth.SHA1;
import com.weixin.util.WXAuthUtil;
import com.yq.dao.UserDao;
import com.yq.entity.Banner;
import com.yq.entity.Cart;
import com.yq.entity.Category;
import com.yq.entity.Goods;
import com.yq.entity.User;
import com.yq.service.BannerService;
import com.yq.service.CartService;
import com.yq.service.CategoryService;
import com.yq.service.GoodsService;
import com.yq.service.UserService;
import com.yq.util.DateUtil;
import com.yq.util.StringUtil;

@Controller
@RequestMapping("/wx")
public class WXLoginController extends StringUtil{
	private static final Logger logger = Logger.getLogger(WXLoginController.class);

	@Autowired
	private GoodsService goodsService;
	private Goods goods = new Goods();
	@Autowired
	private BannerService bannerService;
	private Banner banner = new Banner();
	@Autowired
	private CartService cartService;
	private Cart cart = new Cart();
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	private Category category = new Category();
	Map<String, Object> map = new HashMap<String, Object>();
	
	
	@RequestMapping(value = "/wxLogin.html", method = RequestMethod.GET)
	public String wxLogin(HttpServletRequest request,
	      HttpServletResponse response, String netnum, HttpSession session)
	      throws ParseException, UnsupportedEncodingException {
		System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww:"+netnum);
		session.setAttribute("netnum", netnum);
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext.xml" });
		WxSettingService wxSettingService = (WxSettingService) ctx
				.getBean("wxSettingService");
		WxSetting w = new WxSetting();
		w.setNetnum(netnum);
		WxSetting wxSetting = wxSettingService.selectByPrimaryKey(w);
		String appid = wxSetting.getAppid();
		String appsecret = wxSetting.getAppsecret();
		session.setAttribute("appsecret", appsecret);
		session.setAttribute("appid", appid);
	    //这个url的域名必须要进行再公众号中进行注册验证，这个地址是成功后的回调地址
		
	    String backUrl="http://kd.hyuusoft.com/wxshop/wx/callBack.html";
	    // 第一步：用户同意授权，获取code
	    String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid
	        + "&redirect_uri="+URLEncoder.encode(backUrl, "UTF-8")
	        + "&response_type=code"
	        + "&scope=snsapi_userinfo"
	        + "&state=STATE#wechat_redirect";
	
	    logger.info("forward重定向地址{" + url + "}");
	    //response.sendRedirect(url);
	    return "redirect:"+url;//必须重定向，否则不能成功
	}
	/**
	* 公众号微信登录授权回调函数
	* @param modelMap
	* @param req
	* @param resp
	* @return
	* @throws ServletException
	* @throws IOException
	* @author lbh
	* @date 创建时间：2018年1月18日 下午7:33:53 
	* @parameter
	*/
  @RequestMapping(value = "/callBack.html", method = RequestMethod.GET)
  public String callBack(ModelMap modelMap,HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
    /*
    * start 获取微信用户基本信息
    */
	  String appid = session.getAttribute("appid")+"";
	  String appsecret = session.getAttribute("appsecret")+"";
    String code =req.getParameter("code");
   //第二步：通过code换取网页授权access_token
    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid
        + "&secret="+appsecret
        + "&code="+code
        + "&grant_type=authorization_code";

    System.out.println("url:"+url);
    JSONObject jsonObject = WXAuthUtil.doGetJson(url);
    /*
    { "access_token":"ACCESS_TOKEN",
      "expires_in":7200,
      "refresh_token":"REFRESH_TOKEN",
      "openid":"OPENID",
      "scope":"SCOPE"
     }
    */
    String openid = jsonObject.getString("openid");
    String access_token = jsonObject.getString("access_token");
    String refresh_token = jsonObject.getString("refresh_token");
    //第五步验证access_token是否失效；展示都不需要
    String chickUrl="https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;

    JSONObject chickuserInfo = WXAuthUtil.doGetJson(chickUrl);
    System.out.println(chickuserInfo.toString());
    if(!"0".equals(chickuserInfo.getString("errcode"))){
      // 第三步：刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki，
      String refreshTokenUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+openid+"&grant_type=refresh_token&refresh_token="+refresh_token;

      JSONObject refreshInfo = WXAuthUtil.doGetJson(chickUrl);
      /*
      * { "access_token":"ACCESS_TOKEN",
        "expires_in":7200,
        "refresh_token":"REFRESH_TOKEN",
        "openid":"OPENID",
        "scope":"SCOPE" }
      */
      System.out.println(refreshInfo.toString());
      access_token=refreshInfo.getString("access_token");
    }
   
   // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
   String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
        + "&openid="+openid
        + "&lang=zh_CN";
    System.out.println("infoUrl:"+infoUrl);
    JSONObject userInfo = WXAuthUtil.doGetJson(infoUrl);
    /*
    {  "openid":" OPENID",
      " nickname": NICKNAME,
      "sex":"1",
      "province":"PROVINCE"
      "city":"CITY",
      "country":"COUNTRY",
      "headimgurl":  "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
      "privilege":[ "PRIVILEGE1" "PRIVILEGE2"   ],
      "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
      }
    */
    User user = new User();
    user.setOppen_id(openid);
    user.setNetnum(session.getAttribute("netnum")+"");
    List<User> userList = userService.listById(user);
    Map<String,Object> map = new HashMap<>();
	map.put("oppen_id", userInfo.getString("openid"));
	map.put("realname", userInfo.getString("nickname"));
	map.put("head_img", userInfo.getString("headimgurl"));
	map.put("status", 1);
	map.put("add_time", DateUtil.getDate(DateUtil.timeFormat));
	map.put("netnum", session.getAttribute("netnum"));
    if(userList.size() > 0){//用户已存在
    	userService.update(map);
    }else{
    	userService.insert(map);
    }
    setOppen_id(userInfo.getString("openid"), session);
    System.out.println("JSON-----"+userInfo.toString());
    System.out.println("名字-----"+userInfo.getString("nickname"));
    System.out.println("头像-----"+userInfo.getString("headimgurl"));
    /*
    * end 获取微信用户基本信息
    */
    
    return "redirect:http://kd.hyuusoft.com/wxshop/page/index.html";
  }


}
