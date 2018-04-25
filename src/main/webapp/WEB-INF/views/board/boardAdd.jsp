<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>OneManage后台管理模板 </title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${path}/res/layui/css/layui.css" media="all" >
	<link rel="stylesheet" href="${path}/res/css/public.css" media="all" />
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field layui-field-title site-title">
   <legend><a name="methodRender">编辑版块信息</a></legend>
 </fieldset>
<form class="layui-form linksAdd">
	<input type="hidden" name="boardId" value="${board.boardId }">
	<div class="layui-form-item">
		<label class="layui-form-label">版块名称</label>
		<div class="layui-input-block">
			<input type="text" name="boardName" value="${board.boardName }" class="layui-input" lay-verify="required" placeholder="请输入版块名称" />
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">版块描述</label>
		<div class="layui-input-block">
			<input type="text" name="boardDescription" value="${board.boardDescription }" class="layui-input" lay-verify="required" placeholder="请输入版块描述" />
		</div>
	</div>
	<div class="layui-form-item">
		<button class="layui-btn layui-block" lay-filter="addLink" lay-submit>提交</button>
	</div>
</form>
<script type="text/javascript" src="${path}/res/layui/layui.js"></script>
<script type="text/javascript" src="${path}/res/js/bbs/boardList.js"></script>
<script type="text/javascript">
	var path = "${path}";
</script>
</body>
</html>