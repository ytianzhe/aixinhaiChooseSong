package com.org.ixinhai.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;

import com.alibaba.fastjson.JSON;
import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.entity.UserInformation;
import com.org.ixinhai.sql.SqlMethods;
import com.org.ixinhai.until.AppMethods;

/**
 * Servlet implementation class HomeMethods
 */
@WebServlet("/HomeMethods")
public class HomeMethods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeMethods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		System.out.println("进入home");
		String userId=null;
		String chooseLogId=null;
		String chooseUserName=null;
		String chooseUserTelNumber=null;
		String chooseDepartment=null;
		String chooseSongName=null;
		String chooseSingerName=null;
		String chooseWish=null;
		double row=15;
		int pageCount=0;
		boolean bl=false;
		 Map<String, Object> map = new LinkedHashMap<String, Object>();
		 String json=null;
		List<UserApplication> lessonList= new ArrayList<UserApplication>();
		List<UserInformation> UserlessonList= new ArrayList<UserInformation>();
		try{
			request.setCharacterEncoding("utf-8");
			String methods=request.getParameter("methods");
			System.out.println(methods);
			String msg=null;
			switch(methods){
			 //用户登入接口使用login
			case "AdminLogin":
				String AdminUserName=request.getParameter("AdminUserName");
				String AdminUserPassword=request.getParameter("AdminUserPassword");
				System.out.println("AdminUserName:"+AdminUserName+"----"+"AdminUserPassword:"+AdminUserPassword);
				//进行数据库验证   如果 是true就通过进入页面
				bl=SqlMethods.SearchName(AdminUserName,AdminUserPassword);
				System.out.println("bl:"+bl);
				if(bl!=true)
				{	
					msg="请输入正确的账号密码";
				}
				else{
					UserlessonList=SqlMethods.SearchUserInformation(AdminUserName, AdminUserPassword);	
					System.out.println(UserlessonList.get(0).getUserAuthority());
					if(UserlessonList.get(0).getUserAuthority().equals("Admin") ==false)
					{
						//是管理员 进行管理页面的跳转
						msg="对不起，你没有管理员的访问权限";
						
						
					}
					else if(UserlessonList.get(0).getUserAuthority().equals("Admin") ==true){
					//不是返回
						msg="管理员你好";
						request.getRequestDispatcher("Admin.jsp").forward(request, response);
						return;
					}
					else{
						msg="数据异常";
					}
				}
				System.out.println("msg:"+msg);
				request.setAttribute("msg", msg);
				//request.getRequestDispatcher("loginAdmin.jsp").forward(request, response);
				request.getRequestDispatcher("Admin.jsp").forward(request, response);
				
			break;
			
			case "Login":
				String UserName=request.getParameter("jobNumber");
				String UserPassword=request.getParameter("password");
				System.out.println(UserName+"----"+UserPassword);
				//进行数据库验证   如果 是true就通过进入页面
				 bl=SqlMethods.SearchName(UserName, UserPassword);
				System.out.println("bl:"+bl);
				if(bl==true)
				{
					System.out.println("有该账户开始跳转，获取userId");
					int userIdint=SqlMethods.SearchUserIdByUserNameAndPassWord(UserName, UserPassword);
					userId=String.valueOf(userIdint);
					//true 
					//进入用户的主页  返回用户userId 到左菜单   
					//request.getRequestDispatcher("/WEB-INF/Usermain.jsp").forward(request, response);
				//	response.sendRedirect("/zixinhai/WEB-INF/Usermain.jsp");
					System.out.println("userId:"+userId);
					request.getSession().setAttribute("userId",userId);
					//跳转测试页面
					
					
					lessonList=SqlMethods.SearchUserDateByUserID(Integer.parseInt(userId),1,15);
					request.setAttribute("UserDataLessonList", lessonList);
					
					//分页的数据
					int UserAllcount=AppMethods.SearchCountByUserId(userId);
					pageCount=(int)Math.ceil(UserAllcount/row);
					request.setAttribute("curPage", 1);
					request.setAttribute("pageCount", pageCount);
					request.getRequestDispatcher("UserMainRight.jsp").forward(request, response);
					//request.getRequestDispatcher("/WEB-INF/Usermain.jsp").forward(request, response);
				}
				else
				{
					//
					msg="请输入正常的账号密码";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("TelLogin/TelLogin.jsp").forward(request, response);
				
				}
				
				//如果你false就 不让登入 返回msg 请输入正确的账号密码
				break;
			
				
			case "AddChooseLog":
				LinkedHashMap<String, String> linkedHashMap =  new LinkedHashMap<String, String>();
				 userId=request.getParameter("userId");
				 chooseUserName=request.getParameter("chooseUserName");
				 chooseUserTelNumber=request.getParameter("chooseUserTelNumber");
				 chooseDepartment=request.getParameter("chooseDepartment");
				 chooseSongName=request.getParameter("chooseSongName");
				 chooseSingerName=request.getParameter("chooseSingerName");
				 chooseWish=request.getParameter("chooseWish");
				 
				 
				 
				 //测试歌曲是否 存在酷狗中
				 int count=AppMethods.SearchSongNameInKuGu(chooseSongName);
				System.out.println("count:--------"+count);
				if(count==0){
					msg="歌曲不在歌库中 无法点歌";
				}
				//打桩测试
//				System.out.println("userId:"+userId);
//				System.out.println("chooseUserName:"+chooseUserName);
//				System.out.println("chooseUserTelNumber:"+chooseUserTelNumber);
//				System.out.println("chooseDepartment:"+chooseDepartment);
//				System.out.println("chooseSongName:"+chooseSongName);
//				System.out.println("chooseSingerName:"+chooseSingerName);
//				System.out.println("chooseWish:"+chooseWish);
				//处理数据
				else{
					linkedHashMap=AppMethods.AddSongLog(userId,chooseUserName,chooseUserTelNumber,chooseDepartment,chooseSongName,chooseSingerName,chooseWish);
					System.out.println("处理结果："+linkedHashMap.get("msg").toString());
					msg=linkedHashMap.get("msg").toString(); 
				}
				
				 
				 
				 
				 
				
				//返回结果
				//分页的数据
				int UserAllcount=AppMethods.SearchCountByUserId(userId);
				pageCount=(int)Math.ceil(UserAllcount/row);
				request.setAttribute("msg", msg);
				request.setAttribute("curPage", 1);
				request.setAttribute("pageCount", pageCount);
				lessonList=SqlMethods.SearchUserDateByUserID(Integer.parseInt(userId),1,15);
				request.setAttribute("UserDataLessonList", lessonList);
				request.setAttribute("userId", userId);
				request.getRequestDispatcher("UserMainRight.jsp").forward(request, response);
				
				 
				
				
				
//				String userId=request.getParameter("userId");
//				if(userId==null||userId==""||userId.length()==0){
//					msg="userId缺失 申请失败  ！";
//				}
//				else{
//				
//				
//				
//				String ChooseUserName=request.getParameter("ChooseUserName");
//				String ChooseDepartment=request.getParameter("ChooseDepartment");
//				String ChooseSongName=request.getParameter("ChooseSongName");
//				String ChooseSingerName=request.getParameter("ChooseSingerName");
//				
//				System.out.println("ChooseUserName:"+ChooseUserName);
//				System.out.println("ChooseDepartment:"+ChooseDepartment);
//				System.out.println("ChooseSongName:"+ChooseSongName);
//				System.out.println("ChooseSingerName:"+ChooseSingerName);
//				System.out.println("userId:"+userId);
//				
//				}
			break;
			case "jumpApplySongPagePage":
				System.out.println("已经进入jumpApplySongPagePage");
				userId=request.getParameter("userId");
				request.setAttribute("userId", userId);
				List<UserInformation> userNameandTelLessonList= new ArrayList<UserInformation>();
				userNameandTelLessonList=SqlMethods.SearchUserNameAndTelByUserId(userId);
				String Name=userNameandTelLessonList.get(0).getEmployeeName();
				String tel=userNameandTelLessonList.get(0).getEmployeeTelNumber();
				//获取用户的名字和手机号然后返回
				request.setAttribute("chooseUserName", Name);
				request.setAttribute("chooseUserTelNumber", tel);
				request.getRequestDispatcher("user/applySongPage2.jsp").forward(request, response);
				break;
			case "EditUserChooseLog":
				userId=request.getParameter("userId");
				chooseLogId=request.getParameter("chooseLogId");
				System.out.println("userId:"+userId+",chooseLogId:"+chooseLogId);
				
				//获取log信息
				List<UserApplication> chooseLogLessonList= new ArrayList<UserApplication>();
				chooseLogLessonList=AppMethods.SearchChooseLogByLogid(chooseLogId);
				request.setAttribute("userId", userId);
				request.setAttribute("chooseLogId", chooseLogId);
				request.setAttribute("chooseLogLessonList", chooseLogLessonList);
				request.getRequestDispatcher("user/applySongPageEdit2.jsp").forward(request, response);
				break;	
				
			case "UpdateChooseLog":
				System.out.println("开始更新数据");
				userId=request.getParameter("userId");
				chooseLogId=request.getParameter("chooseLogId");
				//获取更新的log信息
				 chooseUserName=request.getParameter("chooseUserName");
				 chooseUserTelNumber=request.getParameter("chooseUserTelNumber");
				 chooseDepartment=request.getParameter("chooseDepartment");
				 chooseSongName=request.getParameter("chooseSongName");
				 chooseSingerName=request.getParameter("chooseSingerName");
				 chooseWish=request.getParameter("chooseWish");
				 
				 
				AppMethods.UpdateChooseLogbyid(chooseUserName, chooseDepartment, chooseSongName, chooseSingerName, chooseWish, chooseUserTelNumber, userId, chooseLogId);
				
				
				
				//更新完成 然后返回用户的界面
				
			
				lessonList=SqlMethods.SearchUserDateByUserID(Integer.parseInt(userId),1,15);
				request.setAttribute("UserDataLessonList", lessonList);
				request.setAttribute("userId", userId);
				request.setAttribute("msg", "修改成功");
				request.getRequestDispatcher("UserMainRight.jsp").forward(request, response);
				break;	
				
				case "repealChooseLog":
					userId=request.getParameter("userId");
					chooseLogId=request.getParameter("chooseLogId");
					System.out.println("userId:"+userId+" chooseLogId:"+chooseLogId);
					
					//调用撤销的方法
					AppMethods.UpdatelogStatusbyChooseId(userId, chooseLogId);
					
					lessonList=SqlMethods.SearchUserDateByUserID(Integer.parseInt(userId),0,15);
					request.setAttribute("UserDataLessonList", lessonList);
					request.setAttribute("userId", userId);
					request.getRequestDispatcher("UserMainRight.jsp").forward(request, response);
				break;
				
				case "SearchChooseLogStatus":
				    chooseLogId=request.getParameter("chooseLogId");
					String status=SqlMethods.SearchlogStatusbyChooseId(chooseLogId);
					map.put("success", true);
					map.put("status", status);
					// map.put("mouldlessonList", mouldlessonList);
					 json=JSON.toJSONString(map);  
					 System.out.println(json);//输出{"a":"aaa","b":"bbb","c":"ccc"}   
					 response.setContentType("text/javascript;charset=utf-8");
					 response.setCharacterEncoding("utf-8");
					 response.getWriter().print(json);		
				break;
			  }
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	
	}

}
