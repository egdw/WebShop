<!DOCTYPE html>
<html lang="zh-Hans">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>注册用户</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="/WebShop/libs/bootstrap/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="/WebShop/css/sign.css" rel="stylesheet">
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
							href="/WebShop/login/signup.jsp">免费注册</a>
					</div>
				</s:if>
				<s:else>
					<div class="log-in">
						<a class="active"><s:property
								value='#session.currentUser.user_username' /></a> <a
							href="/WebShop/SaledGoodListAction?pageCount=0">已发布商品</a> <a
							href="UserGoodAction?pageCount=0">个人中心</a>
						<s:if test="#session.currentUser.manager">
							<a href="UserListAction?pageCount=0" class="">管理中心</a>
						</s:if>
						<a href="LogoutAction">注销</a>
					</div>
				</s:else>
			</div>
		</div>
	</div>
	<!-- #############################这里变了START#############################-->
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
			<li class="active"><a href="/WebShop/IndexAction">首页</a></li>
			<li class=""><a href="GoodListAction?type=0&pageCount=0">二手商城</a></li>
			<li class=""><a href="UserGoodAction?pageCount=0">个人中心</a></li>
			<li class=""><a href="/WebShop/publish.jsp">发布商品</a></li>
		</ul>
	</div>
	<!-- #############################这里变了END#############################-->
	<div class="page">
		<div class="content">
			<div class="container form-wrap">
				<div class="js-alert"></div>
				<!-- #############################这里变了#############################-->
				<h2 class="form-signin-heading">用户注册</h2>

				<div class="form-group">
					<div class="radio">
						<label for="student"><input class="" id="student"
							type="radio" name="usertype" value="0" checked>普通用户</label> <label
							for="normal"><input class="" id="normal" type="radio"
							name="usertype" value="1">在校学生</label>
					</div>

				</div>

				<form class="form-signin" role="form" id="signforNomal"
					action="/WebShop/RegisterAction" method="post">
					<div class="form-group">
						<label for="">用户名</label> <input type="text" name="username"
							class="form-control" placeholder="用户名">
					</div>
					<div class="form-group">
						<label for="">密码</label> <input type="password" id="password"
							name="password" class="form-control" placeholder="密码">
					</div>
					<div class="form-group">
						<label for="">重复密码</label> <input type="password"
							name="confirm_password" class="form-control" placeholder="重复密码">
					</div>
					<button class="btn btn-warning btn-lg btn-block" type="submit">注册</button>
					<button class="btn btn-default btn-lg btn-block" type="reset">重置</button>
				</form>
				<form class="form-signin hidden" id="signforStudent" role="form"
					action="/WebShop/RegisterAction" method="post">
					<div class="form-group">
						<label for="">用户名</label> <input type="text" name="username"
							class="form-control" placeholder="用户名">
					</div>
					<div class="form-group">
						<label for="">密码</label> <input type="password" id="password1"
							name="password" class="form-control" placeholder="密码">
					</div>
					<div class="form-group">
						<label for="">重复密码</label> <input type="password"
							name="confirm_password" class="form-control" placeholder="重复密码">
					</div>
					<div class="form-group">
						<label for="">学号</label> <input type="text" name="sid"
							class="form-control" placeholder="学号">
					</div>
					<button class="btn btn-warning btn-lg btn-block" type="submit">注册</button>
					<button class="btn btn-default btn-lg btn-block" type="reset">重置</button>
				</form>
				<s:actionerror />
				<p class="signTip">
					已有帐号，<a href="./signin.jsp">点击登录</a>
					<!-- #############################这里变了END#############################-->
			</div>
			<!-- /container -->
		</div>
	</div>
	<div class="footer">
		<p>2016 Copyright &copy 大学生 C2C 交易平台</p>
	</div>
	<script src="/WebShop/js/jquery.min.js"></script>
	<script src="/WebShop/libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="/WebShop/js/jquery.validate.min.js"></script>
	<script src="/WebShop/js/additional-methods.js"></script>
	<script src="/WebShop/js/signin.js"></script>
	<!-- #############################这里变了#############################-->
	<script src="/WebShop/js/signup.js"></script>
	<!-- #############################这里变了END#############################-->
</body>
</html>
