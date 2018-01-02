<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
%>
<html lang="zh-Hans">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>商品详情</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="libs/bootstrap/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="css/publish.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="libs/iconfont/iconfont.css">
<link rel="stylesheet" href="css/details.css">
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
							href="/WebShop/UserGoodAction?pageCount=0">个人中心</a>
						<s:if test="#session.currentUser.manager">
							<a href="/WebShop/UserListAction?pageCount=0" class="">管理中心</a>
						</s:if>
						<a href="/WebShop/LogoutAction">注销</a>
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
		<div class="banner_in">
			<ul class="username">
				<li>
					<p class="p1">用户名</p>
					<p>
						<s:property value='#request.goodObject.goodUser.user_username' />
					</p>
				</li>
				<li>
					<p class="p1">宝贝浏览次数</p>
					<p>
						<s:property value='#request.goodObject.goodWatchNum' />
					</p>
				</li>
				<li>
					<p class="p1">最近编辑</p>
					<p>
						<s:property value='#request.goodObject.createDate' />
					</p>
				</li>
				<s:if test="#session.currentUser.manager">
					<div class="goods-del fr">
						<form action="/WebShop/DelGoodAction">
							<input type="hidden" name="good_id"
								value="<s:property
													value="#request.goodObject.goodId" />">
							<button type="submit" class="btn btn-warning">删除商品</button>
						</form>
					</div>
				</s:if>
			</ul>
		</div>
		<div class="show">
			<div class="show_left">
				<div class="show_pic">
					<div class="slider">
						<ul class="slider-main">
							<s:if test="#request.images != null">
								<s:iterator value="#request.images" var="index">
									<li class="slider-panel"><a href="#"><img alt=""
											title="" src="<s:property value="#index"/>"></a></li>
								</s:iterator>
							</s:if>
							<s:else>
								<li class="slider-panel"><a href="#"><img alt=""
										title="" src="img/noimg.jpg"></a></li>
							</s:else>
						</ul>
						<div class="slider-extra">

							<div class="slider-page"></div>
						</div>
						<ul class="slider-nav">
							<s:if test="#request.images != null">
								<s:iterator value="#request.images" var="index">
									<li class="slider-item"><a href="#"><img alt=""
											title="" src="<s:property value="#index"/>" width="80px"
											height="60px"></a></li>
								</s:iterator>
							</s:if>
							<s:else>
								<li class="slider-item"><a href="#"><img alt=""
										title="" src="img/noimg.jpg" width="80px" height="60px"></a></li>
							</s:else>

						</ul>
					</div>
				</div>
				<div class="show_dsc">
					<div class="dsc_bt">
						<a class="active" id="tag1" onmouseover="showTag(1)">宝贝介绍</a> <a
							id="tag2" onmouseover="showTag(2)">留言</a> <a id="tag3"
							onmouseover="showTag(3)">安全保障</a>
					</div>
					<div class="container">
						<div class="active cf" id="div1">
							<h5>如遇到以下情况可能是诈骗行为：1.宝贝价格异常低；2.卖家图片及描述不清；3.卖家要求直接汇款。</h5>
							<div class="dsc_bt_in">宝贝介绍</div>
							<div class="dsc_zw">
								<s:property value="#request.goodObject.goodDescription" />
							</div>
						</div>
						<div id="div2" class="cf" style="display: none">
							<h5>如遇到以下情况可能是诈骗行为：1.宝贝价格异常低；2.卖家图片及描述不清；3.卖家要求直接汇款。</h5>
							<div class="dsc_bt_in">留言</div>
							<div class="dsc_zw">
								<s:iterator value="#request.says" var="index">
									<div class="message">
										<img src="<s:property value="#index.user.headImage"/>" alt=""
											class="tx">
										<ul>
											<li class="message_1"><s:property
													value="#index.user.user_username" />：</li>
											<li class="message_2"><s:property value="#index.context" /></li>
											<li class="message_3">
												<p>
													<s:property value="#index.register_date" />
												</p>
											</li>
										</ul>
										<s:if test="#session.currentUser.manager">
											<form action="/WebShop/DelLeaveMessageAction?" method="get">
												<input type="hidden" name="leaveId"
													value="<s:property value="#index.userLeaveMessageId" />">
												<input type="hidden" name="goodId"
													value="<s:property value="#index.good.goodId" />">
												<button type="submit"
													class="btn btn-danger btn-xs message-btn">删除</button>
											</form>
										</s:if>
									</div>
								</s:iterator>

							</div>

							<div class="recover">
								<form action="/WebShop/PublishLeaveMessageAction" method="POST">
									<s:if test="#session.currentUser!=null">
										<div class="recover_in">
											<textarea name="context" id="" cols="30" rows="10"
												maxlength="250"></textarea>
											<textarea hidden="true" name="goodId"><s:property
													value="#request.goodObject.goodId" /></textarea>
											<input type="submit" value="提交">
										</div>
									</s:if>
									<s:else>
										<div class="recover_in">
											<textarea name="context" id="" cols="30" rows="10"
												readonly="readonly" maxlength="250">请先登陆后发言</textarea>
											<textarea hidden="true" name="goodId"><s:property
													value="#request.goodObject.goodId" /></textarea>
											<input value="提交" hidden="false">
										</div>
									</s:else>
								</form>
							</div>
						</div>
						<div id="div3" class="cf" style="display: none">
							<h5>如遇到以下情况可能是诈骗行为：1.宝贝价格异常低；2.卖家图片及描述不清；3.卖家要求直接汇款。</h5>
							<div class="dsc_bt_in">安全保障</div>
							<div class="dsc_zw">该平台仅限校园内交易,不涉及在线支付及网银转账,并有专人维护,保障安全。</div>
						</div>
					</div>


				</div>
			</div>
			<div class="show_right">
				<div class="show_r_t">
					<p>
						<s:property value="#request.goodObject.goodName" />
					</p>
					<ul class="show_mes">
						<li>转 卖 价：<span style="font-size: 24px; color: #999;">￥</span><span
							style="font-size: 24px; color: #f40;"><s:property
									value="#request.goodObject.salePrice" /></span></li>
						<li class="li_two">原 &nbsp;&nbsp;&nbsp; 价：￥<s:property
								value="#request.goodObject.formerPrice" /></li>
						<s:if test="#session.currentUser!=null">
							<li>联系方式：<a target="_blank" class="talk"><s:property
										value="#request.goodObject.phoneNumber" /></a></li>
						</s:if>
						<s:else>
							<li>联系方式：<a target="_blank" class="talk">登录后可视</a></li>
						</s:else>
						<li><s:if test="#request.isSale">
								<button type="button" class="btn btn-warning buy">已出售</button>
							</s:if> <s:else>
								<s:if test="#session.currentUser!=null">
									<button type="button" class="btn btn-warning buy"
										data-toggle="modal" data-target="#contact">与他联系</button>
								</s:if>
								<s:else>
									<button type="button" class="btn btn-warning buy">登录后联系</button>
								</s:else>
							</s:else></li>
						<li><span class="tips">交易流程:</span>
							<p>买家通过上方“与他联系”与卖家取得联系,并与卖家经过协商,最终卖家同意交易,即交易成功。</p></li>
					</ul>
					<div class="modal fade" id="contact" tabindex="-1" role="dialog"
						aria-labelledby="contactLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title" id="contactLabel">提交购买请求</h4>
								</div>
								<form method="post" action="CommitContactLeaveAction">
									<div class="modal-body">
										<input type="hidden" name="goodId"
											value="<s:property
													value="#request.goodObject.goodId" />">
										<input type="hidden" name="userId"
											value="<s:property
													value="#session.currentUser.user_id" />">
										<div class="form-group">
											<label for="">联系方式</label> <input name="mobilePhone"
												id="mobilePhone" type="tel" class="form-control"
												placeholder="务必填写真实有效的手机号码" required="required"
												maxlength="11"> <br>
											<code>务必填写真实有效的手机号码</code>
										</div>
										<div class="form-group">
											<label for="">购买意向</label>
											<textarea name="message" class="form-control" cols="30"
												rows="10" placeholder="填写格式请参照下方（包括成交价格,联系方式,交易地点,其他留言）"
												required="required" maxlength="250"></textarea>
											<br>
											<code>1.成交价格:</code>
											<br>
											<code>2.联系方式:</code>
											<br>
											<code>3.交易地点:</code>
											<br>
											<code>4.其他留言</code>
											<br>

										</div>
										<h5>用户提交购买需求后,可以主动与卖家联系,也可等待卖家与您联系协商。</h5>
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


	</div>
	<div class="footer">
		<p>2016 Copyright &copy 浙江省大学生科技创新活动计划暨新苗人才计划2014R453004项目组</p>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/show.js"></script>
</body>
</html>
