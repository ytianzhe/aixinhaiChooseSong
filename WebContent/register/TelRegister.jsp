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
  <link rel="stylesheet" href="<%=basePath%>plugins/weui/style/weui.css?v=-4e168d7" />
  <link rel="stylesheet" href="<%=basePath%>plugins/weui/style/example.css?v=-5c436b4" />
  <link rel="stylesheet" href="<%=basePath%>style/is.css?v=-c53d51b" />
  <style>#request-proxy .dialog-img-code .weui-cell{padding:5px 0}#request-proxy .dialog-img-code .weui-dialog__bd:first-child{padding:0}#request-proxy .dialog-img-code .weui-input{padding-left:10px}#request-proxy .weui-vcode-btn{margin-left:0;width:9rem}</style>
</head>
<body >
  <div class="container" id="container">
   <div class="page js_show" id="request-proxy">
    <div class="page__bd">
     <form id="registerForm"  action=""  method="post"> 
     <div class="weui-cells weui-cells_form">
     <div class="weui-cell">
      <div class="weui-cell__bd  is-text-center">注册表单</div>
     </div>
     
     <!--注册数据start  -->
      <div class="weui-cell">
       <div class="weui-cell__hd">
        <label class="weui-label">真实姓名</label>
       </div>
       <div class="weui-cell__bd">
        <input class="weui-input" name="realName" type="text"  id="realName"/>
       </div>
      </div>
      
      <div class="weui-cell">
       <div class="weui-cell__hd">
        <label class="weui-label">工号</label>
       </div>
       <div class="weui-cell__bd">
        <input class="weui-input" name="jobNumber" type="text"  id="jobNumber"/>
       </div>
      </div>
      
      
      
     
      <div class="weui-cell">
       <div class="weui-cell__hd">
        <label class="weui-label">登录密码</label>
       </div>
       <div class="weui-cell__bd">
        <input class="weui-input" name="password" type="text" id="password"/>
       </div>
      </div>
      
      
      
      
      
      
      <div class="weui-cell weui-cell_vcode">
       <div class="weui-cell__hd">
        <label class="weui-label">手机号码</label>
       </div>
       <div class="weui-cell__bd">
        <input class="weui-input" name="mobile" type="text" maxlength="11" id="mobile" />
       </div>
       <div class="weui-cell__ft">
        <button type="button" class="weui-vcode-btn" name="sendSMS" id="sendSMSBtn" onclick="SendSMS(this)" value="获取验证码"/>获取验证码</button>
        
       <!--  <input type="button" class="weui-vcode-btn" name="sendSMS" id="sendSMSBtn" onclick="SendSMS(this)" value="获取验证码"/> -->
       </div>
      </div>
      <div class="weui-cell">
       <div class="weui-cell__hd">
        <label class="weui-label">短信验证</label>
       </div>
       <div class="weui-cell__bd">
        <input class="weui-input" name="sms-code" type="number" id="VerificationCode"/>
        <input class="weui-input" name="" type="hidden" id="codeGen"/>
       </div>
      </div>
     
       <div class="weui-cell">
      
       <div class="weui-cell__bd">
        <input  type="hidden"  name="openid"   value="${param.openid}"/>
        <input  type="hidden" name="unionid"   value="${param.unionid}"/>
       </div>
      </div>
      
      
     </div>
     <div class="weui-btn-area">
     <button class="weui-btn weui-btn_primary confirm" type="button"  onclick="insertSubmit()"  >确认</button>
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
  <script src="../plugins/zepto/zepto.min.js?v=-2dd2e0f"></script>
  <script src="../plugins/weui/weui.min.js?v=-56b2004"></script>
  <script src="../js/is.js?v=-477a0d2"></script>

