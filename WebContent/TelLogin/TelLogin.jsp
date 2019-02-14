<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>

<head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

 <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
  <title>爱心海后台</title>
  
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script> 
 	<script type="text/javascript" src="<%=basePath%>layer/layer.js"></script>
<script>
window.onload=function showmsg(){
//	var obj=
 	var msg= document.getElementById("msg").value;
 	if(null!=msg){
 		if(msg.length!=0||msg!=""){
 		 layer.msg(msg);
 	}}
}
 </script>
 	
 	
  <link rel="stylesheet" href="<%=basePath%>plugins/weui/style/weui.css?v=-4e168d7" />
  <link rel="stylesheet" href="<%=basePath%>plugins/weui/style/example.css?v=-5c436b4" />
  <link rel="stylesheet" href="<%=basePath%>style/is.css?v=-c53d51b" />
  <style>
  #request-proxy .dialog-img-code .weui-cell{padding:5px 0}
  #request-proxy .dialog-img-code .weui-dialog__bd:first-child{padding:0}
  #request-proxy .dialog-img-code .weui-input{padding-left:10px}
  #request-proxy .weui-vcode-btn{margin-left:0;width:9rem}
  
  

.loginLink{
	text-align: center;
	margin-top: 0.8rem;
}
.loginLink_text{
	display: inline-block;
}
.loginLink_text a{
	font-size: 0.92rem;
	color: #009b63;
}
.loginLink_text a:last-child{
	color: #333;
}
.loginLink_text span{
	margin:0 0.3rem;
	height: 0.9rem;
}
  </style>
</head>
<body >
  <div class="container" id="container">
   <div class="page js_show" id="request-proxy">
    <div class="page__bd">
     <form id="loginForm"  action=""  method="post"> 
     <div class="weui-cells weui-cells_form">
     <div class="weui-cell">
      <div class="weui-cell__bd  is-text-center">登入表单</div>
     </div>
     
     <!--登入数据start  -->
 
      
      <div class="weui-cell">
       <div class="weui-cell__hd">
        <label class="weui-label">工号</label>
       </div>
       <div class="weui-cell__bd">
        <input class="weui-input" name="jobNumber" type="text"  id="jobNumber" value="${jobNumber}"/>
       </div>
      </div>
      
      
      
     
      <div class="weui-cell">
       <div class="weui-cell__hd">
        <label class="weui-label">登录密码</label>
       </div>
       <div class="weui-cell__bd">
        <input class="weui-input" name="password" type="password" id="password" value="${password}"/>
         <input type="hidden" class="login_input" name="methods" value="Login" />
         <input type="hidden" class="login_input"  value="${msg}" id="msg" />
       </div>
      </div>
     </div>
     <div class="weui-btn-area">
     <button class="weui-btn weui-btn_primary confirm" type="button"  onclick="LoginSubmit()"  >确认</button>
     </div>
     <div class="loginLink">
					<div class="loginLink_text"><a href="<%=basePath%>register/TelRegister.jsp?openid=${param.openid}&&unionid=${param.unionid}">注册</a><span>
				<!-- 	|</span><a href="#">忘记密码</a></div> -->
		</div>
     </div>
    </form> 
    </div>
 <!--   <div class="js_dialog dialog-img-code" style="display:none">
     <div class="weui-mask"></div>
     <div class="weui-dialog">
      <div class="weui-dialog__bd">
       <div class="weui-cell">
        <div class="weui-cell__bd">
         <input class="weui-input" name="code" type="text" maxlength="4" />
        </div>
        <div class="weui-cell__ft">
         <img class="weui-vcode-img img-code" />
        </div>
       </div>
      </div>
      <div class="weui-dialog__ft">
       <a href="javascript:" class="weui-dialog__btn weui-dialog__btn_primary dialog-confirm">确认</a> 
       <a href="javascript:" class="weui-dialog__btn weui-dialog__btn_default cancel">取消</a>
      </div>
     </div>
    </div>-->
   </div>
  </div>
  <div class="weui-toptips weui-toptips_warn js_tooltips"></div>
  <script src="<%=basePath%>plugins/zepto/zepto.min.js?v=-2dd2e0f"></script>
  <script src="<%=basePath%>plugins/weui/weui.min.js?v=-56b2004"></script>
  <script src="<%=basePath%>js/is.js?v=-477a0d2"></script>
<script>
function LoginSubmit(){
    //	alert("tryLogin");
    	 var jobNumber=document.getElementById("jobNumber").value;
		 var password=document.getElementById("password").value;
		// document.getElementById("Loginfrom").action="/zixinhai/HomeMethods";
	    //document.getElementById("Loginfrom").submit();
		//alert(UserName+"------"+UserPassword);
		 if(null==jobNumber||jobNumber==""||jobNumber.length==0){
			 //alert("用户名字不能为空");
			// return ; 
			
				 layer.msg('用户名字不能为空');
				  return ; 
					
				
		 }else{
			 if(null==password||password==""||password.length==0){
				// alert("密码不能");
					 layer.msg('密码不能为空');
					  return ; 
			 }
			 else{
					document.getElementById("loginForm").action="/zixinhai/HomeMethods";
			    	document.getElementById("loginForm").submit();
			    
					}
			 return ;
		 
		 }
	 
    }


</script>


</html>