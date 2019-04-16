<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/chihaodian/main/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<title>基本设置</title>

</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i>首页 <span class="c-gray en">&gt;</span>
		添加账户 <span class="c-gray en">&gt;</span>  <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<br><br>
	<div class="pd-20" style="width: 80%">
			
			<div class="row cl">
			
						<label class="form-label col-2">账号：</label>
						<div class="formControls col-10">
							<input type="text" id="username"
								placeholder="请填写" value="" class="input-text" style="width: 80%" >
						</div>
			</div><br>
			
			<div class="row cl">
			
						<label class="form-label col-2">密码：</label>
						<div class="formControls col-10">
							<input type="text" id="password"
								placeholder="请填写" value="" class="input-text" style="width: 80%">
						</div>
			</div><br>
			<div class="row cl">
			
						<label class="form-label col-2">appid：</label>
						<div class="formControls col-10">
							<input type="text" id="appid"
								placeholder="请填写" value="" class="input-text" style="width: 80%">
						</div>
			</div><br>
			<div class="row cl">
			
						<label class="form-label col-2">appsecret：</label>
						<div class="formControls col-10">
							<input type="text" id="appsecret"
								placeholder="请填写" value="" class="input-text" style="width: 80%">
						</div>
			</div><br>
			<div class="row cl">
			
						<label class="form-label col-2">partner：</label>
						<div class="formControls col-10">
							<input type="text" id="partner"
								placeholder="请填写" value="" class="input-text" style="width: 80%">
						</div>
			</div><br>
			<div class="row cl">
			
						<label class="form-label col-2">partnerkey：</label>
						<div class="formControls col-10">
							<input type="text" id="partnerkey"
								placeholder="请填写" value="" class="input-text" style="width: 80%">
						</div>
			</div><br>
			<div class="row cl">
			
						<label class="form-label col-2">证书名称：</label>
						<div class="formControls col-10">
							<input type="text" id="p12name"
								placeholder="请填写" value="" class="input-text" style="width: 80%">
						</div>
			</div><br>
			<div class="row cl">
			
						<label class="form-label col-2">火云网点编码：</label>
						<div class="formControls col-10">
							<input type="text" id="netnum"
								placeholder="请填写" value="" class="input-text" style="width: 80%">
						</div>
			</div><br>
			<div class="row cl">
			
						<label class="form-label col-2">备注：</label>
						<div class="formControls col-10">
							<input type="text" id="remark"
								placeholder="请填写" value="" class="input-text" style="width: 80%">
						</div>
			</div><br>
				<div class="col-10 col-offset-2">
				
						<button onClick="add()" id="butt"
						class="btn btn-primary radius" type="button">
						<i class="Hui-iconfont">&#xe632;</i> 提交
					</button>
					<button onClick="history.go(-1);" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>
				</div>
			</div>
	<script type="text/javascript">
	function add(){
		
		var username = $('#username').val();
		var password = $('#password').val();
		var appid = $('#appid').val();
		var appsecret = $('#appsecret').val();
		var partner = $('#partner').val();
		var partnerkey = $('#partnerkey').val();
		var netnum = $('#netnum').val();
		if(username == '' || password == '' || appid == '' || appsecret == '' || partner == '' || partnerkey == '' || netnum == ''){
			alert('请填写必要信息'); return;
		}
		$.ajax({
			url:'addAdmin.html',
			type:'post',
			data:'username='+username+'&password='+password+'&appid='+appid+'&appsecret='+appsecret+'&partner='+partner+'&partnerkey='+partnerkey+'&netnum='+netnum+'&remark='+$('#remark').val()+'&p12name='+$('#p12name').val(),
			success:function(rs){
				alert(rs);
				/* if(rs==1){
					alert("成功！");
					window.location.href = document.referrer;
				}else{
				
					alert("旧密码错误！");
				} */
			}
		})
	}

	</script>	
	
</body>
</html>