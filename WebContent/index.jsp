<!DOCTYPE html>
<html lang="zh-Hans">
<%@page import="com.web.shop.model.Good"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	ArrayList<Good> lists = (ArrayList<Good>) request
			.getAttribute("newGoodList");
	if (lists == null) {
		response.sendRedirect("IndexAction");
	}
%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>首页</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="libs/bootstrap/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="css/index.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="libs/iconfont/iconfont.css">
</head>

<body>
	<div class="top-nav cf">
		<div class="top-nav-inner">
			<div class="top-nav-l fl"></div>
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
	<div class="header">
		<div class="header-inner cf">
			<div class="header-logo fl"></div>

			<div class="header-search fr">
				<form method="post" action="GoodListAction" name="search">
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
			<li class="active"><a href="IndexAction">首页</a></li>
			<li class=""><a href="GoodListAction?type=0&pageCount=0">二手商城</a></li>
			<li class=""><a href="UserGoodAction?pageCount=0">个人中心</a></li>
			<li class=""><a href="/WebShop/publish.jsp">发布商品</a></li>
			<s:if test="#session.currentUser.manager">
				<li class=""><a href="UserListAction?pageCount=0" class="">管理中心</a></li>
			</s:if>
		</ul>
	</div>
	<!-- #############################这里变了END#############################-->
	<div class="page">
		<div class="content">
			<div class="first-module cf">
				<div class="category">
					<ul class="category-list">
						<li class="list-item">
							<div class="main-cate digit">
								<b></b> <a href="GoodListAction?type=1&pageCount=0"
									target="_blank"> 闲置数码 </a> <span class="main-link"> </span>
							</div>
						</li>
						<li class="list-item">
							<div class="main-cate daily">
								<b></b> <a href="GoodListAction?type=2&pageCount=0"
									target="_blank"> 家具日用 </a> <span class="main-link"> </span>
							</div>
						</li>
						<li class="list-item">
							<div class="main-cate movie">
								<b></b> <a href="GoodListAction?type=3&pageCount=0"
									target="_blank"> 影音家电 </a> <span class="main-link"> </span>
							</div>
						</li>
						<li class="list-item">
							<div class="main-cate shoes">
								<b></b> <a href="GoodListAction?type=4&pageCount=0"
									target="_blank"> 鞋服配饰 </a> <span class="main-link"> </span>
							</div>
						</li>
						<li class="list-item">
							<div class="main-cate watch">
								<b></b> <a href="GoodListAction?type=5&pageCount=0"
									target="_blank"> 手表配饰</a> <span class="main-link"> </span>
							</div>
						</li>
						<li class="list-item">
							<div class="main-cate book">
								<b></b> <a href="GoodListAction?type=6&pageCount=0"
									target="_blank"> 闲置书籍 </a> <span class="main-link"> </span>
							</div>
						</li>
					</ul>
				</div>
				<div class="index-slider">
					<div id="IndexSlider" class="carousel slide" data-ride="carousel">
						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<!-- #############################这里变了#############################-->
							<s:set value="0" var="times"></s:set>
							<s:iterator value="#request.images" var="index">
								<s:if test="#times == 0">
									<div class="item active">
										<img src="<s:property value="#index.bannerImage" />"
											width="1500" alt="...">
										<div class="carousel-caption"></div>
									</div>
									<s:set value="1" var="times"></s:set>
								</s:if>
								<s:else>
									<div class="item">
										<img src="<s:property value="#index.bannerImage" />"
											width="1500" alt="...">
										<div class="carousel-caption"></div>
									</div>
								</s:else>
							</s:iterator>
							<!-- #############################这里变了END#############################-->

						</div>
						<!-- #############################这里变了START#############################-->
						<!--<a class="left carousel-control" href="#IndexSlider" role="button"-->
						<!--data-slide="prev"> <span-->
						<!--class="glyphicon glyphicon-chevron-left"></span> <span-->
						<!--class="sr-only">Previous</span>-->
						</a> <a class="right carousel-control" href="#IndexSlider"
							role="button" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span> <span
							class="sr-only">Next</span>
						</a>
						<!-- #############################这里变了END#############################-->
					</div>
				</div>
			</div>
			<div class="module second-module cf">
				<div class="head-title">
					<h2>全新闲置</h2>
				</div>
				<div class="inner-content">
					<div class="goods-list fl">
						<ul class="item-list cf">
							<s:iterator value="#request.newGoodList" var="index">
								<li class="item">
									<div class="item-pic sh-pic">
										<s:if test="#index.firstImage !=null">
											<a
												href="GoodInfoAction?goodId=<s:property value='#index.goodId' />"><img
												class="J_ItemPic"
												src="<s:property value='#index.firstImage' />"
												title="<s:property
								value='#index.goodName' />"></a>
										</s:if>
										<s:else>
											<a
												href="GoodInfoAction?goodId=<s:property value='#index.goodId' />"><img
												class="J_ItemPic" src="img/noimg.jpg"
												title="<s:property
								value='#index.goodName' />"></a>
										</s:else>
									</div>
									<p class="title">
										<a
											href="GoodInfoAction?goodId=<s:property value='#index.goodId' />"><s:property
												value='#index.goodName' /></a>
									</p>
									<div class="price-block">
										<p class="price">
											<b>¥</b><em><s:property value='#index.salePrice' /></em>
										</p>
									</div>
								</li>
							</s:iterator>
						</ul>
					</div>

				</div>
			</div>
			<div class="module third-module cf">
				<!-- #############################这里变了#############################-->
				<div class="head-title">
					<h2>二手闲置</h2>
				</div>
				<!-- #############################这里变了END#############################-->
				<div class="inner-content">

					<div class="goods-list fl">
						<ul class="item-list cf">
							<s:iterator value="#request.twoHandGoodList" var="index">
								<li class="item">
									<div class="item-pic sh-pic">
										<s:if test="#index.firstImage !=null">
											<a
												href="GoodInfoAction?goodId=<s:property value='#index.goodId' />"><img
												class="J_ItemPic"
												src="<s:property value='#index.firstImage' />"
												title="<s:property
								value='#index.goodName' />"></a>
										</s:if>
										<s:else>
											<a
												href="GoodInfoAction?goodId=<s:property value='#index.goodId' />"><img
												class="J_ItemPic" src="img/noimg.jpg"
												title="<s:property
								value='#index.goodName' />"></a>
										</s:else>
									</div>
									<p class="title">
										<a
											href="GoodInfoAction?goodId=<s:property value='#index.goodId' />"><s:property
												value='#index.goodName' /></a>
									</p>
									<div class="price-block">
										<p class="price">
											<b>¥</b><em><s:property value='#index.salePrice' /></em>
										</p>
									</div>
								</li>
							</s:iterator>
						</ul>
					</div>
				</div>

			</div>
		</div>


	</div>

	<div class="footer">
		<p>2016 Copyright &copy 浙江省大学生科技创新活动计划暨新苗人才计划2014R453004项目组</p>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/index.js"></script>
</body>
</html>
