<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
    

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="mobile web app, mobile template, mobile design, mobile app design, mobile app theme, mobile wordpress theme, my mobile app" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
  <link rel="stylesheet" href="<%=basePath%>plugins/weui/style/weui.css?v=-4e168d7" />
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

<link rel="stylesheet" type="text/css" href="css/css.css">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<title>论坛首页</title>
<style>
.a1{
cursor:pointer;
}

</style>
</head>
<body >
 



          

<div class="main">
 <!--     <div class="logo"><div class="img"><img src="img/logo.png"></div></div>-->

    <c:if test="${not empty UserDataLessonList}">
    		<div class="index">
                    <div class="index_box">
                            <div class="index_icon">
                            <!--     <div class="icon_h">
								  <img src="img/fabu.png">  
                                 </div>-->
                                 <div class="titl">
                                      <form id="jumpApplyFrom" action=""  method="post">
  										        <input type="hidden" value="${userId}"  id="userId" name="userId"/>
 												 <input type="hidden" value="jumpApplySongPagePage"   name="methods"/>
 										<div class="weui-cell__ft"  >
        								  <input type="button"  class="weui-btn weui-btn_primary "  onclick="AddUserChooseData()"  value="申请点歌" />
     									   <input type="button"   class="weui-btn weui-btn_primary weui-btn_mini" onclick="exit()"  value="退出登入" / >
     									  <input type="hidden" value="${msg}" id="msg"/>
     									  </div>			 
 								
 										</form>
                                 </div>	
                            </div>
                   		 </div>
           			 </div>
              
    		
    
    	<c:forEach items="${UserDataLessonList}" var="udl" varStatus="vs" begin="0">
    		    <a onclick="EditChooseLog(this)" title="${udl.id}" class="a1">
            	<div class="index">
                    <div class="index_box">
                            <div class="index_icon">
                                 <div class="icon_h">
                                 
                                <c:if test="${udl.status=='1'}"> 
<!--  								<img src="img/icon/icon1.png"> -->
									<img src="img/icon/1187201.png">
  								</c:if>
  								
                                 <c:if test="${udl.status=='2'}"> 
						<!--			<img src="img/icon/icon7.png">-->
					 				<img src="img/icon/1187199.png">
  								</c:if>
                                  <c:if test="${udl.status=='3'}"> 
					<!--				<img src="img/icon/icon6.png"> -->
									<img src="img/icon/1187198.png">
  								</c:if> 
                                 
                                 
                                 </div>
                                 <div class="titl">
                                     <h1>点歌  ${udl.chooseSongName}</h1>
                                 <div class="titl_h">
                             
                               <c:out value="歌手 ：${udl.chooseSingerName}" escapeXml="false" /><br/>
                                 <time title="#">
								<c:if test="${not empty udl.updateTime}">
								<fmt:formatDate value="${udl.updateTime}" pattern="yyyy-MM-dd" />
							    </c:if>
							    <c:if test="${ empty udl.updateTime}">
								<fmt:formatDate value="${udl.firstAddTime}" pattern="yyyy-MM-dd" />
							    </c:if>
							    
									</time>
                                   <!--   <floor title="#">今日<span>(31)</span></floor> -->
                                      <I><span>${udl.statusValue}</span></I>
                                     
                                     </div>
                                 
                                 </div>	
                                 <div class="clear"></div>
                            </div>
                   		 </div>
           			 </div>
           			 
   			 </a>
    		
    
   	 </c:forEach>
   </c:if>
   
   
   <c:if test="${empty UserDataLessonList}">
    		<div class="index">
                    <div class="index_box">
                            <div class="index_icon">
                                 <div class="icon_h">
									<img src="img/fabu.png">
                                 </div>
                                 <div class="titl">
                                      <form id="jumpApplyFrom" action="" method="post">
  										<input type="hidden" value="${userId}"  id="userId" name="userId"/>
 							          <input type="hidden" value="jumpApplySongPagePage"   name="methods"/>
 							         
 									<button onclick="AddUserChooseData()">申请点歌</button>
 										</form>
                                 <div class="titl_h">
                                     
                                     </div>
                                 
                                 </div>	
                                 <div class="clear"></div>
                            </div>
                   		 </div>
           			 </div>
          
   </c:if>
   
   
  	<div class="foot">
    				
    				
     
     </div>  
      

  
</div>  
 <c:if test="${not empty UserDataLessonList}">
