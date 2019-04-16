<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'uploadImg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="main/css/H-ui.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="main/layui/css/layui.css">
  </head>
  <style>
  	#file{
  		width: 95% !important;
	  
	    margin: 0 auto;
	    
	    margin-top: 40px;
	    margin-left: 45px;
  	}
  	.layui-btn{
  	    position: relative;
    top: 150px;
    right: 37px;
  	}
  </style>
  <body>
  <div class="formControls col-10">
	<input type="file" id="file" name="file" value="" class="input-text" style="width: 80%" >
  </div>
  <button class="layui-btn" onclick="isOk()">确认</button>
  <script charset="utf-8" src="main/layui/layui.js"></script>
  <script src="http://gosspublic.alicdn.com/aliyun-oss-sdk-4.3.0.min.js"></script>
  <script type="text/javascript" src="main/js/aliyunoss.js"></script>
  <script type="text/javascript">
  var layer;
  layui.use('layer', function(){
	  layer = layui.layer;	
	  
	});
      var imgName = '';
	 //上传图片	
	  document.getElementById('file').addEventListener('change', function (e) {
	  		 var ind = layer.msg('图片上传中', {
	  		  icon: 16
	  		  ,shade: 0.01
	  		  ,time : 1000*10
	  		});
	  		
	  		var filename = e.target.files[0].name;
	  		var endstr =  filename.slice(filename.length-3,filename.length);
	  		
	  		var file = e.target.files[0];

	  		var startTime = new Date().getTime();    

	  		startTime ="Y520"+ startTime + file.name ;

	  		var storeAs = startTime;
	  		
	  		client.multipartUpload(storeAs, file).then(function (result) {
	  			imgName = urlpre_value + startTime;			
	  			layer.msg("上传成功");
	  			layer.close(ind);
	  	 	}).catch(function (err) {
	  	 		layer.msg("上传失败,请重试");
	  		});
	  });
	 function isOk(){
		 if(imgName == ''){
			 parent.insert('');
		 }else{
			 parent.insert('<img src="'+imgName+'" />');
		 }
		 
	 }
  </script>
  </body>
</html>
