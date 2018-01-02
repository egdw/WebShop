<!DOCTYPE html>
<html lang="zh-Hans">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<%
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("/WebShop/login/signin.jsp");
	}
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>个人信息</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="/WebShop/libs/bootstrap/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="/WebShop/css/person.css" rel="stylesheet">
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
							href="/WebShop/person.jsp">个人中心</a>
					</div>
				</s:if>
				<s:else>
					<div class="log-in">
						<a class="active"><s:property
								value='#session.currentUser.user_username' /></a> <a
							href="/WebShop/SaledGoodListAction?pageCount=0">已发布商品</a> <a
							href="UserGoodAction?pageCount=0">个人中心</a>
						<s:if test="#session.currentUser.manager">
							<a href="/WebShop/UserListAction?pageCount=0" class="">管理中心</a>
						</s:if>
					<a href="LogoutAction">注销</a>
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
			<li class="active"><a href="/WebShop/UserGoodAction?pageCount=0">个人中心</a></li>
			<li class=""><a href="/WebShop/publish.jsp">发布商品</a></li>
			<s:if test="#session.currentUser.manager">
				<li class=""><a href="/WebShop/UserListAction?pageCount=0"
					class="">管理中心</a></li>
			</s:if>
		</ul>
	</div>
	<div class="page">
		<div class="content">

			<div class="content person cf">

				<div class="leftside">
					<div class="user-info">
						<div class="user-avatar">
							<s:if test="#session.currentUser.headImage!=null">
								<img src='<s:property value="#session.currentUser.headImage"/>'
									alt="">
							</s:if>
							<s:else>
								<img
									src="https://img.alicdn.com/bao/uploaded/i1/181510260073118980/TB2glCLtFXXXXanXpXXXXXXXXXX_!!0-mytaobao.jpg"
									alt="">
							</s:else>
						</div>
						<a href="#" class="user-nick"><s:property
								value="#session.currentUser.user_username" /></a>
					</div>
					<ul class="navigations">
						<li class="selected"><a href="/WebShop/person-info.jsp">个人信息</a></li>
						<li class=""><a href="/WebShop/UserGoodAction?pageCount=0">正在出售</a></li>
						<li class=""><a
							href="/WebShop/SaledGoodListAction?pageCount=0">已售出的</a></li>
							<li class=""><a href="/WebShop/CompleteGoodListAction?pageCount=0">已买到的</a></li>
						<s:if test="#session.currentUser.manager">
							<li class=""><a href="/WebShop/UserListAction?pageCount=0">管理中心</a></li>
						</s:if>
					</ul>
				</div>
				<div class="main-content">
					<div class="person-info">
						<h2 class="page-header">个人信息维护</h2>
						<form role="form" method="post" action="/WebShop/UserChangeAction"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="">昵称</label> <input type="text" name="goods_name"
									class="form-control" placeholder=""
									value="<s:property value="#session.currentUser.user_username" />"
									readonly="true">
							</div>
							<div class="form-group">
								<label for="">头像</label> <input type="file"
									accept="image/png,image/gif,image/jpeg" name="uploadFile"
									id="fileupload">
								<p class="help-block">请选择图片格式文件上传,支持文件类型（jpg,png,gif）</p>
							</div>
							<div class="form-group">
								<label for="">旧密码</label> <input type="text" name="oldpwd"
									class="form-control" placeholder="留空则不修改密码">
							</div>
							<div class="form-group">
								<label for="">新密码</label> <input type="text" name="newpwd"
									class="form-control" placeholder="">
							</div>
							<div class="form-group">
								<label for="">重复密码</label> <input type="text" name="confirmpwd"
									class="form-control" placeholder="">
							</div>
							<div class="form-group">
								<s:actionerror/>
							</div>
							<button type="submit" class="btn btn-default">提交</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="footer">
		<p>2016 Copyright &copy 浙江省大学生科技创新活动计划暨新苗人才计划2014R453004项目组</p>
	</div>

	<script src="/WebShop/js/jquery.min.js"></script>
	<script src="/WebShop/libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="/WebShop/js/publish.js"></script>
</body>
</html>
