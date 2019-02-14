<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<title>Insert title here</title>
<style>
	  th{
        text-align:center;/** 设置水平方向居中 */
        vertical-align:middle/** 设置垂直方向居中 */
    }
</style>
</head>
<body>
<button  id="btn_add" type="button"  >申请点歌</button>

<!--制作一个表单  -->
<c:if test="${not empty UserDataLessonList}">
<!--  有返回结果-->
<div  style="overflow:scroll;">
	<table class="table-bordered"  border="1" width="100%" height="120px" align="left">
			
				<tr>
					<th scope="row" >编号</th>
					<th>状态</th>
					<th >操作</th>
					<th >创建时间</th>
					<th>点歌人</th>
					<th>所在部门</th>
					<th >歌曲名称</th>
					<th >歌手名称</th>
					<th>祝福语句</th>
					<th >附加信息</th>
		 			 
				</tr>
				<c:forEach items="${UserDataLessonList}" var="udl" varStatus="vs" begin="0">
							<tr>
								<th ><c:out value="${(vs.index + 1+(curPage-1) * row)}"/><br /></th>
								<th><c:if test="${ udl.status=='1' }">  <p>未锁定 <p></c:if>
								 <c:if test="${ udl.status=='2' }"> <p>已锁定 <p></c:if></th>
							<th>
      						<button class="btn btn-default" type="button" id="${udl.id}"  value="${udl.status}" onclick="edit(this)">编辑</button>
							<c:if test="${l.status=='1'}"> 
      								<button class="btn btn-default" type="button" id="${udl.id}"  value="${udl.status}" onclick="lock(this)">锁定</button>
  							</c:if>
						
							<c:if test="${l.status=='2'}"> 
      							<button class="btn btn-default" type="button" id="${udl.id}"  value="${udl.status}" onclick="unlock(this)">解锁</button>
  							</c:if>
  								<button class="btn btn-default" type="button" id="${udl.id}"  value="${udl.status}" onclick="unlock(this)">撤销</button>
  							
							<br /></th>
							<th>
							<c:if test="${not empty udl.firstAddTime}">
								<fmt:formatDate value="${udl.firstAddTime}" pattern="yyyy-MM-dd" />
								<br />
							</c:if>
							</th>
							<th><c:out value="${udl.chooseUserName}" escapeXml="false" /><br /></th>
							<th><c:out value="${udl.chooseDepartment}" escapeXml="false" /><br /></th>
							<th><c:out value="${udl.choosesSongName}" escapeXml="false" /><br /></th>
							<th><c:out value="${udl.choosesSongSingerName}" escapeXml="false" /><br /></th>
							<th><c:out value="${udl.chooseWish}" escapeXml="false" /><br /></th>
					  		<th><c:out value="${udl.information}" escapeXml="false" /><br /></th>
							
						</tr>
				</c:forEach>
	</table>
</div>
</c:if>

<form action="Fenye" method="Post" target="mainRight">
		 
 		  <input type="hidden"  id="msg"  value="${msg}"  />
		 
		 <!--  
		查询出 <input type="text"   name="allsize"  value="${allsize}" /> 条记录,
		每页 <input type="text"   name="rows"  value="${row}" />条
		共<input type="text"   name="pageCount"  value="${pageCount}" />页
		当前 <input type="text"   name="nowpage"  value="${curPage}" />页
		-->
		<!--  查询出${allsize}, 条记录,每页${row}条 ,共${pageCount}页,当前${curPage}页-->
	<!--	<div style="text-align: center;">	第${curPage}页/共${pageCount}页</div>-->
</form>

<!--新2的翻页-->
<div style="height:60px;position:relative;" >
	<div style="text-align: center;">第${curPage}页/共${pageCount}页       </div>
<!-- 翻页组件 -->

	<div  style="text-align:right;margin-right:10%" >
		<nav aria-label="Page navigation"  >
  		<ul class="pagination" >
  		  <li>
     		 <a  style="border: 1px solid #ddd;" href="Fenye?method=first&&curPage=1&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}"  target="employeeright" aria-label="Previous">
       	 	<span aria-hidden="true" style="color:#38acff" >&laquo;</span>
     	 </a>
   	 	</li>
    		<li><a  style="border: 1px solid #ddd;" href="Fenye?method=up&&curPage=${curPage}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright"><span style="color:#38acff"><</span> </a></li>
   		<c:forEach var="pageIndex" begin="1" end="${pageCount}">
			<c:choose>
		<c:when test="${pageIndex==1}">
			<c:choose>
				<c:when test="${curPage== pageIndex}">
				<li><a  style="border:1px solid #ddd;background-color: #38acff;color:white">${pageIndex}</a></li>
				</c:when>
				<c:otherwise>
				<li><a  style="border:1px solid #ddd;" href="Fenye?curPage=${pageIndex}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright">${pageIndex}</a></li>
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
						<li><a   style="border:1px solid #ddd;" href="Fenye?curPage=${pageIndex}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright">${pageIndex}</a></li>
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
										<li>	<a   style="border: 1px solid #ddd;" href="Fenye?curPage=${pageIndex}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright">${pageIndex}</a></li>
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
	<li><a  style="border: 1px solid #ddd;" href="Fenye?method=down&&curPage=${curPage}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright"><span style="color:#38acff"> ></span> </a></li>
    		<li>
     			 <a  style="border: 1px solid #ddd;" href="Fenye?method=last&&curPage=${curPage}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright" aria-label="Next">
       			 <span aria-hidden="true" style="color:#38acff">&raquo;</span>
      			</a>
   		 	</li>
 		</ul>
	</nav>
	</div>
</div>



<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">新增</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="txt_departmentname">部门名称</label>
                        <input type="text" name="txt_departmentname" class="form-control" id="txt_departmentname" placeholder="部门名称">
                    </div>
                    <div class="form-group">
                        <label for="txt_parentdepartment">上级部门</label>
                        <input type="text" name="txt_parentdepartment" class="form-control" id="txt_parentdepartment" placeholder="上级部门">
                    </div>
                    <div class="form-group">
                        <label for="txt_departmentlevel">部门级别</label>
                        <input type="text" name="txt_departmentlevel" class="form-control" id="txt_departmentlevel" placeholder="部门级别">
                    </div>
                    <div class="form-group">
                        <label for="txt_statu">描述</label>
                        <input type="text" name="txt_statu" class="form-control" id="txt_statu" placeholder="状态">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                    <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
                </div>
            </div>
        </div>
    </div>

     
    

<script>
function addSong(){
alert("11");
//弹出层
}

$("#btn_add").click(function () {
   //    $("#myModalLabel").text("新增");
   //window.parent.document.getElementById("myModal").modal();
	    $('#myModal').modal();
	 });
</script>>
</body>
</html>