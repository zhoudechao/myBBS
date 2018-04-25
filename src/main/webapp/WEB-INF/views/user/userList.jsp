<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户管理</title>
	<meta name="keywords" content="" />
    <meta name="description" content="OneManage Version:2.0" />
	<meta name="Author" content="oneyuanma" />
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="Shortcut Icon" href="/favicon.ico" />
	<!-- load css -->
	<link rel="stylesheet" type="text/css" href="${path }/res/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${path}/res/font-awesome-4.7.0/css/font-awesome.css" />
</head>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row  animated bounceIn">
    	<div class="layui-col-lg12 layui-col-md12 layui-col-sm12 layui-col-xs12">
    		<fieldset class="layui-elem-field layui-field-title site-title">
                <legend><a name="color-design">用户管理</a></legend>
            </fieldset>
            <div class="layui-btn-group" id="one_group">
                <button class="layui-btn"  data-type="add"><i class="layui-icon">&#xe61f;</i><cite>增加用户</cite></button>
                <!-- <button class="layui-btn layui-btn-normal"  data-type="edit"><i class="layui-icon">&#xe642;</i><cite>修改用户</cite></button>
                <button class="layui-btn layui-btn-danger"  data-type="del"><i class="layui-icon">&#xe640;</i><cite>删除用户</cite></button> -->
            </div>
    	</div>

    	<div class="layui-col-lg10 layui-col-md10 layui-col-sm12 layui-col-xs12">
    		<div class="user-tables">
    			<table id="userTables" lay-filter="userTables"></table>
    		</div>
    	</div>
    </div>
</div>
<script type="text/html" id="userbar">
  <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
  {{#  if(d.status === '0'){ }}
     	<a class="layui-btn layui-btn-warm  layui-btn-xs" lay-event="disable"><i class="fa fa-ban"></i>&nbsp;禁用</a>
  {{#  } else { }}
		<a class="layui-btn layui-btn-warm  layui-btn-xs" lay-event="able"><i class="fa fa-circle-o"></i>&nbsp;置为可用</a>
  {{#  } }}
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>
<script type="text/html" id="img">
  {{#  if(d.img === undefined){ }}
      未知
  {{#  } else { }}
		<img id="img" alt="" src="/oneManage{{d.img}}" width="50px" height="50px" />
  {{#  } }}
</script>
<script type="text/html" id="status">
  {{#  if(d.status === undefined){ }}
      未知
  {{#  } else { }}
		 {{#  if(d.status === '0'){ }}
     		 可用
  		{{#  } else { }}
			禁用
  		{{#  } }}
  {{#  } }}
</script>
<!-- 加载js文件 -->
<script type="text/javascript" src="${path }/res/layui/layui.js"></script> 
<script type="text/javascript" src="${path }/res/js/one-js/user.js"></script> 
<script type="text/javascript">
	var path = "${path}";
</script>
</body>
</html>