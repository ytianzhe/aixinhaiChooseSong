<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
</head>
<body>


 <div class="main" style="position: fixed;z-index:999; width:100%;top:0;">
 
  <div class="index" style="height:50px">
              <div class="index_box">
                       <div class="index_icon">
                             <form id="jumpApplyFrom" action="" method="post">
  							<input type="hidden" value="${userId}"  id="userId" name="userId"/>
 								 <input type="hidden" value="jumpApplySongPagePage"   name="methods"/>
 									<button onclick="AddUserChooseData()">申请点歌</button>
 										</form>
                            			</div>
                                 
                                 </div>	
                                
                            </div>
                   </div>


</body>
</html>


<!--  <div class="index_icon" style="position: fixed;z-index:999; width:100%;height:30px" >
 <form id="jumpApplyFrom" action="" method="post">
  <input type="hidden" value="${userId}"  id="userId" name="userId"/>
  <input type="hidden" value="jumpApplySongPagePage"   name="methods"/>
 <button onclick="AddUserChooseData()">申请点歌</button>
 </form>
 </div> 
 -->