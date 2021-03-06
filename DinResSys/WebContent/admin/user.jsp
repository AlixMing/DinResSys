<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Lumino - Tables</title>

		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/datepicker3.css" rel="stylesheet">
		<link href="css/bootstrap-table.css" rel="stylesheet">
		<link href="css/styles.css" rel="stylesheet">
		<link rel="stylesheet" href="css/public.css">
		<link rel="stylesheet" href="css/user.css">

		<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
					<ul class="user-menu">
						<li class="dropdown pull-right">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a>
								</li>
								<li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a>
								</li>
								<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>

			</div>
			<!-- /.container-fluid -->
		</nav>

		<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
			<form role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
			</form>
			<ul class="nav menu">
				<li><a href="order!getAllOrderAdmin"><span class="glyphicon glyphicon-dashboard"></span> 订单管理</a>
				</li>
				<li><a href="menu!getAllMenus"><span class="glyphicon glyphicon-th"></span> 菜单管理</a>
				</li>
				<li class="active"><a href="user!getAllUser"><span class="glyphicon glyphicon-stats"></span> 客户管理</a>
				</li>
				<li><a href="activity!getAllActivity"><span class="glyphicon glyphicon-list-alt"></span> 活动管理</a>
				</li>
			</ul>
		</div>
		<!--/.sidebar-->

		<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
			<div class="row">
				<ol class="breadcrumb">
					<li><a href="#"><span class="glyphicon glyphicon-home"></span></a>
					</li>
					<li class="active">客户</li>
				</ol>
			</div>
			<!--/.row-->

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading">客户列表</div>
						<!-- Table -->
						<table class="table">
							<thead>
								<tr>
									<th>ID</th>
									<th>用户名</th>
									<th>密码</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="userList" id="p" status="status">
								<tr>
									<td><s:property value="#p.id"/></td>
									<td><input type="text" value="${p.name}"/></td>
									<td><input type="text" value="******" /></td>
									<td>
										<span class='confirm'><a href="#">确定</a></span>
										<span class='delete'><a href="/DinResSys2/admin/user!delete?id=${p.id}">删除</a></span>
									</td>
								</tr>
								</s:iterator>
								<!-- 
								<tr>
									<td>2</td>
									<td><input type="text" value='黄杰勇'/></td>
									<td><input type="text" value="666666" /></td>
									<td>
										<span class='confirm'><a href="#">确定</a></span>
										<span class='delete'><a href="/DinResSys2/admin/user!delete?id=2">删除</a></span>
									</td>
								</tr>
								<tr>
									<td>2</td>
									<td><input type="text" value='刁辉'/></td>
									<td><input type="text" value="666666" /></td>
									<td>
										<span class='confirm'><a href="#">确定</a></span>
										<span class='delete'><a href="/DinResSys2/admin/user!delete?id=3">删除</a></span>
									</td>
								</tr>
								 -->
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!--/.row-->


		</div>

		<!--/.main-->

		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/chart.min.js"></script>
		<script src="js/chart-data.js"></script>
		<script src="js/easypiechart.js"></script>
		<script src="js/easypiechart-data.js"></script>
		<script src="js/bootstrap-datepicker.js"></script>
		<script src="js/bootstrap-table.js"></script>
		<script type="text/javascript" src="../js/public/mock.js" ></script>
		<script type="text/javascript" src="../js/mock-data.js" ></script>
		<script type="text/javascript" src="js/public.js" ></script>
		<script>
			! function($) {
				$(document).on("click", "ul.nav li.parent > a > span.icon", function() {
					$(this).find('em:first').toggleClass("glyphicon-minus");
				});
				$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
			}(window.jQuery);

			$(window).on('resize', function() {
				if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
			})
			 $(window).on('resize', function() {
				if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
			})
		</script>
		<script type="text/javascript" src="js/user.js"></script>
	</body>

</html>