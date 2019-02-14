package com.org.ixinhai.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.sql.SqlMethods;
import com.org.ixinhai.until.AppMethods;
import com.org.ixinhai.until.BackgroundMethods;

/**
 * Servlet implementation class Paging
 */
@WebServlet("/Paging")
public class Paging extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paging() {
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
	try{
		double row=15;
		double pageCount=0;
		int AllCount=0;
		String userId=request.getParameter("userId");
		String label=request.getParameter("label");
		String methods=request.getParameter("methods");
		int curPage=Integer.parseInt(request.getParameter("curPage"));
		pageCount=Double.parseDouble(request.getParameter("pageCount"));
		curPage=AppMethods.PagingClass(methods, curPage,(int)pageCount);
		
		switch(label){
		case "UserPage":
		
			
			List<UserApplication> lessonList= new ArrayList<UserApplication>();
			lessonList=SqlMethods.SearchUserDateByUserID(Integer.parseInt(userId),curPage,15);
			request.setAttribute("UserDataLessonList", lessonList);
			
			//分页的数据
			int UserAllcount=AppMethods.SearchCountByUserId(userId);
			pageCount=(int)Math.ceil(UserAllcount/row);
			request.setAttribute("curPage", curPage);
			request.setAttribute("pageCount",(int)pageCount);
			request.getRequestDispatcher("UserMainRight.jsp").forward(request, response);
			
			break;
			
			
		case "AdminPage":
			//获取数据
			//获取分页的三个条件返回
			String date=request.getParameter("date");
			String chooseUserId=request.getParameter("chooseUserId");
			String nickNameId=request.getParameter("nickNameId");
			System.out.println("date:"+date+"chooseUserId:"+chooseUserId+"nickNameId:"+nickNameId);
			
			request.setAttribute("date", date);
			request.setAttribute("chooseUserId", chooseUserId);
			request.setAttribute("nickNameId", nickNameId);
			//获取分页的三个条件返回 end
			 String conditions="";
			 conditions=BackgroundMethods.JoiningTogether(date,chooseUserId,nickNameId);
			//获取主要数据
			 LinkedHashMap<String, Object> MainDataMap =  new LinkedHashMap<String, Object>();	
			 System.out.println(1);
			 String UntreatedCount="0";
			 String treatedCount="0";
			 String undoCount="0";
			 String AllMessageCount="0";
			 //获取的方法
			 System.out.println("当前的curPage："+curPage);
			 MainDataMap=BackgroundMethods.getDealDataByCurPageAndConditions(curPage,(int)row,conditions);

	//		 System.out.println("undoCount:"+MainDataMap.get("undoCount"));
			 List<UserApplication> MainDatalessonList= new ArrayList<UserApplication>();
			 Object obj =MainDataMap.get("MainDatalessonList");
		
			 MainDatalessonList = (ArrayList<UserApplication>)obj;//obj->list 警告 可以不用理会 因为这个obj是list转换来的 必然可以转换
//			for(int i=0;i<MainDatalessonList.size();i++){
//				System.out.println(MainDatalessonList.get(i).getChooseSingerName());
//			}
			 UntreatedCount=  MainDataMap.get("UntreatedCount").toString();
			 treatedCount=  MainDataMap.get("treatedCount").toString();
			 undoCount=  MainDataMap.get("undoCount").toString();
			 AllMessageCount=  MainDataMap.get("AllMessageCount").toString();
			 
			 request.setAttribute("UntreatedCount", UntreatedCount);
			 request.setAttribute("treatedCount",treatedCount);
			 request.setAttribute("undoCount", undoCount);
			 request.setAttribute("AllMessageCount", AllMessageCount);
			//分页的配置
//			 AllCount=BackgroundMethods.SearchDealDateALllCount();
			 AllCount=BackgroundMethods.SearchDealDateCountByConditions(conditions);
			 request.setAttribute("curPage", curPage);
			 request.setAttribute("row", 15);
			 pageCount=(int)Math.ceil(AllCount/row);
			 request.setAttribute("pageCount",(int)pageCount);
			 request.setAttribute("MainDatalessonList",MainDatalessonList);
			 
			 
			 //分页之后返回两个列表
			 //获取两个查询列表
			 LinkedHashMap<String,  List<UserApplication>> linkedHashMap =  new LinkedHashMap<String, List<UserApplication>>();	
			 linkedHashMap=BackgroundMethods.getUserNameandNickNameList();
			 List<UserApplication> MainDatalessonUserNameList= new ArrayList<UserApplication>();
			 List<UserApplication> MainDatalessonNickNameList= new ArrayList<UserApplication>();
			 MainDatalessonUserNameList=linkedHashMap.get("MainDatalessonUserNameList");
			 MainDatalessonNickNameList=linkedHashMap.get("MainDatalessonNickNameList");	
			 request.setAttribute("MainDatalessonUserNameList", MainDatalessonUserNameList);
			 request.setAttribute("MainDatalessonNickNameList",MainDatalessonNickNameList);
			 //获取两个查询列表end
			 
			 //分页之后返回两个列表end
			 request.getRequestDispatcher("Admin.jsp").forward(request, response);
			
			
			
			break;
		default:
			break;
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	
	}

}
