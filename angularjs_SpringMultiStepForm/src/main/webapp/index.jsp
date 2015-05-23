<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<!-- CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

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

<body ng-app="formApp">

	<!-- views will be injected here -->
	<div class="container">
		<div ui-view></div>
	</div>


</body>
</html>