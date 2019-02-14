<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台页</title>
 <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script> 
 <script src="layer/layer.js"></script>
<link rel="stylesheet" href="frame/layui/css/layui.css">
 <link rel="stylesheet" href="frame/static/css/style.css">

 <link href="css/bootstrap.css" rel="stylesheet" media="screen">
 <script>
 function  filter(){
 var  returnDate= document.getElementById("returnDate").value;
 var  returnChooseUserId= document.getElementById("returnChooseUserId").value;
 var  returnNickNameId= document.getElementById("returnNickNameId").value;
 //alert(returnDate+"---"+returnChooseUserId);
 if(null!=returnDate){
	 if(returnDate.length!=0){
 document.getElementById("date").value=returnDate;}}
 if(null!=returnChooseUserId){
	 if(returnChooseUserId.length!=0){
 document.getElementById("chooseUserId").value=returnChooseUserId;}}
 
 if(null!=returnNickNameId){
	 if(returnNickNameId.length!=0){
 document.getElementById("nickNameId").value=returnNickNameId;}}
 }
 </script>

</head>
<body class="body" onload="filter()">

<div class="layui-row layui-col-space10 my-index-main">
    <div class="layui-col-xs4 layui-col-sm2 layui-col-md2">
        <div class="my-nav-btn layui-clear" data-href="./demo/btn.html">
            <div class="layui-col-md5">
                <button class="layui-btn layui-btn-big layui-btn-danger layui-icon">&#xe756;</button>
            </div>
            <div class="layui-col-md7 tc">
                <p class="my-nav-text">${UntreatedCount}</p>
                <p class="my-nav-text layui-elip">未处理的信息</p>
            </div>
        </div>
    </div>
    <div class="layui-col-xs4 layui-col-sm2 layui-col-md2">
        <div class="my-nav-btn layui-clear" >
            <div class="layui-col-md5">
                <button class="layui-btn layui-btn-big layui-btn-warm layui-icon">&#xe735;</button>
            </div>
            <div class="layui-col-md7 tc">
                <p class="my-nav-text">${treatedCount}</p>
                <p class="my-nav-text layui-elip">已处理的信息</p>
            </div>
        </div>
    </div>
    <div class="layui-col-xs4 layui-col-sm2 layui-col-md2">
        <div class="my-nav-btn layui-clear" >
            <div class="layui-col-md5">
                <button class="layui-btn layui-btn-big layui-icon">&#xe715;</button>
            </div>
            <div class="layui-col-md7 tc">
                <p class="my-nav-text">${undoCount}</p>
                <p class="my-nav-text layui-elip">已经撤销的信息数量</p>
            </div>
        </div>
    </div>
     <div class="layui-col-xs4 layui-col-sm2 layui-col-md2">
        <div class="my-nav-btn layui-clear" data-href="./demo/folding-panel.html">
            <div class="layui-col-md5">
                <button class="layui-btn layui-btn-big layui-bg-black layui-icon">&#xe698;</button>
            </div>
            <div class="layui-col-md7 tc">
                <p class="my-nav-text">${AllMessageCount}</p>
                <p class="my-nav-text layui-elip">总数量</p>
            </div>
        </div>
    </div>
    
    <div class="layui-col-xs4 layui-col-sm2 layui-col-md2">
        <div class="my-nav-btn layui-clear" data-href="./demo/tab-card.html">
            <div class="layui-col-md5">
                <button class="layui-btn layui-btn-big layui-btn-normal layui-icon">&#xe705;</button>
            </div>
            <div class="layui-col-md7 tc">
                <p class="my-nav-text">${curPage}</p>
                <p class="my-nav-text layui-elip">当前页数</p>
            </div>
        </div>
    </div>
    <div class="layui-col-xs4 layui-col-sm2 layui-col-md2">
        <div class="my-nav-btn layui-clear" data-href="./demo/progress-bar.html">
            <div class="layui-col-md5">
                <button class="layui-btn layui-btn-big layui-bg-cyan layui-icon">&#xe6b2;</button>
            </div>
            <div class="layui-col-md7 tc">
                <p class="my-nav-text">${row}</p>
                <p class="my-nav-text layui-elip">当前行数</p>
            </div>
        </div>
    </div>
   
  
    <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
        <div class="layui-collapse">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">后台的数据</h2>
                <!-- 筛选列表 start-->
                
     <div class="layui-form-item">
       <form action=""  method="post" id="ScreeningForm" >
        <div class="layui-inline">
            <label class="layui-form-label">日期</label>
            <div class="layui-input-inline">
                <input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" >
            </div>
        </div>
        <!--  
         <div class="layui-inline">
            <label class="layui-form-label">用户</label>
            <div class="layui-input-inline">
                <select name="chooseUserId" lay-verify="required" lay-search="" class="layui-input"  id="chooseUserId" >
                   <option value="">请选择问题</option>
                    <optgroup label="用户名称">
                    
                       <c:if test="${not empty MainDatalessonUserNameList}">
                        <c:forEach items="${MainDatalessonUserNameList}" var="mdlul" varStatus="vs" begin="0">
                         <option value="${mdlul.id}">${mdlul.chooseUserName}</option>
                 		 </c:forEach>
                 		 </c:if>
                        
                    </optgroup>
                    <optgroup label="用户昵称">
                        <c:if test="${not empty MainDatalessonNickNameList}">
                        <c:forEach items="${MainDatalessonNickNameList}" var="mdlnnl" varStatus="vs" begin="0">
                         <option value="${mdlnnl.id}">${mdlnnl.nickName}</option>
               			   </c:forEach>
                			  </c:if>
                       
                    </optgroup>
                </select>
            </div>
       
        </div>
        -->
        
         <div class="layui-inline">
            <label class="layui-form-label">用户</label>
            <div class="layui-input-inline">
                <select name="chooseUserId" lay-verify="required" lay-search="" class="layui-input"  id="chooseUserId" >
                    <option value="">----请选择客户-----</option>
                   <c:if test="${not empty MainDatalessonUserNameList}">
                        <c:forEach items="${MainDatalessonUserNameList}" var="mdlul" varStatus="vs" begin="0">
                         <option value="${mdlul.id}">${mdlul.chooseUserName}</option>
                  </c:forEach>
                  </c:if>
                </select>
            </div>
        </div>
        
        
          <div class="layui-inline">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-inline">
                <select name="nickNameId" lay-verify="required" lay-search="" class="layui-input"  id="nickNameId" >
                    <option value="">----请选择昵称----</option>
                   <c:if test="${not empty MainDatalessonNickNameList}">
                        <c:forEach items="${MainDatalessonNickNameList}" var="mdlnnl" varStatus="vs" begin="0">
                         <option value="${mdlnnl.id}">${mdlnnl.nickName}</option>
                  </c:forEach>
                  </c:if>
                </select>
            </div>
        </div>
       
       
       
  
       
        <div class="layui-inline">
            <div class="layui-input-block">
             <input type="hidden" name="methods" value="screen" />
            <button class="layui-btn"  id="screenButton" onclick="screenSend()">搜索</button>
            <button type="reset" class="layui-btn layui-btn-primary" onclick="reset()">重置</button>
      	  </div>
        </div>
       
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="hidden" class="layui-input" value="${date}" id="returnDate"/>
            </div>
              <div class="layui-input-inline">
                 <input type="hidden" class="layui-input" value="${chooseUserId}" id="returnChooseUserId"/>
            </div>
            <div class="layui-input-inline">
                 <input type="hidden" class="layui-input" value="${chooseUserWeiXin}" id="returnChooseUserWeiXin"/>
            </div>
              <div class="layui-input-inline">
                 <input type="hidden" class="layui-input" value="${nickNameId}" id="returnNickNameId"/>
            </div>
        </div>
       </form>
       
       
       
       
    </div>
                
                
                
                <!-- 筛选列表 end-->
                <div class="layui-colla-content layui-show">

                    <table class="layui-table">
                        <colgroup>
                            <col width="150">
                            <col width="200">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                             <th>序号</th>
                       <!--    <th>id</th> -->
                             <th>点歌昵称</th>
                             <th>点歌人</th>
                             <th>所属单位</th>
                             <th>点歌名称</th>
                             <th>歌手名称</th>
                             <th>用户祝福</th>
                             <th>用户手机</th>
                             <th>添加时间</th>
                             <th>状态</th>
                             <th>更新的信息</th>
                             <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty MainDatalessonList}">
                        <c:forEach items="${MainDatalessonList}" var="mdll" varStatus="vs" begin="0">
                        <tr>
                          	<td><c:out value="${(vs.index + 1+(curPage-1) * row)}" escapeXml="false" /></td>
                     <!--         <td><c:out value="${mdll.id}" escapeXml="false" /></td>  -->
                            <td><c:out value="${mdll.nickName}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.chooseUserName}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.chooseDepartment}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.chooseSongName}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.chooseSingerName}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.chooseWish}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.chooseUserTelNumber}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.firstAddTime}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.statusValue}" escapeXml="false" /></td>
                            <td><c:out value="${mdll.information}" escapeXml="false" /></td>
                            <td>
                            <c:if test="${mdll.status==1}"> 
      							<button  class="layui-btn layui-btn-normal layui-btn-radius" onclick="editStatus(this)" value="${mdll.chooseSongName}" id="${mdll.id}" name="${mdll.chooseSingerName}"  >编辑</button>
  							</c:if>
                             <c:if test="${mdll.status==2}"> 
      							<button  class="layui-btn layui-btn-warm layui-btn-radius" onclick="editStatus(this)" value="${mdll.chooseSongName}" id="${mdll.id}" name="${mdll.chooseSingerName}" title="${mdll.information}" >修改</button>
  							</c:if>
                            <c:if test="${mdll.status==3}"> 
      							<button  class="layui-btn layui-btn-disabled layui-btn-radius" onclick="editStatus(this)" value="${mdll.id}" >编辑</button>
  							</c:if>
                            </td>
                        </tr>
                        </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
 </div>
