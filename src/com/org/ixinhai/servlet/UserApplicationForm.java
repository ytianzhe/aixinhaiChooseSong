package com.org.ixinhai.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.sql.SqlMethods;
/**
 * Servlet implementation class UserApplicationForm
 */
@WebServlet("/UserApplicationForm")
public class UserApplicationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserApplicationForm() {
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
			//用于获取用户的点歌数据   根据的userId
			String userId=request.getParameter("userId");
			int allsize=0;
			double pageCount=0;
			double row=15;
			//
			List<UserApplication> lessonList= new ArrayList<UserApplication>();
			lessonList=SqlMethods.SearchUserDateByUserID(Integer.parseInt(userId),0,15);
			allsize=SqlMethods.SearchUserDateCountByUserID(Integer.parseInt(userId));
			pageCount=(int)Math.ceil(allsize/row);
			
			request.setAttribute("allsize", allsize);//总数量 
			request.setAttribute("curPage",1); //当前页
			request.setAttribute("pageCount", (int)pageCount); //总页数
			request.setAttribute("row", (int)row);
			request.setAttribute("userId", userId);
			request.setAttribute("UserDataLessonList", lessonList);
			request.getRequestDispatcher("UserDataPage.jsp").forward(request, response);
			
			//跳转测试页面
			//request.getRequestDispatcher("UserMainRight.jsp").forward(request, response);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}
