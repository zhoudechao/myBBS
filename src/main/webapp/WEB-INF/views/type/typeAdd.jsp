<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>类型添加弹窗</title>
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
   <legend><a name="methodRender">编辑类型信息</a></legend>
 </fieldset>
<form class="layui-form linksAdd">
	<input type="hidden" name="typeId" value="${type.typeId }">
	<div class="layui-form-item">
		<label class="layui-form-label">类型名称</label>
		<div class="layui-input-block">
			<input type="text" name="typeName" value="${type.typeName }" class="layui-input" lay-verify="required" placeholder="请输入类型名称" />
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">类型描述</label>
		<div class="layui-input-block">
			<input type="text" name="typeDescription" value="${type.typeDescription}" class="layui-input" lay-verify="required" placeholder="请输入类型描述" />
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">类型编号</label>
		<div class="layui-input-block">
			<input type="text" name="typeBh" value="${type.typeBh}" class="layui-input" lay-verify="required" placeholder="请输入类型编号" />
		</div>
	</div>
	<div class="layui-form-item">
		<button class="layui-btn layui-block" lay-filter="addLink" lay-submit>提交</button>
	</div>
</form>
<script type="text/javascript" src="${path}/res/layui/layui.js"></script>
<script type="text/javascript" src="${path}/res/js/bbs/typeList.js"></script>
<script type="text/javascript">
	var path = "${path}";
</script>
</body>
</html>