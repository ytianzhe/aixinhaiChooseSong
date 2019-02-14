<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.echart1{
height:400px;
width:240px;
}
</style>
<link rel="stylesheet" href="css/bootstrap.css">

 <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div style="padding: 15px;padding-top:40px">
	<form id="userIdForm" method="post"  target="mainRight">

   
        <input type="button"  value="用户的数据" onclick="User()"/>  
  		<input type="hidden" name="userId" value="${userId}" id="userId"/>
    </form>
   </div> 
  
  
  <script>
   function User(){
	   document.getElementById("userIdForm").action="/zixinhai/UserApplicationForm";
	   document.getElementById("userIdForm").submit();	   
   }
  
   </script>
   
</body>
</html>
<!-- 
取消手机号的绑定
<c:if test="${ empty employeetelphone}">
           <td><input type="hidden"  name="employeeNumber" value="${employeeNumber}"/></td>
           <td><input type="hidden"  name="employeeid" value="${employeeid}"/></td>
          <td><input type="button" value="绑定手机号" class="btn btn-default" onclick="javascript:BindCellPhoneNumber()"></td>  
       </br>
        </c:if> 
         <c:if test="${not empty employeetelphone}">
           <td><input type="hidden"  name="employeeNumber" value="${employeeNumber}"/></td>
           <td><input type="hidden"  name="employeeid" value="${employeeid}"/></td>
          <td><input type="button" value="跟换手机号" class="btn btn-default" onclick="javascript:BindCellPhoneNumber()"></td>  
        </c:if>  -->