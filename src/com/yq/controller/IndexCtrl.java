package com.yq.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yq.entity.Area;
import com.yq.entity.Banner;
import com.yq.entity.Cart;
import com.yq.entity.Category;
import com.yq.entity.Goods;
import com.yq.entity.User;
import com.yq.service.AreaService;
import com.yq.service.BannerService;
import com.yq.service.CartService;
import com.yq.service.CategoryService;
import com.yq.service.GoodsService;
import com.yq.service.UserService;
import com.yq.util.StringUtil;

@Controller
@RequestMapping("/")
public class IndexCtrl extends StringUtil {
	@Autowired
	private UserService userService;
	private User user = new User();

	@Autowired
	private AreaService areaService;
	private Area area = new Area();
	@Autowired
	private CartService cartService;
	private Cart cart = new Cart();
	@Autowired
	private GoodsService goodsService;
	private Goods goods = new Goods();

	@Autowired
	private BannerService bannerService;
	private Banner banner = new Banner();

	@Autowired
	private CategoryService categoryService;
	private Category category = new Category();
	Map<String, Object> map = new HashMap<String, Object>();

	@RequestMapping(value = "/main/main.html")
	public ModelAndView mainindex(String netnum, HttpSession session) {
		session.setAttribute("netnum", netnum);
		return new ModelAndView("main/index");
	}

	@RequestMapping(value = "/page/index.html")
	public ModelAndView index(HttpSession session) {
		//测试oppenid
		/*session.setAttribute("netnum","060");
		session.setAttribute("oppen_id", "oSLRh1ux0kADwe4Dm4Vzx5GiS-JA");*/
		ModelAndView ml = new ModelAndView();
//		session.setAttribute("netnum", "001");
		// user.setOppen_id(getOppen_id(session));
		// List<User> userList = userService.listById(user);
		// if(userList.size()>0){
		// System.out.println(userList.get(0).getArea_id());
		// if(userList.get(0).getArea_id()!=null&&userList.get(0).getArea_id()>0){
		String netnum = session.getAttribute("netnum")+"";
		goods.setType(1);
		goods.setStatus(1);
		goods.setNetnum(netnum);
		
		banner.setType(1);
		banner.setStatus(1);
		banner.setNetnum(netnum);
		
		goods.setIs_recommend(1);
		List<Banner> banList = bannerService.list(banner);// 轮动图片
		banner.setType(2);
		List<Banner> advList = bannerService.list(banner);// 轮动图片
		goods.setCtg_id(0);
		List<Goods> hotGoodsList = goodsService.list(goods); // 本周推荐商品
		for (int j = 0; j < hotGoodsList.size(); j++) {
			Goods g = hotGoodsList.get(j);
			String p = String.valueOf(g.getGoods_price());
			DecimalFormat df = new DecimalFormat("#,###");
			String m = df.format(g.getGoods_price());
			int a = Integer.parseInt(p.split("\\.")[1]);
			if(a==0){
				g.setThePrice(p.split("\\.")[0]);
			}else{
				g.setThePrice(p);
			}
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			g.setThePrice(numberFormat.format(Double.parseDouble(g.getThePrice())));
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
				+ hotGoodsList.size());
		category.setStatus(1);
		category.setNetnum(netnum);
		List<Category> ctgList = categoryService.list(category); // 1分类
		for (int i = 0; i < ctgList.size(); i++) {	
			goods.setIs_recommend(0);
			goods.setCtg_id(ctgList.get(i).getCtg_id());
			List<Goods> goodsList = goodsService.list(goods);
			for (int j = 0; j < goodsList.size(); j++) {
				Goods g = goodsList.get(j);
				String p = String.valueOf(g.getGoods_price());
				/*DecimalFormat df = new DecimalFormat("#,###");
				String m = df.format(g.getGoods_price());*/
				int a = Integer.parseInt(p.split("\\.")[1]);
				if(a==0){
					g.setThePrice(p.split("\\.")[0]);
				}else{
					g.setThePrice(p);
				}
				NumberFormat numberFormat = NumberFormat.getNumberInstance();
				g.setThePrice(numberFormat.format(Double.parseDouble(g.getThePrice())));
			}
			map.put("goodsList" + i, goodsList);

		}
		ml.addObject("map", map);
		ml.addObject("ctgList", ctgList);
		ml.addObject("banList", banList);
		ml.addObject("advList", advList);
		ml.addObject("hotGoodsList", hotGoodsList);
		String oppen_id = getOppen_id(session);
		cart.setOppen_id(oppen_id);
		cart.setNetnum(netnum);
		int cart_num = cartService.goodstotalnum(cart);
		session.setAttribute("cart_num", cart_num);

		// ml.addObject("userList",userList); // 用户自提点
		ml.setViewName("page/index");
		// }else{
		// area.setLevel(0);
		// area.setStatus(1);
		// List<Area> areaList =areaService.list(area);
		// for(int i = 0;i < areaList.size(); i++){
		// area.setLevel(areaList.get(i).getArea_id());
		// List<Area> addr =areaService.list(area);
		// map.put("addr"+i, addr);
		// }
		// map.put("areaList",areaList);
		// ml.addObject("map",map);
		// ml.setViewName("page/area");
		// }
		// }else{
		// }
		return ml;
	}

}
