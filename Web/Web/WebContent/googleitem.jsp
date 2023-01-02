<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hoogle</title>
<style type="text/css">
#padding {
	padding: 0px 0px 15px 15px;
}

a {
	color: #0B173B;
	font-size: 30px;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.border-style {
	border-radius: 90px/90px;
}
</style>
</head>
<body style='background-color: #A1FFA1'>
	<form action='${requestUri}' method='get'>
		<div style='position: absolute; margin-top: 190px; margin-left: 50px'>
			<%
			String[][] orderList = (String[][]) request.getAttribute("query");
			for (int i = 0; i < orderList.length; i++) {
				String s = orderList[i][1];
			%>
			<a href='<%=s%>'><%=orderList[i][0]%> </a> <br>
			<hr size="2px" align="center" width="100%" color="black">
			<br><br>
			<%}%>
			
			相關搜尋:
			<% 
			String[] orderList2 = (String[]) request.getAttribute("relative");
			for (int i = 0; i < orderList2.length; i++) {
			String r = orderList2[i];
			%>
			<br><%=orderList2[i]%>
			<%}%><br><br>
		</div>
		
		<div>
			<img src="images/running.png"
				style='position: absolute; width: 150px; height: 150px; left: 50%; top: 50%; margin-top: -280px; margin-left: -590px'>
		</div>
		<div>
			<input type='text' class="border-style" id="padding" name='keyword'
				style='font-size: 120%; position: absolute; left: 50%; top: 48%; margin-top: -250px; margin-left: -400px; width: 800px; height: 25px'
				placeholder='請輸入關鍵字' value='<%=request.getParameter("keyword")%>' />
		</div>
	</form>
</body>
</html>