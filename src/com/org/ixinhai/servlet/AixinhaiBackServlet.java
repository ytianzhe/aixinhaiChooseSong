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

import com.alibaba.fastjson.JSON;
import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.sql.BackgroundSql;
import com.org.ixinhai.until.BackgroundMethods;

/**
 * Servlet implementation class AixinhaiBackServlet
 */
@WebServlet("/AixinhaiBackServlet")
public class AixinhaiBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AixinhaiBackServlet() {
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
		
		int AllCount=0;
		double pageCount=0;
		double row=15;
		String methods=request.getParameter("methods");
		System.out.println(methods);
		
		switch(methods){
		
		case "getMainData":
			System.out.println(3);
			//获取主要的信息  以map的形式 获取     //所有的状态的信息    有未处理时间的总数  有已经处理的事件总数  有各种need的后续数据  可以分块获取 保证后续的要求
			//开始获取数据  从自己的方法类里获取
			 LinkedHashMap<String, Object> MainDataMap =  new LinkedHashMap<String, Object>();	
			 System.out.println(1);
			 String UntreatedCount="0";
			 String treatedCount="0";
			 String undoCount="0";
			 String AllMessageCount="0";
			
			 //获取的方法
			 MainDataMap=BackgroundMethods.getDealData();

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
			 AllMessageCount=MainDataMap.get("AllMessageCount").toString();
			 request.setAttribute("UntreatedCount", UntreatedCount);
			 request.setAttribute("treatedCount",treatedCount);
			 request.setAttribute("undoCount", undoCount);
			 request.setAttribute("AllMessageCount", AllMessageCount);
			 //获取两个查询列表
			 LinkedHashMap<String,  List<UserApplication>> linkedHashMap =  new LinkedHashMap<String, List<UserApplication>>();	
			 linkedHashMap=BackgroundMethods.getUserNameandNickNameList();
			 List<UserApplication> MainDatalessonUserNameList= new ArrayList<UserApplication>();
			 List<UserApplication> MainDatalessonNickNameList= new ArrayList<UserApplication>();
			 MainDatalessonUserNameList=linkedHashMap.get("MainDatalessonUserNameList");
			 MainDatalessonNickNameList=linkedHashMap.get("MainDatalessonNickNameList");	
			 request.setAttribute("MainDatalessonUserNameList", MainDatalessonUserNameList);
			 request.setAttribute("MainDatalessonNickNameList",MainDatalessonNickNameList);
			 
			//分页的配置
			 AllCount=BackgroundMethods.SearchDealDateALllCount();
			 request.setAttribute("curPage", 1);
			 request.setAttribute("row", 15);
			 pageCount=(int)Math.ceil(AllCount/row);
			 request.setAttribute("pageCount", (int)pageCount);
			 request.setAttribute("MainDatalessonList",MainDatalessonList);
			 request.getRequestDispatcher("Admin.jsp").forward(request, response);
			break;
		 case "updateLogById":
		//获取ajax中的data
//			 String chooseLogId=request.getParameter("chooseLogId");
//			 String additional="";
//			 additional=request.getParameter("additional");
//			 System.out.println("chooseLogId"+chooseLogId+" additional:"+additional);
			 
			 
			 
			 String additional="";
			 String chooseLogId=request.getParameter("chooseLogId");
			 if(request.getParameter("additional")!=null){
				  additional=request.getParameter("additional");
				 System.out.println("additional:"+additional);
			 }
			 System.out.println("chooseLogId:"+chooseLogId);
			 
			 //更新的状态和信息
			 BackgroundMethods.UpdatelogStatusbyChooseId(chooseLogId, additional);
			 //更新后发送短信提示客户
			 LinkedHashMap<String, Object> map =  new LinkedHashMap<String, Object>();	
			 map=BackgroundMethods.SendMsg(chooseLogId, "ProcessingRemind");
			 request.setAttribute("msg", "更新完毕");
			 request.getRequestDispatcher("editform.jsp").forward(request, response);
			// Map<String, Object> map = new LinkedHashMap<String, Object>();
//			 map.put("success", true);
//			 map.put("msg", "更新完毕");
//			 String json=JSON.toJSONString(map);  
//			 System.out.println(json);//输出{"a":"aaa","b":"bbb","c":"ccc"}   
//			 response.setContentType("text/javascript;charset=utf-8");
//			 response.setCharacterEncoding("utf-8");
//			 response.getWriter().print(json);		
			break;
			
		 case "screen":
			 String newdate="";
			 if(request.getParameter("date").length()!=0||request.getParameter("date")!=null||request.getParameter("date")!=""){
				 newdate= request.getParameter("date");
				 System.out.println("这次获取到的日期:"+newdate);
				 request.setAttribute("date", newdate);
				// System.out.println(request.getParameter("date").length());
			 }
			 
			 String chooseUserId=""; 
			 if(request.getParameter("chooseUserId")!=null||request.getParameter("chooseUserId")!=""||request.getParameter("chooseUserId").length()!=0){
				 chooseUserId= request.getParameter("chooseUserId");
				 System.out.println("这次获取到的用户Id："+chooseUserId);
				 request.setAttribute("chooseUserId", chooseUserId);
			 }
			 String nickNameId=""; 
			 if(request.getParameter("nickNameId")!=null||request.getParameter("nickNameId")!=""||request.getParameter("nickNameId").length()!=0){
				 nickNameId= request.getParameter("nickNameId");
				 System.out.println("这次获取到的用户昵称对应的用户Id："+nickNameId);
				 request.setAttribute("nickNameId", nickNameId);
			 }
			 String conditions="";
			 conditions=BackgroundMethods.JoiningTogether(newdate,chooseUserId,nickNameId);
			 //获取带条件查询的数据
			 
			 
			 
			 LinkedHashMap<String, Object> MainDataMapByConditionsMap =  new LinkedHashMap<String, Object>();	
			 //获取的方法
			 MainDataMapByConditionsMap=BackgroundMethods.getDealDataByCurPageAndConditions(1, 15, conditions);
			 List<UserApplication> MainDataByConditionslessonList= new ArrayList<UserApplication>();
			 Object objbyc =MainDataMapByConditionsMap.get("MainDatalessonList");
			 MainDataByConditionslessonList = (ArrayList<UserApplication>)objbyc;//obj->list 警告 可以不用理会 因为这个obj是list转换来的 必然可以转换

			 UntreatedCount=  MainDataMapByConditionsMap.get("UntreatedCount").toString();
			 treatedCount=  MainDataMapByConditionsMap.get("treatedCount").toString();
			 undoCount=  MainDataMapByConditionsMap.get("undoCount").toString();
			 AllMessageCount=  MainDataMapByConditionsMap.get("AllMessageCount").toString();
			 request.setAttribute("UntreatedCount", UntreatedCount);
			 request.setAttribute("treatedCount",treatedCount);
			 request.setAttribute("undoCount", undoCount);
			 request.setAttribute("AllMessageCount", AllMessageCount);
			 //获取两个查询列表
			 linkedHashMap =  new LinkedHashMap<String, List<UserApplication>>();	
			 linkedHashMap=BackgroundMethods.getUserNameandNickNameList();
			 MainDatalessonUserNameList= new ArrayList<UserApplication>();
			 MainDatalessonNickNameList= new ArrayList<UserApplication>();
			 MainDatalessonUserNameList=linkedHashMap.get("MainDatalessonUserNameList");
			 MainDatalessonNickNameList=linkedHashMap.get("MainDatalessonNickNameList");	
			 request.setAttribute("MainDatalessonUserNameList", MainDatalessonUserNameList);
			 request.setAttribute("MainDatalessonNickNameList",MainDatalessonNickNameList);
			//分页的配置
			 
			 AllCount=BackgroundMethods.SearchDealDateCountByConditions(conditions);
			 
			 System.out.println("AllCount:"+AllCount);
			 request.setAttribute("curPage", 1);
			 request.setAttribute("row", 15);
			 pageCount=(int)Math.ceil(AllCount/row);
			 request.setAttribute("pageCount", (int)pageCount);
			 request.setAttribute("MainDatalessonList",MainDataByConditionslessonList);
			 request.getRequestDispatcher("Admin.jsp").forward(request, response);
			break;
			default:
			break;
		
		
		  }
		
		}catch (Exception e) {
			e.printStackTrace();
	}
	
	}

}
