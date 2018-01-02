<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.web.shop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html lang="zh-Hans">
<%
	User user = (User) session.getAttribute("currentUser");
	if (user == null) {
		response.sendRedirect("/WebShop/login/signin.jsp");
	} else if (!user.isManager()) {
		response.sendRedirect("/WebShop/person.jsp");
	}
	ArrayList<User> users = (ArrayList<User>) request
			.getAttribute("users");
	if (users == null) {
	}
%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>管理中心</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="libs/bootstrap/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="css/person.css" rel="stylesheet">
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
							href="/WebShop/UserGoodAction?pageCount=0">个人中心</a>
					</div>
				</s:if>
				<s:else>
					<div class="log-in">
						<a class="active"><s:property
								value='#session.currentUser.user_username' /></a> <a
							href="/WebShop/SaledGoodListAction?pageCount=0">已发布商品</a> <a
							href="/WebShop/UserGoodAction?pageCount=0">个人中心</a> <a
							href="/WebShop/UserListAction?pageCount=0" class="">管理中心</a> <a
							href="/WebShop/LogoutAction">注销</a>
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
				<li class="active"><a
					href="/WebShop/UserListAction?pageCount=0" class="">管理中心</a></li>
			</s:if>
		</ul>
	</div>
	<div class="page">
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
					<li class=""><a href="/WebShop/person-info.jsp">个人信息</a></li>
					<li class=""><a href="/WebShop/UserGoodAction?pageCount=0">正在出售</a></li>
					<li class=""><a
						href="/WebShop/SaledGoodListAction?pageCount=0">已售出的</a></li>
					<li class=""><a
						href="/WebShop/CompleteGoodListAction?pageCount=0">已买到的</a></li>
					<li class="selected"><a
						href="/WebShop/UserListAction?pageCount=0">管理中心</a></li>
				</ul>
			</div>
			<div class="main-content">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#user"
						role="tab" data-toggle="tab">用户管理</a></li>
					<li role="presentation" class=""><a href="#student" role="tab"
						data-toggle="tab">学生管理</a></li>
					<li role="presentation"><a href="#index" role="tab"
						data-toggle="tab">首页Banner管理</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="user">
						<div class="user-list">
							<div class="filter">
								<form method="post" action="UserListAction" name="filteuser">
									<div class="row">
										<div class="col-md-6">
											<select class="form-control" name="groupType">
												<option value="0">全部用户</option>
												<option value="1">普通用户</option>
												<option value="2">管理员</option>
												<option value="3">待审核</option>
											</select>
										</div>

										<div class="col-md-6">
											<button class="btn btn-default" type="submit">筛选</button>
										</div>
									</div>
								</form>
							</div>
							<table class="table table-striped text-center">
								<thead>
									<tr>
										<th>#</th>
										<th>用户名</th>
										<th>注册时间</th>
										<th>用户状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="#request.users" var="index">
										<tr>
											<td class="text-center"><s:property value="#index.user_id" /></td>
											<td><s:property value="#index.user_username" /></td>
											<td><s:property value="#index.register_date" /></td>
											<s:if test="#index.verify">
												<s:if test="#index.manager">
													<td><span class="label label-danger">管理员</span></td>
												</s:if>
												<s:else>
													<td><span class="label label-success">普通用户</span></td>
												</s:else>
											</s:if>
											<s:else>
												<td><span class="label label-default">待审核</span></td>
											</s:else>
											<td><s:if test="#index.denied">
													<form style="float: left;" action="UserManagerAction" >
														<input type="hidden" name="userId"
															value="<s:property value="#index.user_id"/>"> <input
															type="hidden" name="type" value="setDined">
														<button type="submit"
															class="btn btn-warning btn-xs js-band">解禁</button>
													</form>
												</s:if> <s:else>
													<form style="float: left;" action="UserManagerAction">
														<input type="hidden" name="userId"
															value="<s:property value="#index.user_id"/>"> <input
															type="hidden" name="type" value="setDined">
														<button type="submit"
															class="btn btn-warning btn-xs js-band">封禁</button>
													</form>
												</s:else>
												<form style="float: left;" action="UserManagerAction"
													method="post">
													<input type="hidden" name="userId"
														value="<s:property value="#index.user_id"/>"> <input
														type="hidden" name="type" value="delUser">
													<button type="submit" class="btn btn-danger btn-xs js-del">删除</button>
												</form> <s:if test="#index.verify">
												</s:if> <s:else>
													<form style="float: left;" action="UserManagerAction"
														method="post">
														<input type="hidden" name="userId"
															value="<s:property value="#index.user_id"/>"> <input
															type="hidden" name="type" value="setVerify">
														<button type="submit" class="btn btn-success btn-xs">审核</button>
													</form>
												</s:else> <s:if test="#index.manager">
													<form style="float: left;" action="UserManagerAction"
														method="post">
														<input type="hidden" name="userId"
															value="<s:property value="#index.user_id"/>"> <input
															type="hidden" name="type" value="setManager">
														<button type="submit" class="btn btn-primary btn-xs">取消管理员</button>
													</form>
												</s:if> <s:else>
													<form style="float: left;" action="UserManagerAction"
														method="post">
														<input type="hidden" name="userId"
															value="<s:property value="#index.user_id"/>"> <input
															type="hidden" name="type" value="setManager">
														<button type="submit" class="btn btn-primary btn-xs">设为管理员</button>
													</form>
												</s:else>
												<form action="ResetPasswordAction" style="float: left;">
													<input type="hidden" name="userId" value="<s:property value="#index.user_id"/>">
													<button type="submit" class="btn btn-primary btn-xs">重置密码</button>
												</form>
												<code><s:property value="#session.newPasswd"/></code></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							<div class="page-nav">
								<nav>
									<ul class="pagination">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="#request.pageNum+1" />
											<s:iterator>
												<li><a
													href="/WebShop/UserListAction?pageCount=<s:property value="current-1-1"/>"><s:property
															value="current-1" /> </a></li>
											</s:iterator>
										</s:bean>
									</ul>
								</nav>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="index">
						<h3 class="page-header">管理图片</h3>
						<table class="table table-striped  text-center">
							<thead>
								<tr>
									<th>#</th>
									<th>图片</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.images" var="index">
									<tr>
										<td><s:property value="#index.bannderId" /></td>
										<td><img src="<s:property value="#index.bannerImage"/>"
											alt="" height="40"></td>
										<td>
											<form action="BannerDelAction">
												<input type="hidden"
													value="<s:property value="#index.bannderId" />"
													name="bannerId">
												<button type="submit" class="btn btn-danger btn-xs">删除</button>
											</form>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>

						<div class="upload-banner">
							<h3 class="page-header">上传图片</h3>
							<form role="form" method="post" enctype="multipart/form-data"
								action="BannerAction">
								<div class="form-group">
									<input type="file" accept="image/png,image/gif,image/jpeg"
										name="uploadFile" id="fileupload" multiple="multiple"
										required="required">
									<p class="help-block">请选择图片格式文件上传,支持文件类型（jpg,png,gif）</p>
								</div>
								<button type="submit" class="btn btn-primary">上传图片</button>
							</form>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="student">
						<h3 class="page-header">学生管理</h3>
						<form class="form-inline" role="form" method="post"
							name="searchStudent" id="searchStudent">
							<div class="form-group">
								<div class="input-group">
									<select class="form-control" name="searchType">
										<option value="1">按学号搜索</option>
										<option value="2">按姓名搜索</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<input class="form-control" type="text" name="searchValue">
							</div>
							<button type="submit" class="btn btn-default" id="submitSearch">搜索</button>
						</form>



						<table class="table table-striped  text-center">
							<thead>
								<tr>
									<th>#</th>
									<th>学号</th>
									<th>姓名</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="studentList">

							</tbody>
						</table>

						<div class="upload-banner">
							<h3 class="page-header">添加学生</h3>
							<form class="form" role="form" method="post" id="addStudent"
								name="addStudent">
								<table class="table">
									<thead>
										<tr>
											<th>学号</th>
											<th>姓名</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody id="list">
										<tr>
											<td><input class="form-control input-sm" type="text"
												name="ids" id="id-1" placeholder="输入学号"></td>
											<td><input class="form-control input-sm" type="text"
												name="names" id="name-1" placeholder="输入姓名"></td>
											<td>
												<button type="button" class="btn btn-danger btn-xs">删除</button>
											</td>
										</tr>
									</tbody>
								</table>
								<div class="filter">
									<button type="button" class="btn btn-primary " id="addNewItem">新增</button>
									<button type="submit" class="btn btn-success " id="submitItems">提交</button>
									<button type="button" class="btn btn-info" data-toggle="modal"
										data-target="#uploadXls">批量导入</button>
								</div>
							</form>
						</div>
					</div>

				</div>
			</div>
			<div class="modal fade" id="uploadXls" tabindex="-1" role="dialog"
				aria-labelledby="xls" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="xls">批量导入</h4>
						</div>
						<form method="post" enctype="multipart/form-data"
							action="BatchAddStudentAction">
							<div class="modal-body">
								<h4>导入注意事项：</h4>
								<p class="text-danger">Excel表格为2列，分别为学号、姓名。</p>
								<div class="form-group">
									<h4>选择文件</h4>
									<input type="file" accept="" name="upload" id="xlsUploader">
									<p class="help-block">请选择Excel格式文件上传,支持文件类型（xls）</p>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="submit" class="btn btn-primary">确认提交</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>


	</div>
	</div>

	<div class="footer">
		<p>2016 Copyright &copy 浙江省大学生科技创新活动计划暨新苗人才计划2014R453004项目组</p>
		<s:debug></s:debug>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/additional-methods.js"></script>
	<script src="js/person-admin.js"></script>
</body>
</html>
