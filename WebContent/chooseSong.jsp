<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<meta name="viewport" content="width=device-width,user-scalable=no,minimum-scale=1.0, maximum-scale=2.0" />
<link href="iconfont/style.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
<title>手机端点歌</title>
</head>
<body style="background:url(images/bg.jpg) no-repeat;">
	
 <div class="container wrap1" style="height:600px;">
            <h2 class="mg-b20 text-center">点歌统计</h2>
            <div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
                <p class="text-center font16">用户点歌</p>
                <form id="songForm" action=""   Method="Post">
                    <div class="form-group mg-t20">
                        <i class="icon-user icon_font"></i>
                        <input type="text"  class="login_input" name="ChooseUsrName" id="ChooseUsrName" value=""   placeholder="请输入点歌用户名称"  />
                    </div>
                    
                    <div class="form-group mg-t20">
                        <i class="icon-user icon_font"></i>
                        <input type="text"  class="login_input" name="telNumber" id="telNumber" value=""   placeholder="请输入点歌用户部门"  />
                    </div>
                    
                    
                     <div class="form-group mg-t20">
                        <i class="icon-user icon_font"></i>
                        <input type="text"  class="login_input" name="ChooseSongName" id="ChooseSongName" value=""   placeholder="请输入歌曲名称"  />
                    </div>
                    
                     <div class="form-group mg-t20">
                        <i class="icon-user icon_font"></i>
                        <input type="text"  class="login_input" name="ChooseSingerName" id="ChooseSingerName" value=""   placeholder="请输入歌手名字"  />
                    </div>
                    
                     <div class="form-group mg-t20">
                        <i class="icon-user icon_font"></i>
                        <input type="text"  class="login_input" name="ChooseSingerName" id="ChooseSingerName" value=""   placeholder="请输入祝福"  />
                    </div>
                    
                    <div class="checkbox mg-b25">
                    	<input type="hidden" name="UserId" value="${userId}"/>
                    </div>
                    <button  class="login_btn"   onclick="ChooseSing()">申请</button>
               </form>
        </div><!--row end-->
    </div><!--container end-->
	


	
<script>
function ChooseSing(){
	 //alert("1");
	 document.getElementById("songForm").action="/zixinhai/HomeMethods?methods=AddChooseLog";
	 document.getElementById("songForm").submit();
	 
}
</script>



</body>
</html>
