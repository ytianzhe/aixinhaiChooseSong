<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 因为getRequestDispatcher跳转丢失了路径 所以这里重构路径 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%>


<!DOCTYPE html>
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
<!--  
<link href="../css/applystyleFrom.css" type="text/css" rel="stylesheet" />
<link href="../css/applystyleAlert.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/jquery-1.12.3.min.js"></script>
<script src="../js/popups.js"></script>
-->
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
</style>

</head>
<body>

	<form id="applyForm" action="" method="post">
		<div class="t-main1">
			<span>基本信息</span>
			<div class="t-main1-list">
				<label>用户姓名：</label><input type="text" class="name-input"
					name="chooseUserName" id="chooseUserName"  value="${chooseUserName}" />
			</div>
			<div class="t-main1-list" style="padding-top: 20px;">
				<label>联系电话：</label>
				<input type="text" class="name-input"
					style="margin-left: 1px;" name="chooseUserTelNumber"
					id="chooseUserTelNumber" value="${chooseUserTelNumber}" />
			</div>
			<div class="t-main1-list">
				<label>所属部门：</label> 
					<input type="text" class="name-input"
					style="margin-left: 1px;" name="chooseDepartment"
					id="chooseDepartment"  />
				
				<!--  
				<select name="chooseDepartment"
					class="selcet-input">
					<option value="开发部" selected="selected">开发部</option>
					<option value="电商部">电商部</option>
					<option value="策划部">策划部</option>
					<option value="活动部">活动部</option>
					<option value="行政部">行政部</option>
				</select>
				-->
			</div>
		</div>
		<div class="t-main1" style="padding-top: 20px;">
			<span>点歌信息</span>
			<div class="t-main1-list">
				<label>歌曲名字：</label><input type="text" class="name-input"
					name="chooseSongName" id="chooseSongName"  />
			</div>

			<div class="t-main1-list">
				<label>歌手名字：</label><input type="text" class="name-input"
					name="chooseSingerName" id="chooseSingerName"/>
			</div>
			<div class="t-main1-list">
				<label>用户祝福：</label><input type="text" class="name-input"
					name="chooseWish" id="chooseWish"  />
			</div>
		</div>



		<input type="hidden" name="methods" value="AddChooseLog" />
		 <input type="hidden" name="userId" value="${userId}"  id="userId"/>
		<div class="subbtn" onclick="test()">提交</div>
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
						document.getElementById("applyForm").action="/zixinhai/HomeMethods";
						document.getElementById("applyForm").submit();
								}	
							}
						}
					}
		
			   }
			}
		
	
		</script>
	<script>
        $(function () {
            $('#demo1').click(function () {
                jqtoast('你点击了toast')
            })
            $('#demo2').click(function () {
                jqalert({
                    title:'提示',
                    content:'自定义弹窗内容，居左对齐显示，告知需要确认的信息等'
                })
            })
            $('#demo3').click(function () {
                jqalert({
                    title:'提示',
                    content:'自定义弹窗内容，居左对齐显示，告知需要确认的信息等',
                    yestext:'知道了',
                    notext:'取消'
                    
                    
                })
            })
            $('#demo4').click(function () {
                jqalert({
                    title:'提示',
                    prompt:'请问你叫什么名字?',
                    yestext:'提交',
                    notext:'取消',
                    yesfn:function () {
                        jqtoast('提交成功');
                    },
                    nofn:function () {
                        jqtoast('你点击了取消');
                    }
                })
            })
            $('#demo5').click(function () {
                jqalert({
                    title:'提示',
                    content:'自定义弹窗内容，居左对齐显示，告知需要确认的信息等',
                    yeslink:'https://www.baidu.com/'
                })
            })
            $('#demo6').click(function () {
                jqalert({
                    title:'提示',
                    content:'自定义弹窗内容，居左对齐显示，告知需要确认的信息等',
                    click_bg:false
                })
            })
        })

    </script>
  <script>
      function back(){
    	  var userId=$('#userId').val();
    	//  alert(userId+"开始跳转");
    	  location.href="GetDate?methods=ChooserData&userId="+userId; 
    	 // history.back(-1);
      }
      
      
      </script>
</body>
</html>
