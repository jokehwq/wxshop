<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet"
	type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet"
	type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<link href="layui/css/layui.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="layui/layui.js"></script>
<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : true,
					afterBlur : function() {
						this.sync();
					},
					items : [
						'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image','multiimage', 'link','fullscreen']
				});
			});
		</script>
<title></title>

</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		订单发货 <span class="c-gray en">&gt;</span>  <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div id="app">
	<br><br><br><br>
	<div class="pd-20" style="width: 80%">
			
		<div class="row cl">
			<label class="form-label col-2">寄件人信息：</label>
			<div class="formControls col-10">
			<select id="express_dm" class="input-text" style="width: 80%" @change="selectd" v-model="index">
				<option value="-1" >请选择寄件人</option>
				<option  v-for="(iteam, index) in user" :value="index" :key="index">zhangsan1</option>
			</select>
			</div>
		</div>
		<div v-if="index != -1 ">
			<p style="padding-left: 16.66667%;margin-top: 10px;">姓名:张三&#12288;电话:123456</p>
			<p style="padding-left: 16.66667%;margin-top: 5px;">地址:213&nbsp;321312&nbsp;3211231&nbsp;312312&nbsp;3</p>
		</div>
		<br>
			
		<div class="row cl">
			<label class="form-label col-2">收件人信息：</label>
			<div class="formControls col-10">
				<!-- <input type="text" id="express_hm"
					placeholder="请填写快递单号" value="" class="input-text" style="width: 80%"> -->
				<p >姓名:{{buyuser.addr_user}}&#12288;电话:{{buyuser.addr_tel}}</p>
				<p >地址:{{buyuser.addr}} {{buyuser.addr_name}}
			</div>
		</div><br>
		<div class="row cl">
			<label class="form-label col-2">商品信息：</label>
			<div class="formControls col-10">
				<!-- <input type="text" id="express_hm"
					placeholder="请填写快递单号" value="" class="input-text" style="width: 80%"> -->
				<p >名称:${order.goods_name }&#12288;数量:${order.goods_num }</p>
				<p >单价:₫${order.goods_price}&#12288;总价:₫ ${order.goods_total }</p>
			</div>
		</div><br>
			<div id = "show" style="margin-left: 176px">
			
			</div>
			<br>		
				<div class="col-10 col-offset-2">
						<button @click="add" id="butt"
						class="btn btn-primary radius" type="button">
						<i class="Hui-iconfont">&#xe632;</i> 提交
					</button>
					<button onClick="history.go(-1);" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>
				</div>
			</div><br><br>
