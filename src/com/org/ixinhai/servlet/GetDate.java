package com.org.ixinhai.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.sql.SqlMethods;
import com.org.ixinhai.until.AppMethods;
import com.org.ixinhai.until.BackgroundMethods;
import com.org.ixinhai.until.SMS;
import com.org.ixinhai.until.SMSCode;

/**
 * Servlet implementation class GetDate
 */
@WebServlet("/GetDate")
public class GetDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDate() {
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
	
	try{
		String methods=request.getParameter("methods");
		switch(methods){
		case "WeChatNickname":
			String jobNumber=request.getParameter("jobNumber");
			System.out.println("jobNumber:"+jobNumber);
			String jsonString = "";
			jsonString=BackgroundMethods.insertlogic(jobNumber);
			JSONObject  json = JSONObject.parseObject(jsonString);
			System.out.println(json.toString());
			 response.setContentType("text/javascript;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 response.getWriter().print(json);		
			break;
		case "VerificationCode":
			String mobile=request.getParameter("mobile");
			String method=request.getParameter("method");
			String res=null;
			String msg=null;
			String content=null;
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			//
			if(null!=mobile){
			    Pattern p = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");
				if(p.matcher(mobile).matches()){
				String name="尊敬的"+mobile+"机主";
				String codeGen=AppMethods.codeGen();
				System.out.println(codeGen);
				switch(method){
				case "resetTelphone":
				 content ="正在跟换绑定手机号,当前的验证码是："+codeGen+",如果不是本人操作请立即修改密码";
				break;
				case "resetpwd":
					 content ="正在修改密码,当前的验证码是："+codeGen+",如果不是本人操作请立即修改密码";
					break;
				case "EatStatisticsVerificationCode":
					content ="正在申请吃饭统计系统的验证码,当前的验证码是："+codeGen+",如果不是本人操作请立即修改密码";
					break;
				case "VerificationCode":
					content ="正在绑定爱心海点歌系统手机号,当前的验证码是："+codeGen+",如果不是本人操作请立即修改密码";
					break;
				default:
				 break;
				}
				String templId = "32518";
				String operation= "onesms";
				System.out.println("机主"+mobile+"名字"+name+"内容"+content+"短信类型编号"+templId+"单/群"+operation);
				//发送验证码
				try {
					res = SMSCode.SetSMS(mobile, name, content, templId, operation);
					System.out.println(res);
					} catch (Exception e) {
					e.printStackTrace();
					}
				 msg="验证码发送成功";
				 map.put("msg", msg);
				 map.put("success", true);
				 map.put("codeGen", codeGen);
				 String jsonCode=JSON.toJSONString(map);  
				 response.setContentType("text/javascript;charset=utf-8");
				 response.setCharacterEncoding("utf-8");
				 response.getWriter().print(jsonCode);			
				}
				else{
					//手机号类型不对 非法访问
					msg="请填写正确的手机号";
					map.put("msg", msg);
					map.put("false", true);
					 String jsonCode=JSON.toJSONString(map);  
					response.setContentType("text/javascript;charset=utf-8");
					response.setCharacterEncoding("utf-8");
					response.getWriter().print(jsonCode);	
				}	
			}
			
			else{
				//employeetelNumber为空  无法发送不执行任何操作
			}
			//
			
			
			
			
			
//			JSONObject  jsonCode = JSONObject.parseObject(codeGen);
//			//System.out.println(jsonCode.toString());
//			 response.setContentType("text/javascript;charset=utf-8");
//			 response.setCharacterEncoding("utf-8");
//			 response.getWriter().print(jsonCode);		
			break;
			
			
			
			case"ChooserData":
			double row=15;
			int pageCount=0;
			String userId=request.getParameter("userId");
			List<UserApplication> lessonList= new ArrayList<UserApplication>();
			lessonList=SqlMethods.SearchUserDateByUserID(Integer.parseInt(userId),1,15);
			request.setAttribute("UserDataLessonList", lessonList);
			
			//分页的数据
			int UserAllcount=AppMethods.SearchCountByUserId(userId);
			pageCount=(int)Math.ceil(UserAllcount/row);
			request.setAttribute("curPage", 1);
			request.setAttribute("pageCount", pageCount);
			request.getRequestDispatcher("UserMainRight.jsp").forward(request, response);
			
			break;
			default:
				break;
		
		}
		
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	}

}
