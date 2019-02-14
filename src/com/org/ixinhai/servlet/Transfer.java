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
import com.alibaba.fastjson.JSONObject;
import com.org.ixinhai.entity.UserInformation;
import com.org.ixinhai.sql.SqlMethods;
import com.org.ixinhai.until.AppMethods;
import com.org.ixinhai.until.BackgroundMethods;

/**
 * Servlet implementation class Transfer
 */
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
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
	//	doGet(request, response);
		
		//优先与一切的握手 这是是项目的出发源
	try{
		//根据链接协议必定会有两个参数过来
		String openid=null;
		String unionid=null;
		String msg=null;
		String ip=null;
		int count=0;
		int isload=0;
		if(null==request.getParameter("openid")&&null==request.getParameter("unionid")){
			//两个参数必须有 没有就是非法访问
			msg="非法访问";
			ip=BackgroundMethods.getRemortIP(request);
			//非法访问后返回登入页面同时没有openid 和 unionid
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("msg", msg);
			map.put("ip", ip);
			String json=JSON.toJSONString(map); 
			//JSONObject  json = JSONObject.parseObject(map);
			System.out.println(json);
			AppMethods.AddLog(ip, msg);
		 request.getRequestDispatcher("TelLogin/TelLogin.jsp").forward(request, response);
		}
		else{
			openid=request.getParameter("openid");
			unionid=request.getParameter("unionid");
			//获取两个openid和unionid 进行数据库的取值
			count=AppMethods.SearchCountByOpenidandUnionid(openid, unionid);
			
			System.out.println("count:"+count);
			switch(count){
			case 1:
				//账号存在 查询是否直接登入
				isload=AppMethods.SearchisLoadByOpenidandUnionid(openid, unionid);
				// 1 不自动 2自动(默认注册2)
				switch(isload){
				case 1:
					//1  不自动跳转  同时将两个openid unionid 返回给登入页（其实也可以不给  因为注册的时候检查这个人在不在是看这个人的工号的）
					// request.setAttribute("openid",openid);
					// request.setAttribute("unionid", unionid);
					// request.getRequestDispatcher("TelLogin/TelLogin.jsp").forward(request, response);
					break;
				case 2:
					//2 自动拉取这个人的账号密码 回去
					//查询账号密码
					 List<UserInformation> lessonList= new ArrayList<UserInformation>();
					 lessonList=SqlMethods.SearchAccountAndPasswordByOpenidandUnionid(openid,unionid);
					 request.setAttribute("jobNumber",lessonList.get(0).getEmployeeUserName());
					 request.setAttribute("password", lessonList.get(0).getEmployeePassWord()); 
					break;
				default:
					//一般不可能出现 出现的时候就是自动登入的状态值异常，不进行自动登入，同时返回openid 和unionid给登入页面 
					
					msg="openid unionid 对应的isload状态值   异常";
					ip=BackgroundMethods.getRemortIP(request);
					AppMethods.AddLog(ip, msg);
					break;
				}
				break;
			case 0:
				//账号不存咋 不进行查询 直接跳转登入页面同时携带openid 和unionid跳转给TelLogin.jsp
				//无操作
			break;
			default:
				//openid 异常unionid异常   跳转给TelLogin.jsp同时携带两个异常的参数  登入的时候只使用 账号密码对openid 和unionid无影响 只会在注册的时候提示账号异常
				msg="openid unionid 异常";
				ip=BackgroundMethods.getRemortIP(request);
				AppMethods.AddLog(ip, msg);
				break;
			}
			 request.setAttribute("openid",openid);
			 request.setAttribute("unionid", unionid);
			 request.getRequestDispatcher("TelLogin/TelLogin.jsp").forward(request, response);
		}
	}catch (Exception e) {
		e.printStackTrace();	// TODO: handle exception
		}
	}

}
