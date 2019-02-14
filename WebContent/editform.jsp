<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>xxx</title>
   <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script> 
 	<script src="layer/layer.js"></script>
    <link rel="stylesheet" href="css/editformstyle.css" media="screen" type="text/css" />
    <%
	String info = request.getParameter("info");
	String id = request.getParameter("id");
	String data = request.getParameter("data");
	String name = request.getParameter("name");
	%>

</head>
<body>
  <div class="container">  
  <form id="contact" action="" method="post">
 	 <h3><%=id%></h3>
    <fieldset>
      <input placeholder="歌曲名称" type="text" tabindex="1"   value="<%=data%>" readonly="readonly" />
    </fieldset>
     <fieldset>
      <input placeholder="歌手" type="text" tabindex="1"  value="<%=name%>"  readonly="readonly"/>
    </fieldset>
    <h4>附加信息</h4>
    <fieldset>
        <textarea rows="2" cols="20"  id="t1" name="additional" ><%=info%></textarea>
    </fieldset>
    
    
    <fieldset>
      <input type="hidden" name="chooseLogId" id="chooseLogId" value="<%=id%>" >
       <input type="hidden" name="methods" value="updateLogById" >
       <input type="hidden"  value="${msg}" >
    </fieldset>
 
   
    <fieldset>
      <button name="submit" type="submit" onclick="updateLoginStatus()">更新为已处理</button>
    </fieldset>
  </form>

</div>
<div style="text-align:center;clear:both">

</div>

<script>
function updateLoginStatus(){
	//alert("2");
//	close2();
//	document.getElementById("contact").action="/zixinhai/AixinhaiBackServlet";
//	document.getElementById("contact").submit();
var chooseLogId=document.getElementById("chooseLogId").value;
var additional=document.getElementById("t1").value;
	//ajax
	 $.ajax({  
         type:'post',  
         url:'/zixinhai/AixinhaiBackServlet?methods=updateLogById', 
         data:{"chooseLogId":chooseLogId,"additional":additional},
         cache:false,  
         dataType:'json',  
         success:function(data) {
			    	if(data.success)
			    	{
			    		
			    	}
			    	else
			    	{	
			    	}
			    }
     }); 
	
	 close2();
	 
}
function close2(){
	
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	//alert(index);
	 parent.layer.close(index);
	
	 window.parent.location.reload(); //刷新父页面
}
</script>
</body>

</html>

<!--此页中的 input 中的 required autofocus 可以检验  空-->