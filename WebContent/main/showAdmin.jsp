<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'showAdmin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link  rel="stylesheet" type="text/css" href="layui/css/layui.css">
  </head>
  
  <body style="padding: 50px;">
    <table id="demo" lay-filter="test"></table>
  </body>
  <script type="text/javascript" src="layui/layui.js"></script>
  <script type="text/javascript">
  layui.use('table', function(){
	  var table = layui.table;
	  //第一个实例
	  table.render({
	    elem: '#demo'
	    ,height: 312
	    ,url: '../admin/showAdmin.html' //数据接口
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'admin_id', title: 'id'}
	      ,{field: 'username', title: '用户名', sort: true}
	      ,{field: 'pwd', title: '密码', sort: true}
	      ,{field: 'netnum', title: '网点编码'} 
	      ,{field: 'remark', title: '备注'}
	    ]]
	  });
  });
  </script>
</html>