<!-- 翻页组件 -->
<div style="height:60px;position:relative;" >
	<div style="text-align: center;">第${curPage}页/共${pageCount}页       </div>


		<div  style="text-align: right;margin-right:10%" >
		<nav aria-label="Page navigation"  >
  		<ul class="pagination" >
  		  <li>
     		 <a  style="border: 1px solid #ddd;" href="Paging?methods=HomePage&&curPage=1&&pageCount=${pageCount}&&userId=${userId}&&label=UserPage"   aria-label="Previous">
       	 	<span aria-hidden="true" style="color:#38acff" >&laquo;</span>
     	 </a>
   	 	</li>
    		<li><a  style="border: 1px solid #ddd;" href="Paging?methods=PreviousPage&&curPage=1&&pageCount=${pageCount}&&userId=${userId}&&label=UserPage"><span style="color:#38acff"><</span> </a></li>
   		<c:forEach var="pageIndex" begin="1" end="${pageCount}">
			<c:choose>
		<c:when test="${pageIndex==1}">
			<c:choose>
				<c:when test="${curPage== pageIndex}">
				<li><a  style="border:1px solid #ddd;background-color: #38acff;color:white">${pageIndex}</a></li>
				</c:when>
				<c:otherwise>
				<li><a  style="border:1px solid #ddd;" href="Paging?methods=null&&curPage=${pageIndex}&&pageCount=${pageCount}&&userId=${userId}&&label=UserPage">${pageIndex}</a></li>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${pageIndex==pageCount}">
					<c:choose>
						<c:when test="${curPage== pageIndex}">
							<li><a  style="border:1px solid #ddd;background-color:#38acff;color:white">${pageIndex}</a></li>
						</c:when>
						<c:otherwise>
						<li><a   style="border:1px solid #ddd;" href="Paging?methods=null&&curPage=${pageIndex}&&pageCount=${pageCount}&&userId=${userId}&&label=UserPage">${pageIndex}</a></li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${pageCount<=10 }">
							<c:choose>
								<c:when test="${curPage== pageIndex}">
									<li><a style="border:1px solid #ddd;background-color: #38acff;color:white">${pageIndex}</a></li>
								</c:when>
								<c:otherwise>
								<li>	<a  style="border:1px solid #ddd;" href="Fenye?curPage=${pageIndex}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright">${pageIndex}</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${curPage-pageIndex<=3&&curPage-pageIndex>=-3 }">
									<c:choose>
										<c:when test="${curPage== pageIndex}">
											<li><a style="border:1px solid #ddd;background-color: #38acff;color:white">${pageIndex}</a></li>
										</c:when>
										<c:otherwise>
										<li>	<a   style="border: 1px solid #ddd;" href="Paging?methods=null&&curPage=${pageIndex}&&pageCount=${pageCount}&&userId=${userId}&&label=UserPage">${pageIndex}</a></li>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${pageIndex==2 }">
											<li><a>...</a></li>
										</c:when>
										<c:when test="${pageIndex==pageCount-1 }">
										</c:when>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</c:forEach>
	<li><a  style="border: 1px solid #ddd;" href="Paging?methods=NextPage&&curPage=1&&pageCount=${pageCount}&&userId=${userId}&&label=UserPage"><span style="color:#38acff"> ></span> </a></li>
    		<li>
     			 <a  style="border: 1px solid #ddd;" href="Paging?methods=EndPage&&curPage=1&&pageCount=${pageCount}&&userId=${userId}&&label=UserPage" aria-label="Next">
       			 <span aria-hidden="true" style="color:#38acff">&raquo;</span>
      			</a>
   		 	</li>
 		</ul>
	</nav>
	</div>
</div>
</c:if>
<!-- 翻页组件 -->



<script>

function exit(){
	//layer.msg("退出");
	//询问框
	
	layer.confirm('确定要退出系统么？', {
	  btn: ['退出','取消'] //按钮
	}, function(){
		//执行退出
	    //layer.msg("退出");
		location.href="TelLogin/TelLogin.jsp";
	}, function(){
	 return;
	});
	
	
}
function AddUserChooseData(){
	//alert("add");
	//alert("11111");
	document.getElementById("jumpApplyFrom").action="/zixinhai/HomeMethods";
	document.getElementById("jumpApplyFrom").submit();
//var obj=document.getElementById("jumpApplyFrom");
//	alert(obj);
}


function EditChooseLog(obj){
	//alert(obj.title);
	var userId =	document.getElementById("userId").value;
	location.href="HomeMethods?methods=EditUserChooseLog&&userId="+userId+"&&chooseLogId="+obj.title;
	
}
</script>

  
</body>


</html>
