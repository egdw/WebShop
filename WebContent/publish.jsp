<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html lang="zh-Hans">
<%
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("/WebShop/login/signin.jsp");
	}
%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>发布商品</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="libs/bootstrap/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="css/publish.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="libs/iconfont/iconfont.css">
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
							href="UserGoodAction?pageCount=0">个人中心</a>
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
			<li class="active"><a href="/WebShop/publish.jsp">发布商品</a></li>
			<s:if test="#session.currentUser.manager">
				<li class=""><a href="/WebShop/UserListAction?pageCount=0"
					class="">管理中心</a></li>
			</s:if>
		</ul>
	</div>
	<div class="page">
		<div class="content publish">
			<h2 class="page-header">发布商品</h2>
			<div class="publish-main">
				<form role="form" method="post" action="PublishGoodAction"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="">商品名称</label> <input type="text" class="form-control"
							placeholder="请输入商品名称" name="goodName" required="required"
							maxlength="17">
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="">原价</label> <input type="number"
									name="source_price" class="form-control" placeholder=""
									required="required" max="999999" maxlength="6">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="">卖价</label> <input type="number" name="goods_price"
									class="form-control" placeholder="" required="required"
									max="999999" maxlength="6">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="">商品分类</label> <select class="form-control"
							name="types">
							<option value="1">闲置数码</option>
							<option value="2">家具日用</option>
							<option value="3">影音家电</option>
							<option value="4">鞋服配饰</option>
							<option value="5">闲置图书</option>
							<option value="6">手表配饰</option>
							<option value="7">其他物品</option>
						</select>
					</div>
					<div class="form-group">
						<label for="">商品类型</label>
						<div>
							<label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" value="new">全新
							</label> <label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" value="second" checked>二手
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="">联系方式</label> <input type="number" name="contact"
							class="form-control" placeholder="请输入您的联系方式" required="required"
							maxlength="11">
					</div>
					<div class="form-group">
						<label for="">商品图片</label> <input type="file"
							accept="image/png,image/gif,image/jpeg" name="uploadFile"
							multiple="multiple">
						<p class="help-block">请选择图片格式文件上传,支持文件类型（jpg,png,gif）</p>
					</div>
					 <s:token /> 
					<div class="form-group">
						<label for="">商品描述</label>
						<textarea name="textContext" class="form-control" rows="3"
							placeholder="请在此输入相应的商品描述" required="required" maxlength="250"></textarea>
					</div>
					<s:actionerror/>
					<button type="submit" class="btn btn-default">提交</button>
				</form>
			</div>
		</div>
	</div>

	<div class="footer">
	<p>2016 Copyright &copy 浙江省大学生科技创新活动计划暨新苗人才计划2014R453004项目组</p>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/publish.js"></script>
</body>
</html>
