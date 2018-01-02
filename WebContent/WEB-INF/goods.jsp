<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html lang="zh-Hans">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>商品检索</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="libs/bootstrap/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="libs/iconfont/iconfont.css">
<link rel="stylesheet" href="css/goods.css">
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
			<li class=""><a href="/WebShop/IndexAction">首页</a></li>
			<li class="active"><a
				href="/WebShop/GoodListAction?type=0&pageCount=0">二手商城</a></li>
			<li class=""><a href="/WebShop/UserGoodAction?pageCount=0">个人中心</a></li>
			<li class=""><a href="/WebShop/publish.jsp">发布商品</a></li>
			<s:if test="#session.currentUser.manager">
				<li class=""><a href="/WebShop/UserListAction?pageCount=0"
					class="">管理中心</a></li>
			</s:if>
		</ul>
	</div>
	<div class="page cf">
		<div class="goods_left">
			<div class="classify cf">
				<p>
					二手商品:<span><a href="/WebShop/GoodListAction?type=0&pageCount=0"
						target="_blank"> 全部</a></span>
				</p>
				<ul class="classify_in cf">
					<li><a href="/WebShop/GoodListAction?type=1&pageCount=0">闲置数码</a></li>
					<li><a href="/WebShop/GoodListAction?type=2&pageCount=0">家具日用</a></li>
					<li><a href="/WebShop/GoodListAction?type=3&pageCount=0">影音家电</a></li>
					<li><a href="/WebShop/GoodListAction?type=4&pageCount=0">鞋服配饰</a></li>
					<li><a href="/WebShop/GoodListAction?type=5&pageCount=0">闲置图书</a></li>
					<li><a href="/WebShop/GoodListAction?type=6&pageCount=0">手表配饰</a></li>
					<li><a href="/WebShop/GoodListAction?type=7&pageCount=0">其他物品</a></li>
				</ul>
			</div>
			<div class="goods_thro cf">
				<s:iterator value="#request.goods" var="index">
					<div class="goods_thro_in">
						<div class="goods_img">
							<s:if test="#index.firstImage !=null">
								<a
									href="/WebShop/GoodInfoAction?goodId=<s:property value="#index.goodId"/>"><img
									src="<s:property value="#index.firstImage"/>" alt=""
									width="200" height="200"></a>
							</s:if>
							<s:else>
								<a
									href="/WebShop/GoodInfoAction?goodId=<s:property value="#index.goodId"/>"><img
									src="<s:property value="/WebShop/img/noimg.jpg"/>" alt="" width="200"
									height="200"></a>
							</s:else>
						</div>
						<ul>
							<li class="heading_1">¥<s:property value="#index.salePrice" /></li>
							<li class="heading_2"><a
								href="/WebShop/GoodInfoAction?goodId=<s:property value="#index.goodId"/>"><s:property
										value="#index.goodName" /></a></li>
							<li class="heading_3"><a
								href="/WebShop/GoodInfoAction?goodId=<s:property value="#index.goodId"/>"><s:property
										value="#index.goodUser.user_username" /></a></li>
						</ul>
					</div>
				</s:iterator>


			</div>
			<div class="page-nav text-center">
				<nav>
					<ul class="pagination">
						<s:bean name="org.apache.struts2.util.Counter" id="counter">
							<s:param name="first" value="1" />
							<s:param name="last" value="#request.pageNum+1" />
							<s:if test="#request.essential == null">
								<s:iterator>
									<li><a
										href="/WebShop/GoodListAction?type=<s:property value="#request.type"/>&pageCount=<s:property value="current-1-1"/>"><s:property
												value="current-1" /> </a></li>
								</s:iterator>
							</s:if>
							<s:else>
								<s:iterator>
									<li><a
										href="/WebShop/GoodListAction?pageCount=<s:property value="current-1-1"/>&essential=<s:property value="#request.essential"/>"><s:property
												value="current-1" /> </a></li>
								</s:iterator>
							</s:else>
						</s:bean>
					</ul>
				</nav>
			</div>
		</div>


	</div>

	<div class="footer">
		<p>2016 Copyright &copy 浙江省大学生科技创新活动计划暨新苗人才计划2014R453004项目组</p>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="libs/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/show.js"></script>
</body>
</html>