</div>
<script type="text/javascript" src="js/vue.min.js"></script>
<script type="text/javascript">
	var layer;
	layui.use('layer', function(){
		layer = layui.layer;	
	});
	var netnum = localStorage.getItem('netnum');
	new Vue({
		el: '#app',
		data: {
			user: [],
			index: -1,
			data: {},
			buyuser: {},
			formData: {}
		},
		created: function() {				
			this.show();	
		},
        methods: {
        	add: function(){
        		if(this.index == -1){
        			alert('请选择寄件人');return false;
        		}
        		var that = this;
        		/* 收件人信息 */
        		that.formData.collectname = that.buyuser.addr_user;
        		that.formData.collectphone = that.buyuser.addr_tel;
        		that.formData.collectaddress = that.buyuser.addr_name;
        		that.formData.collectcountryid = that.buyuser.country;
        		that.formData.collectcountry = that.buyuser.addr.split(' ')[0];
        		that.formData.collectproviceid = that.buyuser.province;
        		that.formData.collectprovicename = that.buyuser.addr.split(' ')[1];
        		that.formData.collectcityid = that.buyuser.city;
        		that.formData.collectcityname = that.buyuser.addr.split(' ')[2];
        		that.formData.collectcountyid = that.buyuser.area;
        		that.formData.collectcountyname = that.buyuser.addr.split(' ')[3];
        		/*寄件人信息*/
        		that.formData.sendname = that.data.realname;
        		that.formData.sendtel = that.data.phone;
        		that.formData.sendaddress = that.data.address;
        		that.formData.sendcountryid = that.data.countryid;
        		that.formData.sendcountry = that.data.country;
        		that.formData.sendproviceid = that.data.provinceid;
        		that.formData.sendprovicename = that.data.province;
        		
        		that.formData.sendcityid = that.data.cityid;
        		that.formData.sendcityname = that.data.city;
        		that.formData.sendcountyid = that.data.areaid;
        		that.formData.sendcountyname = that.data.area;
        		
        		that.formData.longitude = that.data.lng;
        		that.formData.latitude = that.data.lat;
        		
        		that.formData.source = '微信商城';
        		that.formData.dispatchnetnum = localStorage.getItem('netnum');
        		that.formData.number = '${order.goods_num }';
        		that.formData.goodstype = '${order.goods_name }';
        		
        		var dd = JSON.stringify(that.formData);
        		var oid = '${order.order_id }';
        		$.ajax({
            		url: 'http://kd.hyuusoft.com/hyuu/api/addNetOrder',
            		type: 'post',
            		data: dd,
            		beforeSend: function(){
        				layer.msg('操作中', {
        				    icon: 16
        				    ,shade: 0.01
        				    ,time: 0
        				});
        			},
            		contentType: 'application/json', 
            		success: function(data){
            			if(data.state){
            				$.ajax({
                        		url: 'updateNetorderid.html',
                        		data: {
                        			order_id: oid,
                        			netorderid: data.data.id
                        		},
                        		success: function(data){
                        			layer.closeAll();
                        			if(data == 1){
                        				layer.alert('提交成功');
                        				window.location.href = document.referrer;
                        			}else{
                        				layer.alert('提交失败，请联系管理员');
                        			}
                        		}
                        	});
            			}else{
            				layer.alert('提交失败，请联系管理员');
            			}
            		}
            	});
        	},
        	selectd: function (){
        		var that = this;
        		if(that.index == -1){
        			that.data = {};
        		}else{
        			that.data = that.user[that.index];
        		}
        	},
            show: function () {
            	var that = this;
            	$.ajax({
            		url: 'http://kd.hyuusoft.com/hyuu/api/getUserData',
            		data: {
            			netnum: netnum,
            			rank: 0,
            			page: 1,
            			limit: 999
            		},
            		success: function(data){
            			console.log(data);
            			that.user = data.data;
            		}
            	});
            	$.ajax({
            		url: 'getOrderAdress.html',
            		data: {
            			addr_id: '${order.addr_id}'
            		},
            		success: function(data){
            			
            			that.buyuser = JSON.parse(data);
            		}
            	});
            	
            	
            }
        }
	});
	
	
	
	
			$(function(){
				var rs = '${result}';
				var data = eval('('+rs+')');
				if(data.Success){
					if(typeof(data.Reason) == "undefined"){
						$.each(data.Traces, function() {
							$('#show').append('<li><i class="node-icon"></i><span class="time">'+this.AcceptTime+'</span><span class="txt">'+this.AcceptStation+'</span></li>');
							
						});
					}else{
						$('#show').append('<li><i class="node-icon"></i><span class="time">'+data.Reason+'</span>');
					}
					
				}else{
					$('#show').append('<li><i class="node-icon"></i><span class="time">查询失败</span>');
				}
			})
			</script>
	<script type="text/javascript">
	function add(){
		var order_id = $('#order_id').val();
		var express_dm = $('#express_dm').val();
		var express_hm = $('#express_hm').val();
		var  express_name=$('#express_dm').find("option:selected").text();
		if(express_dm!=''&&express_hm==''){
			alert("请填写快递单号");
			return ;
		}
		if(express_dm==''){
		  express_name= '';
		}
		$.ajax({
			url:'orderUpstatus.html',
			type:'post',
			data:'order_id='+order_id+'&status=2'+'&express_dm='+express_dm+'&express_hm='+express_hm+'&express_name='+encodeURI(express_name),
			success:function(rs){
				if(rs==1){
					alert("成功！");
					window.location.href = document.referrer;
				}else{
					alert("失败！");
				}
			}
		})
	}
	function upload() {

		var fp = document.getElementById("file").value;
		//为了避免转义反斜杠出问题，这里将对其进行转换

		var re = /(\\+)/g;
		var fn = fp.replace(re, "#");
		//对路径字符串进行剪切截取
		var one = fn.split("#");
		//获取数组中最后一个，即文件名
		var two = one[one.length - 1];
		//再对文件名进行截取，以取得后缀名
		var three = two.split(".");
		//获取截取的最后一个字符串，即为后缀名
		var last = three[three.length - 1];
		last = last.toLowerCase();

		if (last != 'png' && last != 'jpg' && last != 'gif'
				&& last != 'PNG' && last != 'JPG' && last != 'GIF') {
			alert("请上传png、jpg或者gif文件！");
			return;
		}
		$.ajaxFileUpload({
					url : 'upload.html', //需要链接到服务器地址  
					secureuri : false,
					fileElementId : "file", //文件选择框的id属性  
					dataType : 'text', //服务器返回的格式，可以是json  
					success : function(rs) //相当于java中try语句块的用法  
					{	
						if (rs != "") {
							$('#img').html("");
							$('#img').append("<img src='"+rs+"' width='100' height='100'>");
							$('#filepath').val(rs);
						} else {
							alert('失败');
							//document.getElementById("msg"+m[1]).value="失败"; 
						}
					},
					error : function(data, status, e) //相当于java中catch语句块的用法  
					{alert('失败');
						
					}
				});
	}

	</script>	
	
</body>
</html>