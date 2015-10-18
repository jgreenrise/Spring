<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<!-- CSS -->
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="multiselect.min.css">

<!-- JS -->
<!-- load angular, nganimate, and ui-router -->
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.10/angular-ui-router.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular-animate.min.js"></script>

<script src="app.js"></script>
<script src="multiselect.js"></script>

</head>

<style>
/* Put your css in here */
multiselect {
	display: block;
}

multiselect .btn {
	width: 100%;
	background-color: #FFF;
}

multiselect .btn.error {
	border: 1px solid #da4f49 !important;
}

multiselect .dropdown-menu {
	max-height: 300px;
	overflow-y: auto;
}

multiselect .dropdown-menu {
	width: 100%;
	box-sizing: border-box;
	padding: 2px;
}

multiselect .dropdown-menu>li>a {
	padding: 3px 10px;
	cursor: pointer;
}
</style>

<body ng-app="formApp">

	<!-- views will be injected here -->
	<div class="container">
		<div ui-view></div>
	</div>


</body>
</html>