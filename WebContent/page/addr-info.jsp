<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/shoujisc.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/area.js"></script>
<link rel="stylesheet" href="css/mui.min.css">
<link rel="stylesheet" href="css/mui.picker.min.css">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/module.css">
<script src="js/mui.min.js"></script>
    <script src="js/mui.picker.min.js"></script>
<script type="text/javascript">
$(function(){
	init("province","city","area");
});
</script>
</head>

<body>
	
    <div class="sjsc-title1">
    	<h3 class="sjsc-t1l f-l"><a href="javascript:history.go(-1)"><img src="images/back.png" alt="" style="width:20px;height: 20px;position: relative;top:10px;"/></a></h3>
        <button class="sjsc-btn1 f-r" onclick="add()">保存</button>
        <div style="clear:both;"></div>
    </div>
   <div class="mui-content">
    <div class="bg-fff gj-item">
        <ul class="address-item">
            <li class="flex flex-1 address-edit" style="border: none; height: auto; padding: 0;">
                <div class="flex-1">
                    <div class="table-model mt1" style="margin: 0; border-top:none;">
                        <div class="td-model"><input class="myinput" type="text" id="addr_user" placeholder="姓名" value="${list.addr_user}"></div>
                        <div class="td-model"><input class="myinput" type="text" id="addr_tel" placeholder="手机号或0开头固话" value="${list.addr_tel}"></div>
                    </div>    
                    <div class="flex flex-middle mt2">
                        <div id="showCityPicker0" class="flex-1"><span id="cityResult0"><b>请选择国家</b></span></div>
                        <!-- <a href="javascript:" onclick="getLocation();" class="dingwei"></a> -->
                    </div>                
                    <div class="flex flex-middle mt2">
                        <div id="showCityPicker1" class="flex-1"><span id="cityResult1"><b>请选择省份</b></span></div>
                        <!-- <a href="javascript:" onclick="getLocation();" class="dingwei"></a> -->
                    </div>
                    <div class="flex flex-middle mt2">
                        <div id="showCityPicker2" class="flex-1"><span id="cityResult2"><b>请选择城市</b></span></div>
                        <!-- <a href="javascript:" onclick="getLocation();" class="dingwei"></a> -->
                    </div>
                    <div class="flex flex-middle mt2">
                        <div id="showCityPicker3" class="flex-1"><span id="cityResult3"><b>请选择区域</b></span></div>
                        <!-- <a href="javascript:" onclick="getLocation();" class="dingwei"></a> -->
                    </div>
                    <input id="addr_name" class="mt3 myinput" type="text" placeholder="详细地址" value="${list.addr_name}">
                </div>
            </li>
        </ul>
    </div>
    </div>
    
    <%-- <ul class="xzdz-ul1">
    <c:forEach items="${list}" var="list">
    
    	<li>
        	<p class="xzdz-p1 f-l">收货人</p>
        	<input type="hidden" value="${list.addr_id}" id="addr_id">
            <input type="text" placeholder="姓名" class="xzdz-ipt1 f-l" id="addr_user" value="${list.addr_user}"/>
            <div style="clear:both;"></div>            
        </li>
    	<li>
        	<p class="xzdz-p1 f-l">手机号</p>
            <input type="text" placeholder="11位手机号" class="xzdz-ipt1 f-l" id="addr_tel" value="${list.addr_tel}"/>
            <div style="clear:both;"></div>            
        </li>
    	
    	<li>
        	<p class="xzdz-p1 f-l">详细地址</p><p>
        	<select id="province" name="province"  lang="${list.province }" class="input-text" style="width: 20%"></select>
    <select id="city" name="city" lang="${list.city }"  class="input-text" style="width: 20%"></select>
    <select id="area" name="area" lang="${list.area }" class="input-text" style="width: 20%"></select></p>
            <input type="text" placeholder="具体到街道门牌信息" class="xzdz-ipt1 f-l" id="addr_name"  value="${list.addr_name}"/>
            <div style="clear:both;"></div>            
        </li>
    	 </c:forEach>
    </ul> --%>
    <button class="drdd-btn" onclick="del('${addr_id}')">删除收货地址</button>
    <script type="text/javascript">
    var country = '${list.country}';
    var province = '${list.province}';
    var city = '${list.city}';
    var area = '${list.area}';
	var addrs = '${list.addr}';
	var addrarray = addrs.split(' ');
    var countrytext = addrarray[0];
    var provincetext = addrarray[1];
    var citytext = addrarray[2];
    var areatext = addrarray[3];
    
    
    (function($, doc) {
        $.init();
        $.ready(function() {
            var _getParam = function(obj, param) {
                return obj[param] || '';
            };
            var cityPicker0 = new $.PopPicker({
                layer: 1
            });
            var cityPicker1 = new $.PopPicker({
                layer: 1
            });
            var cityPicker2 = new $.PopPicker({
                layer: 1
            });
            var cityPicker3 = new $.PopPicker({
                layer: 1
            });
            $.ajax({
        		url: 'http://kd.hyuusoft.com/hyuu/allPosition/getAllCountry',
        		data: {},
        		async: true,
        		success: function(data){
        			var str = JSON.stringify(data);
        			str = str.replace(/id/g,'value');
        			str = str.replace(/countryName/g,'text');
        			cityPicker0.setData(JSON.parse(str)); 
        			
        			cityPicker0.pickers[0].setSelectedValue(country);
        			document.getElementById('cityResult0').innerHTML = countrytext;
        			
        			$.ajax({
                		url: 'http://kd.hyuusoft.com/hyuu/allPosition/queryProviceByCountry',
                		data: {'countryId': country},
                		async: false,
                		success: function(data){
                			var str = JSON.stringify(data);
                			str = str.replace(/proviceId/g,'value');
                			str = str.replace(/proviceName/g,'text');
                			cityPicker1.setData(JSON.parse(str)); 
                			
                			cityPicker1.pickers[0].setSelectedValue(province);
                			document.getElementById('cityResult1').innerHTML = provincetext;
                			
                			$.ajax({
                        		url: 'http://kd.hyuusoft.com/hyuu/api/getProvice',
                        		data: {'id': province},
                        		async: false,
                        		success: function(data){
                        			cityPicker2.setData(data);
                        			
                        			cityPicker2.pickers[0].setSelectedValue(city);
                        			document.getElementById('cityResult2').innerHTML = citytext;
                        			
                        			$.ajax({
                                		url: 'http://kd.hyuusoft.com/hyuu/api/getTown',
                                		data: {'id': city},
                                		async: false,
                                		success: function(data){
                                			cityPicker3.setData(data);
                                			
                                			cityPicker3.pickers[0].setSelectedValue(area);
                                			document.getElementById('cityResult3').innerHTML = areatext;
                                		}
                                	});
                        		}
                        	}); 
                			
                			
                		}
                	}); 
        			
        			
        		}
        	}); 
            /* $.ajax({
        		url: 'http://kd.hyuusoft.com/hyuu/api/getCountry',
        		data: {},
        		async: false,
        		success: function(data){
        			cityPicker1.setData(data); 
        		}
        	});  */           
            var showCityPickerButton0 = doc.getElementById('showCityPicker0');                                   
            var showCityPickerButton1 = doc.getElementById('showCityPicker1');
            var showCityPickerButton2 = doc.getElementById('showCityPicker2');
            var showCityPickerButton3 = doc.getElementById('showCityPicker3');
            var cityResult0 = doc.getElementById('cityResult0');
            var cityResult1 = doc.getElementById('cityResult1');
            var cityResult2 = doc.getElementById('cityResult2');
            var cityResult3 = doc.getElementById('cityResult3');   
            
            showCityPickerButton0.addEventListener('tap', function(event) {
            	cityPicker0.show(function(items) {
                    cityResult0.innerText = _getParam(items[0], 'text');
                    country = items[0].value;//zz
                    countrytext = items[0].text;
                    $.ajax({
                		url: 'http://kd.hyuusoft.com/hyuu/allPosition/queryProviceByCountry',
                		data: {'countryId': items[0].value},
                		async: false,
                		success: function(data){
                			var str = JSON.stringify(data);
                			str = str.replace(/proviceId/g,'value');
                			str = str.replace(/proviceName/g,'text');
                			cityPicker1.setData(JSON.parse(str)); 
                		}
                	});                    
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            
            showCityPickerButton1.addEventListener('tap', function(event) {
            	cityPicker1.show(function(items) {
                    cityResult1.innerText = _getParam(items[0], 'text');
                    province = items[0].value;//zz
                    provincetext = items[0].text;
                    $.ajax({
                		url: 'http://kd.hyuusoft.com/hyuu/api/getProvice',
                		data: {'id': items[0].value},
                		async: false,
                		success: function(data){
                			cityPicker2.setData(data);
                		}
                	});                    
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            
            showCityPickerButton2.addEventListener('tap', function(event) {
            	cityPicker2.show(function(items) {
                    cityResult2.innerText = _getParam(items[0], 'text');
                    city = items[0].value;//zz
                    citytext = items[0].text;
                    $.ajax({
                		url: 'http://kd.hyuusoft.com/hyuu/api/getTown',
                		data: {'id': items[0].value},
                		async: false,
                		success: function(data){
                			cityPicker3.setData(data);
                		}
                	});
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            
            showCityPickerButton3.addEventListener('tap', function(event) {
            	cityPicker3.show(function(items) {
                    cityResult3.innerText = _getParam(items[0], 'text');
                    area = items[0].value;//zz
                    areatext = items[0].text;
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
        });
    })(mui, document);
    	
    </script>
    <script type="text/javascript">
			function add() {
				var addr_id = '${list.addr_id}';
				var addr_user =$('#addr_user').val();
				if(addr_user==""){
					showTip("请填写姓名");return;
				}
				var addr_tel =$('#addr_tel').val();
				if(addr_tel==""){
					showTip("请填写手机号码");return;
				}
				if(countrytext==''){
					showTip("请选择国家");return;
				}
				if(provincetext==''){
					showTip("请选择省份");return;
				}
				if(citytext==''){
					showTip("请选择城市");return;
				}
				if(areatext==''){
					showTip("请选择区县");return;
				}
				
				var addr_name =$('#addr_name').val();
				if(addr_name==""){
					showTip("请填写具体收货地址");return;
				}
				var addr = countrytext+' '+provincetext+' '+citytext+' '+areatext;
				$.ajax({
					url : 'addrUpdate.html',
					type : 'post',
					data : 'addr_user=' + addr_user + '&addr_tel=' + addr_tel
					+ '&addr_name=' + addr_name+ '&addr_id=' + addr_id+ '&province=' + province+ '&city=' + city+ '&area=' + area+'&addr='+addr+'&country='+country,
					success : function(rs) {
						if (rs == 1) {
							window.location.href = document.referrer;
						} else {
							alert("系统故障！");
						}
					}
				})
			}
			function del(addr_id){
				var  b = confirm('确定删除？');
				if(!b){
				return ;
				}
				$.ajax({
					url:'addrDel.html',
					type:'post',
					data:'addr_id='+addr_id,
					success:function(rs){
						if(rs==1){
							window.location.href = document.referrer;
						}else{
							alert("失败！");
						}
					}
				})
			}
		</script>
</body>
</html>
