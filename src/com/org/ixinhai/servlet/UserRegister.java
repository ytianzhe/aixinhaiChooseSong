package com.org.ixinhai.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.ixinhai.until.AppMethods;
import com.org.ixinhai.until.BackgroundMethods;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	
	try{
		
		request.setCharacterEncoding("utf-8");
		String msg="";
		String realName=request.getParameter("realName");
		String jobNumber=request.getParameter("jobNumber");
		String password=request.getParameter("password");
		
		
		int states=1;
		
		
		
		String openid=request.getParameter("openid");
		String unionid=request.getParameter("unionid");
		int isload=2;//1 不自动 登入  2 自动登入 
		String mobile=request.getParameter("mobile");
		
		
		//获取nickName
				String nickName=BackgroundMethods.GetNickNameByJobNumber(jobNumber, openid, unionid);
		//获取nickName  end
		System.out.println("realName:"+realName);
		System.out.println("jobNumber:"+jobNumber);
		System.out.println("password:"+password);
	//	System.out.println("now:"+now);
		System.out.println("states:"+states);
		System.out.println("openid:"+openid);
		System.out.println("unionid:"+unionid);
		System.out.println("isload:"+isload);
		System.out.println("mobile:"+mobile);
		System.out.println("nickName:"+nickName);
		
		//开始注册
		//检测工号是否被注册过
		int  count=AppMethods.SearchCountByjobNumber(jobNumber);
		switch(count){
		case 1:
			System.out.println("用户已经注册");
			msg="用户已经注册";
			//回跳登入界面 
			
		break;
		case 0:
			System.out.println("用户没有被注册 开始注册");
			AppMethods.insertUserInfo(realName, jobNumber, password, states, nickName, openid, unionid, isload,mobile);
			//回跳登入界面
		break;
		default:
			break;
		
		}
		request.setAttribute("msg",msg);
		request.setAttribute("openid",openid);
		request.setAttribute("unionid",unionid);
		//统一回跳 新的登入页面  然后然后两个参数
		request.getRequestDispatcher("TelLogin/TelLogin.jsp").forward(request, response);
		
	}
	catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	
	}

}
