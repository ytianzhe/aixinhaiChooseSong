<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
  <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script> 
 <script type="text/javascript" src="layer/layer.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="iconfont/style.css" type="text/css" rel="stylesheet">
<script>
function testmsg(str){
	layer.msg(str+'不能为空');
}	
</script>
<style>
	body{color:#fff; font-family:"微软雅黑"; font-size:14px;}
	.wrap1{position:absolute; top:0; right:0; bottom:0; left:0; margin:auto }/*把整个屏幕真正撑开--而且能自己实现居中*/
	.main_content{background:url(images/main_bg.png) repeat; margin-left:auto; margin-right:auto; text-align:left; float:none; border-radius:8px;}
	.form-group{position:relative;}
	.login_btn{display:block; background:#3872f6; color:#fff; font-size:15px; width:100%; line-height:50px; border-radius:3px; border:none; }
	.login_input{width:100%; border:1px solid #3872f6; border-radius:3px; line-height:40px; padding:2px 5px 2px 30px; background:none;}
	.icon_font{position:absolute; bottom:15px; left:10px; font-size:18px; color:#3872f6;}
	.font16{font-size:16px;}
	.mg-t20{margin-top:20px;}
	@media (min-width:200px){.pd-xs-20{padding:20px;}}
	@media (min-width:768px){.pd-sm-50{padding:50px;}}
	#grad {
	  background: -webkit-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Safari 5.1 - 6.0 */
	  background: -o-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Opera 11.1 - 12.0 */
	  background: -moz-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Firefox 3.6 - 15 */
	  background: linear-gradient(#4990c1, #52a3d2, #6186a3); /* 标准的语法 */
	}
</style>

</head>
<body style="background:url(images/bg.jpg) no-repeat;">
    
    <div class="container wrap1" style="height:450px;">
            <h2 class="mg-b20 text-center">爱新海点歌系统登入页面</h2>
            <div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
                <p class="text-center font16">用户登录</p>
                <form id="Loginfrom" action=""   Method="Post"  >
                    <div class="form-group mg-t20">
                        <i class="icon-user icon_font"></i>
                        <input type="text" class="login_input" name="UserName" placeholder="请输入工号8" id="UserName" />
                    </div>
                    <div class="form-group mg-t20">
                        <i class="icon-lock icon_font"></i>
                        <input type="password" class="login_input" name="UserPassword" placeholder="请输入密码" id="UserPassword" />
                    </div>
                    <div class="checkbox mg-b25">
                        <input type="hidden" class="login_input" name="methods" value="Login" />
                         
                    </div>
                    <button  class="" type="button"  onclick="trylogin()">登 录</button>
                     <button  class="" type="button"  onclick="goRegister(this)"    title="${openid}" value="${unionid}">注册</button>
                    
               </form>
        </div><!--row end-->
    </div><!--container end-->
    
    <script>
  function goRegister(obj){
	  var openid=obj.title;
	  var unionid=obj.value;
	  window.location.href="register/TelRegister.jsp?openid="+openid+"&&unionid="+unionid; 
  }
    function trylogin(){
    //	alert("tryLogin");
    	 var UserName=document.getElementById("UserName").value;
		 var UserPassword=document.getElementById("UserPassword").value;
		// document.getElementById("Loginfrom").action="/zixinhai/HomeMethods";
	    //document.getElementById("Loginfrom").submit();
		//alert(UserName+"------"+UserPassword);
		 if(null==UserName||UserName==""||UserName.length==0){
			 //alert("用户名字不能为空");
			// return ; 
			
				 layer.msg('用户名字不能为空');
				  return ; 
					
				
		 }else{
			 if(null==UserPassword||UserPassword==""||UserPassword.length==0){
				// alert("密码不能");
					
				
					 layer.msg('密码不能为空');
					  return ; 
					
			 }
			 else{
					document.getElementById("Loginfrom").action="/zixinhai/HomeMethods";
			    	document.getElementById("Loginfrom").submit();
			    
					}
			 return ;
		 
		 }
	 
    }
    </script>
    
         
</body>
</html>