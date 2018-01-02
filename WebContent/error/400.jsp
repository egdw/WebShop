<!DOCTYPE html>
<html lang="zh-Hans">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>400!</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="/WebShop/libs/bootstrap/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="/WebShop/css/index.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="/WebShop/libs/iconfont/iconfont.css">
</head>

<body>
	<div class="top-nav cf">
		<div class="top-nav-inner">
			<div class="top-nav-l fl">大学生 C2C 交易平台</div>
			<div class="top-nav-r fr">
				<s:if test="#session.currentUser==null">
					<div class="not-login">
						<a class="active" href="/WebShop/login/signin.jsp">亲,请登录</a> <a
							href="/WebShop/login/signup.jsp">免费注册</a> <a
							href="/WebShop/UserGoodAction?pageCount=0">个人中心</a>
					</div>
				</s:if>
				<s:else>
					<div class="log-in">
						<a class="active"><s:property
								value='#session.currentUser.user_username' /></a> <a
							href="/WebShop/person-sellout.jsp">已发布商品</a> <a
							href="/WebShop/UserGoodAction?pageCount=0">个人中心</a>
						<s:if test="#session.currentUser.manager">
							<a href="/WebShop/UserListAction?pageCount=0" class="">管理中心</a>
						</s:if>
					</div>
				</s:else>
			</div>
		</div>
	</div>
	<div class="header">
		<div class="header-inner cf">
			<div class="header-logo fl"></div>

			<div class="header-search fr">
				<form method="post" action="/WebShop/GoodListAction" name="search">
					<input class="input-search" id="HeaderSearchQuery" name="essential"
						type="text" value="" placeholder="搜宝贝" autocomplete="off">
					<button class="btn-search" type="submit">
						<i class="iconfont icon-sousuo"></i><span class="search-img"></span>
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="header-nav">
		<ul class="header-menu cf">
			<li class=""><a href="/WebShop/IndexAction">首页</a></li>
			<li class=""><a
				href="/WebShop/GoodListAction?type=0&pageCount=0">二手商城</a></li>
			<li class=""><a href="/WebShop/UserGoodAction?pageCount=0">个人中心</a></li>
			<li class=""><a href="/WebShop/publish.jsp">发布商品</a></li>
			<s:if test="#session.currentUser.manager">
				<li class=""><a href="/WebShop/UserListAction?pageCount=0"
					class="">管理中心</a></li>
			</s:if>
		</ul>
	</div>
	<div class="page">
		<div class="content">
			<div style="width: 1222px; margin: 80px auto;">
				<a href="#"> <img src="/WebShop/img/400.jpg" style="width: 100%">
				</a>
			</div>
		</div>
	</div>

	<div class="footer">
		<p>2016 Copyright &copy 浙江省大学生科技创新活动计划暨新苗人才计划2014R453004项目组</p>
	</div>

	<script src="/WebShop/js/jquery.min.js"></script>
	<script src="/WebShop/libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="/WebShop/js/index.js"></script>
</body>
</html>
