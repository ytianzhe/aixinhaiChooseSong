<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form  id="Loginfrom" action=""   Method="Post">
名字
<input type="text" name="UserName" value=""  />
密码
<input type="password" name="UserPassword" value="">
<input type="hidden"   name="methods" value="Login">
<button type="button"   onclick="zijian()"  >登入</button>
</form>
<script>
function zijian(){
	//HomeMethods
	document.getElementById("Loginfrom").action="/zixinhai/HomeMethods";
	document.getElementById("Loginfrom").submit();
}

</script>
</body>
</html>