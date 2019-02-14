<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
<title></title>
<link href="${path}/css/applystyleFrom.css" type="text/css" rel="stylesheet" />
<link href="${path}/css/applystyleAlert.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="${path}/js/jquery-1.12.3.min.js"></script>
<script src="${path}/js/popups.js"></script>

<script>
        setSize();
        addEventListener('resize',setSize);
        function setSize() {
            document.documentElement.style.fontSize = document.documentElement.clientWidth/750*100+'px';
        }
    </script>

<style>
.show-list {
	width: 80%;
	margin: 0 auto;
}

.show-list li {
	height: 1rem;
	font-size: .3rem;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	border-bottom: 1px solid #dcdcdc;
}


input.text{text-align:center}
</style>

</head>
<body>
<c:if test="${not empty chooseLogLessonList}">
    	<c:forEach items="${chooseLogLessonList}" var="cll" varStatus="vs" begin="0">
	<form id="applyUpdateForm" action="" method="post">
		<div class="t-main1">
			<span>基本信息</span>
			<div class="t-main1-list">
				<label>用户姓名：</label><input type="text" class="name-input"
					name="chooseUserName" id="chooseUserName"  value="${cll.chooseUserName}"/>
			</div>
			<div class="t-main1-list" style="padding-top: 20px;">
				<label>联系电话：</label>
				<input type="text" class="name-input"
					style="margin-left: 1px;" name="chooseUserTelNumber"
					id="chooseUserTelNumber"  value="${cll.chooseUserTelNumber}"/>
			</div>
			<div class="t-main1-list">
				<label>所属部门：</label>
				
				<input type="text" class="name-input"
					style="margin-left: 1px;" name="chooseDepartment"
					id="chooseDepartment"  value="${cll.chooseDepartment}" />
				
				<!--  <select name="chooseDepartment" class="selcet-input">
					<option value="开发部"   <c:if test="${cll.chooseDepartment == '开发部'}">selected</c:if>>开发部</option>
					<option value="电商部"    <c:if test="${cll.chooseDepartment == '电商部'}">selected</c:if>>电商部</option>
					<option value="策划部"     <c:if test="${cll.chooseDepartment == '策划部'}">selected</c:if>>策划部</option>
					<option value="活动部"      <c:if test="${cll.chooseDepartment == '活动部'}">selected</c:if>>活动部</option>
					<option value="行政部"       <c:if test="${cll.chooseDepartment == '行政部'}">selected</c:if>>行政部</option>
				</select>
				
				 -->
			</div>
		</div>
		<div class="t-main1" style="padding-top: 20px;">

			<span>点歌信息</span>
			<div class="t-main1-list">
				<label>歌曲名字：</label><input type="text" class="name-input"
					name="chooseSongName" id="chooseSongName" value="${cll.chooseSongName}" />
			</div>

			<div class="t-main1-list">
				<label>歌手名字：</label><input type="text" class="name-input"
					name="chooseSingerName" id="chooseSingerName" value="${cll.chooseSingerName}"/>&nbsp;
			</div>
			<div class="t-main1-list">
				<label>用户祝福：</label><input type="text" class="name-input"
					name="chooseWish" id="chooseWish" value="${cll.chooseWish}"/>&nbsp;
			</div>
			<c:if test="${not empty cll.information}">
			<div class="t-main1-list">
				<label>附加信息：</label><input type="text" class="name-input"
					name="" id="" value="${cll.information}"/>&nbsp;
			</div>
			</c:if>
		</div>




		<input type="hidden" name="methods" value="UpdateChooseLog" /> 
		<input type="hidden" name="userId" value="${userId}"  id="userId"/>
		<input type="hidden" name="chooseLogId" value="${chooseLogId}" id="chooseLogId" />
		<div class="subbtn" onclick="test()">提交</div>
		<div class="subbtn" onclick="repealChooseLog()">撤销</div>
		<div class="subbtn" onclick="back()">返回</div>

		<!--  
		<ul class="show-list">
        <li id="demo1">toast</li>
        <li id="demo2">alert</li>
        <li id="demo3">confirm</li>
        <li id="demo4">prompt</li>
        <li id="demo5">点击按钮跳转</li>
        <li id="demo6">取消默认点击背景消失</li>
   		 </ul>
		-->

	</form>

