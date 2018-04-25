<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>首页--OneManage后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${path}/res/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${path}/res/css/one-css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="${path}/res/css/one-css/main.css" media="all" />
</head>
<body class="childrenBody">
	<div class="panel_box row">
		<div class="panel col">
			<a href="javascript:;" data-url="page/message/message.html">
				<div class="panel_icon">
					<i class="layui-icon" data-icon="&#xe63a;">&#xe63a;</i>
				</div>
				<div class="panel_word newMessage">
					<span>22</span>
					<cite>新消息</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/user/allUsers.html">
				<div class="panel_icon" style="background-color:#FF5722;">
					<i class="iconfont icon-dongtaifensishu" data-icon="icon-dongtaifensishu"></i>
				</div>
				<div class="panel_word userAll">
					<span>12</span>
					<cite>新增人数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/user/allUsers.html">
				<div class="panel_icon" style="background-color:#009688;">
					<i class="layui-icon" data-icon="&#xe613;">&#xe613;</i>
				</div>
				<div class="panel_word userAll">
					<span>133</span>
					<cite>用户总数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/img/images.html">
				<div class="panel_icon" style="background-color:#5FB878;">
					<i class="layui-icon" data-icon="&#xe64a;">&#xe64a;</i>
				</div>
				<div class="panel_word imgAll">
					<span>23</span>
					<cite>图片总数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/news/newsList.html">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word waitNews">
					<span>324</span>
					<cite>待审核文章</cite>
				</div>
			</a>
		</div>
		<div class="panel col max_panel">
			<a href="javascript:;" data-url="page/news/newsList.html">
				<div class="panel_icon" style="background-color:#2F4056;">
					<i class="iconfont icon-text" data-icon="icon-text"></i>
				</div>
				<div class="panel_word allNews">
					<span>123</span>
					<em>文章总数</em>
					<cite>文章列表</cite>
				</div>
			</a>
		</div>
	</div>
	<div class="row">
		<div class="sysNotice col">
			<blockquote class="layui-elem-quote title">OneManage更新日志</blockquote>
			<div class="layui-elem-quote layui-quote-nm">
				<h3>V2.0.3 - 2018-04-21</h3>
				<p>* 后台代码结构进行了优化，代码更简洁。</p>
				<p>* 系统工具中加入了代码生成器，大大减少了代码编写的工作量，减少了简单的代码复制。</p>
				<p>* 系统设置中增加了数据字典的管理。</p>
				<p>* 加入了Maven版本，管理更方便。</p>
				<p>* 修复了顶级菜单初始化无法点击第一个菜单的bug。</p>
				<br />
				<h3>V2.0.0 - 2018-03-24</h3>
				<p>* 整个框架进行了重构，前端依然是基于方便易用的Layui。</p>
				<p>* 修复了若干个bug，例如友链的批量删除。</p>
				<p>* 系统设置中优化了菜单管理和角色管理的界面，增加了图标选择器。</p>
				<p>* 增加了连接池监控，后续小版本将不断增加对于系统的监控功能。</p>
				<p>* 数据库连接池采用了阿里的 druid，Druid在监控、可扩展性、稳定性和性能方面都有明显的优势,支持并发。</p>
				<p>* 各个方法进行了详细注释。</p>
				<p>* 将在后续小版本中不断丰富系统功能。</p>
				<br />
				<h3>V1.0.0 - 2016-12-31</h3>
				<p>* OneManage首个版本面世。</p>
				<p>* 首个由One源码官方出品的一款基于SpringMVC +Spring +Mybatis +  Layui的通用后台管理系统。</p>
				<p>* 系统具备了用户管理，角色管理，菜单管理等基本功能，可以在此基础上进行二次开发。</p>
				<p>* 首个版本在2017年的最后一天上线，也将在2018年不断的更新版本，希望大家喜欢。</p>
				<br />
				<p>更久以前，在学习并在项目中使用了Layui</p>
			</div>
		</div>
		<div class="sysNotice col">
			<blockquote class="layui-elem-quote title">系统概览</blockquote>
			<table class="layui-table">
				<colgroup>
					<col width="150">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<td>系统名称</td>
						<td class="version">OneManage</td>
					</tr>
					<tr>
						<td>版本信息</td>
						<td class="homePage">OneManage v2.0.3</td>
					</tr>
					<tr>
						<td>开发作者</td>
						<td class="author">one源码</td>
					</tr>
					<tr>
						<td>服务器环境</td>
						<td class="server">Linux或Window + Tomcat 7.0 + MySQL5.7</td>
					</tr>
					<tr>
						<td>官网地址</td>
						<td class="dataBase"><a href="http://www.y-one.cn" target="_blank">http://www.y-one.cn</a></td>
					</tr>
					<tr>
						<td>系统源码下载</td>
						<td class="maxUpload"><a href="http://www.y-one.cn/code/ac4fcacc04224a71b8b49a5e683e88fb.html" target="_blank">OneManage管理系统</a></td>
					</tr>
					<!-- <tr>
						<td>当前用户权限</td>
						<td class="userRights"></td>
					</tr> -->
				</tbody>
			</table>
		</div>
		<div class="sysNotice col">
			<blockquote class="layui-elem-quote title">图表实例</blockquote>
			<div class="layui-elem-quote layui-quote-nm">
				<div id="main" style="width: 500px;height:250px;"></div>
			</div>
		</div>
	</div>
	<script src="${path}/res/layui/layui.js"></script>
	<script src="${path}/res/js/other-js/echarts.js"></script><!-- 图表js -->
	 <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
</html>