<script>
function insertSubmit(){
//优先验空


	
	var realName= $("#realName").val();
	var jobNumber= $("#jobNumber").val();
	var password=$("#password").val();
	var mobile=$("#mobile").val();
	var VerificationCode=$("#VerificationCode").val();
	var codeGen=$("#codeGen").val();
	
	if(null==realName||realName==""||realName.length==0){
		layer.msg("消息：姓名不能放为空");
		return;
	}else{
		
		if(null==jobNumber||jobNumber==""||jobNumber.length==0){
			layer.msg("消息：工号不能为空");
			
			return;
		}else{
			if(null==password||password==""||password.length==0){
				layer.msg("消息：密码不能为空");
				return;
			}else{
				if(null==mobile||mobile==""||mobile.length==0){
					layer.msg("消息：手机号码不能为空");
					return;
				}else{
					if(null==VerificationCode||VerificationCode==""||VerificationCode.length==0){
						layer.msg("消息：短信验证不能为空");
						return;
					}else{
						if(VerificationCode!=codeGen){
							layer.msg("消息：短信验证不正确"+VerificationCode+"----"+codeGen);
							return;
						}else{
							
							//layer.msg("消息：验证全部通过");
							//return;
						
							//首先 ajax验证 工号和名字是否配对 
							$.ajax({
								type : 'post',
							    url : '/zixinhai/GetDate?methods=WeChatNickname&&jobNumber='+jobNumber,
											cache : false,
											dataType : 'json',
											success : function(data) {
												if (data.success) {
													//var status=data.status;
													var msg=data.msg;
													//layer.msg("消息："+msg);
													//用户已存在 比对用户姓名
													var obj=data.data;
													var name=obj.trueName;
													var tel=obj.trueTel;
													//layer.msg("消息："+name+" tel:"+tel);
													if(realName!=name){
														layer.msg("消息：姓名和工号不匹配");
													}
													else if(realName==name){
														//layer.msg("消息：姓名和工号匹配开始注册");
														//验证通过开始注册(缺少  id  添加时间 状态 昵称 默认登入方式)
														
														document.getElementById("registerForm").action="/zixinhai/UserRegister";
														document.getElementById("registerForm").submit();
														
														
													}
													else{
														layer.msg("消息：数据异常");
													}
												} else {
													//var status=data.status;
													var msg=data.msg;
													layer.msg("消息：工号不存在");
													return;
												}
											}
										});
							
						}
						
						
					}
					
				}
				
			}
			
		}
		
	}
	
	
	//layer.msg(realName+"----"+jobNumber);
	
	
	
	
	
	//验证通过   获取主页传过来的openid和unionid   
	
	
	//提交表单   在后台通过 openid  工号   openid  unionid goal 获取 该注册用户的微信昵称
	
	//在后台完成注册
}

</script>
<script>
function SendSMS(btn){
	var mobile=document.getElementById("mobile").value;
	if(mobile==null||mobile==""||mobile.length==0){
		layer.msg("消息：请填写手机号");//alert('请填写手机号');
		return;
		}
		else{
			if(mobile.length!=11){
				layer.msg("消息：请填写11位的手机号");//alert("请填写11位的手机号");
				return;
			}
			else{
				
				var pattern = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
				if( mobile.length!=11||!mobile.match(pattern) ){
					layer.msg("消息：请输入正确的手机号码");//alert("请输入正确的手机号码");
					return;
					}
				else
				{
				//	layer.msg("手机号正确 开始获取验证码");
				Ctime(btn);
					$.ajax({
						type : 'post',
					    url : '/zixinhai/GetDate?methods=VerificationCode&&method=VerificationCode&&mobile='+mobile,
									cache : false,
									dataType : 'json',
									success : function(data) {
										if (data.success) {
											//var status=data.status;
											var msg=data.msg;
											layer.msg("消息："+msg);
											var codeGen=data.codeGen;
			    				    		document.getElementById("codeGen").value=codeGen;
										//	return;
											
										} else {
											//var status=data.status;
											var msg=data.msg;
											layer.msg("消息："+msg);
											return;
										}
									}
								});
					
					
				   }
	
			  }
			}
//	layer.msg("开始获取验证码");

	
}
var wait = 60;
function Ctime(btn){
    if(wait == 0) {
        btn.removeAttribute("disabled");
        btn.innerHTML="获取验证码";
     //   btn.value = "获取验证码";
        wait = 60;
    } else {
        btn.setAttribute("disabled", true);
        btn.innerHTML="还剩(" + wait + "s)";
      //  btn.value = "还剩(" + wait + "s)";
        wait--;
        setTimeout(function(){Ctime(btn)},1000);
    }	
}
</script>
</html>