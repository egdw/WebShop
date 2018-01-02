<!DOCTYPE html>
<html lang="zh-Hans">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>用户登录</title>
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
			<s:if test="#session.currentUser==null">

				<div class="top-nav-r fr">
					<a class="active" href="/WebShop/login/signin.jsp">亲,请登录</a> <a
						href="/WebShop/login/signup.jsp">免费注册</a> <a href="person.jsp">个人中心</a>
				</div>
			</s:if>
			<s:else>
				<div class="top-nav-r fr">
					<a class="active"><s:property
							value='#session.currentUser.user_username' /></a> <a
						href="/WebShop/SaledGoodListAction?pageCount=0">已发布商品</a> <a
						href="/WebShop/UserGoodAction?pageCount=0">个人中心</a>
					<s:if test="#session.currentUser.manager">
						<a href="/WebShop/UserListAction?pageCount=0" class="">管理中心</a>
					</s:if>
					<a href="/WebShop/LogoutAction">注销</a>
				</div>
			</s:else>
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
			<li class="active"><a href="/WebShop/IndexAction">首页</a></li>
			<li class=""><a href="/WebShop/GoodListAction?type=0&pageCount=0">二手商城</a></li>
			<li class=""><a href="UserGoodAction?pageCount=0">个人中心</a></li>
			<li class=""><a href="/WebShop/publish.jsp">发布商品</a></li>
		</ul>
	</div>
	<!-- #############################这里变了END#############################-->
	<div class="page">
		<div class="content">
			<div class="container form-wrap">
				<div class="js-alert"></div>
				<form class="form-signin" id="loginForm" role="form"
					action="/WebShop/LoginAction" method="post">
					<h2 class="form-signin-heading">用户登录</h2>
					<div class="form-group">
						<label for="">用户名</label> <input type="text" name="username"
							class="form-control" placeholder="用户名" required autofocus>
					</div>
					<div class="form-group">
						<label for="">密码</label> <input type="password" name="password"
							class="form-control" placeholder="密码" required>
					</div>
					<s:actionerror />
					<button class="btn btn-warning btn-lg btn-block" type="submit">立即登录</button>
					<button class="btn btn-default btn-lg btn-block"
						data-toggle="modal" data-target="#findPwd">忘记密码</button>
				</form>
				<p class="signTip">
					还没有帐号？<a href="signup.jsp">点击注册</a>
				</p>


				  <!-- 找回密码 -->
            <div class="modal fade" id="findPwd" tabindex="-1" role="dialog" aria-labelledby="forgotPwd" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="forgotPwd">忘记密码</h4>
                        </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <h5>请联系管理员进行重置。</h5>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>
                    </div>
                </div>
            </div>



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
</body>
</html>