</div>
<!-- 翻页组件 -->
<div style="height:60px;position:relative;" >
	<div style="text-align: center;">第${curPage}页/共${pageCount}页       </div>
		<div  style="text-align: right;margin-right:10%" >
		<nav aria-label="Page navigation"  >
  		<ul class="pagination" >
  		  <li>
     		 <a  style="border: 1px solid #ddd;" href="Paging?methods=HomePage&&curPage=1&&pageCount=${pageCount}&&label=AdminPage&&date=${date}&&chooseUserId=${chooseUserId}&&nickNameId=${nickNameId}"   aria-label="Previous">
       	 	<span aria-hidden="true" style="color:#38acff" >&laquo;</span>
     	 </a>
   	 	</li>
    		<li><a  style="border: 1px solid #ddd;" href="Paging?methods=PreviousPage&&curPage=1&&pageCount=${pageCount}&&label=AdminPage&&date=${date}&&chooseUserId=${chooseUserId}&&nickNameId=${nickNameId}"><span style="color:#38acff"><</span> </a></li>
   		<c:forEach var="pageIndex" begin="1" end="${pageCount}">
			<c:choose>
		<c:when test="${pageIndex==1}">
			<c:choose>
				<c:when test="${curPage== pageIndex}">
				<li><a  style="border:1px solid #ddd;background-color: #38acff;color:white">${pageIndex}</a></li>
				</c:when>
				<c:otherwise>
				<li><a  style="border:1px solid #ddd;" href="Paging?methods=null&&curPage=${pageIndex}&&pageCount=${pageCount}&&label=AdminPage&&date=${date}&&chooseUserId=${chooseUserId}&&nickNameId=${nickNameId}">${pageIndex}</a></li>
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
						<li><a   style="border:1px solid #ddd;" href="Paging?methods=null&&curPage=${pageIndex}&&pageCount=${pageCount}&&label=AdminPage&&date=${date}&&chooseUserId=${chooseUserId}&&nickNameId=${nickNameId}">${pageIndex}</a></li>
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
								<li>	<a  style="border:1px solid #ddd;" href="Paging?curPage=${pageIndex}&&pageCount=${pageCount}&&date=${date}&&chooseUserId=${chooseUserId}&&nickNameId=${nickNameId}" >${pageIndex}</a></li>
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
										<li>	<a   style="border: 1px solid #ddd;" href="Paging?methods=null&&curPage=${pageIndex}&&pageCount=${pageCount}&&label=AdminPage&&date=${date}&&chooseUserId=${chooseUserId}&&nickNameId=${nickNameId}">${pageIndex}</a></li>
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
	<li><a  style="border: 1px solid #ddd;" href="Paging?methods=NextPage&&curPage=${curPage}&&pageCount=${pageCount}&&label=AdminPage&&date=${date}&&chooseUserId=${chooseUserId}&&nickNameId=${nickNameId}"><span style="color:#38acff"> ></span> </a></li>
    		<li>
     			 <a  style="border: 1px solid #ddd;" href="Paging?methods=EndPage&&curPage=1&&pageCount=${pageCount}&&label=AdminPage&&date=${date}&&chooseUserId=${chooseUserId}&&nickNameId=${nickNameId}" aria-label="Next">
       			 <span aria-hidden="true" style="color:#38acff">&raquo;</span>
      			</a>
   		 	</li>
 		</ul>
	</nav>
	</div>
</div>
<!-- 翻页组件 -->
<script type="text/javascript" src="frame/layui/layui.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="frame/echarts/echarts.min.js"></script>
<script>
function reset(){
	$('#ScreeningForm').reset();
}

function screenSend(){
	document.getElementById("ScreeningForm").action="/zixinhai/AixinhaiBackServlet";
	document.getElementById("ScreeningForm").submit;
	
}
</script>



<script>
function editStatus(obj){
	//alert(obj.value);
	//layer.msg(obj.value);
	var data=obj.value;
	var id=obj.id;
	var name=obj.name;
	var info=obj.title;
//	alert("information:"+info+"---"+data+"-----"+id+"------"+name);
//	layer.msg(id);
	layer.open({
		type: 2,
		title: '编辑页面',
		shadeClose: false,
		shade: 0.5,
		area: ['30%','60%'],
		content: 'editform.jsp?id='+id+'&&data='+data+'&&name='+name+'&&info='+info,
		end: function () {
		//关闭弹框后事件 
		}
		});
	
}
</script>
<script>
layui.use(['form','laydate'], function(){
    var laydate = layui.laydate;
    //日期

    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#date1'
    });
});





</script>
</body>
</html>