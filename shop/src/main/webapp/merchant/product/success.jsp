<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="layout.tld" prefix="e"%>
<e:override name="title">股东汇 | 管理平台</e:override>


<!-- 页头 -->
<e:override name="header">
	<!-- small navbar -->
	<header>
		<nav class="navbar navbar-default navbar-static-top" role="navigation">
			<div class="container">

				<div class="row">

					<div class="col-lg-12">
						<div class="collapse navbar-collapse">
							<ul
								class="nav navbar-nav navbar-left bootstrap-admin-theme-change-size">
								<li><a class="navbar-brand" href="#">Change size:</a></li>
								<li><a class="size-changer small">Small</a></li>
								<li><a class="size-changer large active">Large</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</header>

</e:override>


<!-- 内容区 -->
<e:override name="content">
	<!-- content -->

	<section id="cloud-hosting-for-you" class="hero extra-space">
		<div class="container">
			<div class="row">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="col-lg-12">
							<div class="centering text-center error-container">
								<div class="text-center">
									<h2 class="without-margin">
										 你已经<span class="text-success"><strong>成功发布</strong></span>
										商品
									</h2>
									<h4 class="text-danger">You have successfully release goods.</h4>
								</div>
								<div class="text-center">
									<h3>
										<small>Choose an option below</small>
									</h3>
								</div>
								<hr>
								<ul class="pager">
									<li><a href="<c:url value="/merchant/product/publish.jsp"/>">&larr; 继续发布宝贝</a></li>
									<li><a href="##">&nbsp;&nbsp;返回主页&nbsp;&nbsp;</a></li>
									<li><a href="##">查看宝贝&rarr;</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</e:override>

<!-- 页脚 -->
<e:override name="footer">
	<!-- footer -->
	<div class="navbar navbar-footer navbar-fixed-bottom">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-lg-offset-4">
					<footer role="contentinfo">
						<p class="navbar-text">
							<strong>Bootstrap 3.x Admin Theme. </strong>&copy; 2013 <a
								href="##" target="_blank">Meritoo.pl</a>
						</p>
					</footer>
				</div>
			</div>
		</div>
	</div>
</e:override>
<%@include file="/merchant/base.jsp"%>