<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title>Hoogle</title>
<style type="text/CSS">
body {
	background: #f2f2f2;
	font-family: 'Open Sans', sans-serif;
}

.search {
	width: 100%;
	position: relative;
	display: flex;
}

.searchTerm {
	width: 100%;
	border: 3px solid #00B4CC;
	border-right: none;
	padding: 5px;
	height: 25px;
	border-radius: 5px 0 0 5px;
	outline: none;
	color: #9DBFAF;
}

.searchTerm:focus {
	color: #00B4CC;
}

.searchButton {
	width: 40px;
	height: 41px;
	border: 1px solid #00B4CC;
	background: #00B4CC;
	text-align: center;
	color: #fff;
	border-radius: 0 5px 5px 0;
	cursor: pointer;
	font-size: 20px;
}

/*Resize the wrap to see the search bar change!*/
.wrap {
	width: 30%;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.center {
	display: flex;
	margin-left: auto;
	margin-right: auto;
	width: 17%;
}
</style>
</head>

<body style='background-color: #A1FFA1'>
	<form action='${requestUri}' method='get'>
		<div class="center">
			<img src="images/running.png"
				style='position: absolute; width: 200px; height: 200px; margin-top:30px'>
		</div>
		<div class="wrap">
			<div class="search">
				<input type="text" class="searchTerm" name='keyword'
					placeholder="輸入與健康相關的關鍵字" onfocus="placeholder= '' "
					onblur="placeholder='請輸入關鍵字'">
				<button type="submit" class="searchButton">
					<i class="fa fa-search"></i>
				</button>
			</div>
		</div>
	</form>
</body>
</html>