</c:forEach>
</c:if>



	<script>
		function test(){
		//	alert("111");
			var chooseUserName =document.getElementById("chooseUserName").value;
			var chooseUserTelNumber =document.getElementById("chooseUserTelNumber").value;
			var chooseSongName =document.getElementById("chooseSongName").value;
			var chooseSingerName =document.getElementById("chooseSingerName").value;
			if(chooseUserName==""||chooseUserName.length==0){
				jqtoast('请填写你的用户名称');
				return;
			}
			else{
				if(chooseUserTelNumber==""||chooseUserTelNumber.length==0){
					jqtoast('请填写你的手机号码');
					return;
				}
				else{
					if(chooseSongName==""||chooseSongName.length==0){
						jqtoast('请填写歌曲名称');
						return;
					}else{
						if(chooseSingerName==""||chooseSingerName.length==0){
							jqtoast('请填写歌手名字');
							return;
						}
						else{
							var pattern = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
							if( chooseUserTelNumber.length!=11||!chooseUserTelNumber.match(pattern) ){
								jqtoast("请输入正确的手机号码");
								}
							else{
						        jqalert({
				                    title:'提示',
				                    content:'确定要提交编辑后的内容么',
				                    yestext:'确定',
				                    notext:'取消',
				                    
				                    	 yesfn:function () {
				                             jqtoast('提交成功');
				                            document.getElementById("applyUpdateForm").action="/zixinhai/HomeMethods";
											document.getElementById("applyUpdateForm").submit();
				                         },
				                         nofn:function () {
				                             jqtoast('你点击了取消');
				                         }
				                })
								
									
								}	
							}
						}
					}
		
			   }
			}
		
	
		</script>
	<script>
	function repealChooseLog(){
		var chooseLogId=document.getElementById("chooseLogId").value;
		var userId=document.getElementById("userId").value;
		jqalert({
              title:'提示',
              content:'确定要撤销这条申请信息么',
              yestext:'确定',
              notext:'取消',
              	 yesfn:function () {
                      // jqtoast('提交成功');
                      //获取该日志的状态 如果处于未处理就执行撤销在,剩下的两个状态停止进行撤销然后发出alert
                      	$.ajax({
										type : 'post',
										url : '/zixinhai/HomeMethods?methods=SearchChooseLogStatus&&chooseLogId='+chooseLogId,
										// data:$("#myform").serialize(), 
										//	data: 
										cache : false,
										dataType : 'json',
										success : function(data) {
											var status=null;
											if (data.success) {
												status=data.status;
												jqtoast(status);
												if(status==1){
													  location.href="HomeMethods?methods=repealChooseLog&&userId="+userId+"&&chooseLogId="+chooseLogId;	
												}
												else if(status==2){
													jqtoast("该请求已经被处理请不要进行撤销行为");
												}
												else if(status==3){
													jqtoast("该请求已经被撤销请不要进行重复撤销行为");
												}
												else{
													jqtoast("该申请状态异常");
												}
											} else {
												status=data.status;
												jqtoast("获取状态失败");
											}
										}
									});
                      
                      
                     //
                   },
                   nofn:function () {
                       jqtoast('你点击了取消');
                   }
          });	
		
		
	}
	
	
	</script>
      <script>
      function back(){
    	  var userId=$('#userId').val();
    	//  alert(userId);
    	  location.href="GetDate?methods=ChooserData&userId="+userId; 
    	 // history.back(-1);
      }
      
      
      </script>

</body>
</html>
