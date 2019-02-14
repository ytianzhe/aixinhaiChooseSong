package com.org.ixinhai.until;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.sql.BackgroundSql;
import com.org.ixinhai.sql.SqlMethods;


/**
* @author Tony
* @version 创建时间：2018年8月4日 下午1:19:18
* @ClassName 类名称
* @Description 类描述
*/
public class BackgroundMethods {
	
	public static LinkedHashMap<String, Object> getDealData() throws Exception{
		 LinkedHashMap<String, Object> linkedHashMap =  new LinkedHashMap<String, Object>();	
		 String msg=null;
		 String code="0";
		 int UntreatedCount=0;
		 UntreatedCount=BackgroundSql.SearchDealDateCount(1);
		 int treatedCount=0;
		 treatedCount=BackgroundSql.SearchDealDateCount(2);
		 int undoCount=0;
		 undoCount=BackgroundSql.SearchDealDateCount(3);
		 int AllMessageCount=0;
		 AllMessageCount=BackgroundSql.SearchDealDateALllCount();
		 linkedHashMap.put("code",code);
		 linkedHashMap.put("UntreatedCount",UntreatedCount+"");
		 linkedHashMap.put("treatedCount",treatedCount+"");
		 linkedHashMap.put("undoCount",undoCount+"");
		 linkedHashMap.put("AllMessageCount",AllMessageCount+"");
		 
		 List<UserApplication> MainDatalessonList= new ArrayList<UserApplication>();
		 MainDatalessonList=BackgroundSql.SearchMainData(1,15);
		 linkedHashMap.put("MainDatalessonList",MainDatalessonList);
		 
		 
		 return  linkedHashMap;
	 }
	
	
	public static LinkedHashMap<String, Object> getMainData(int curPage,int row) throws Exception{
	LinkedHashMap<String, Object> linkedHashMap =  new LinkedHashMap<String, Object>();	
		List<UserApplication> MainDatalessonList= new ArrayList<UserApplication>();
		MainDatalessonList=BackgroundSql.SearchMainData(curPage, row);
		linkedHashMap.put("MainDatalessonList", MainDatalessonList);
		return  linkedHashMap;
	}
	
	
//更新状态
	//UpdatelogStatusbyChooseId
	public static void UpdatelogStatusbyChooseId(String chooseLogId,String additional) throws Exception{
		BackgroundSql.UpdatelogStatusbyChooseId(chooseLogId, additional, "2");
		//	return  linkedHashMap;
		}
	
//获取所有的条数
	public static int SearchDealDateALllCount() throws Exception{
		int count=0;
		count=BackgroundSql.SearchDealDateALllCount();
	return  count;
		}
	
//获取带条件的条数
	public static int SearchDealDateCountByConditions(String conditions) throws Exception{
		int count=0;
		count=BackgroundSql.SearchCountByConditions(conditions);
	return  count;
		}
	
	public static LinkedHashMap<String, Object> getDealDataByCurPage(int curPage,int row) throws Exception{
		 LinkedHashMap<String, Object> linkedHashMap =  new LinkedHashMap<String, Object>();	
		 String msg=null;
		 String code="0";
		 int UntreatedCount=0;
		 UntreatedCount=BackgroundSql.SearchDealDateCount(1);
		 int treatedCount=0;
		 treatedCount=BackgroundSql.SearchDealDateCount(2);
		 int undoCount=0;
		 undoCount=BackgroundSql.SearchDealDateCount(3);
		 int AllMessageCount=0;
		 AllMessageCount=BackgroundSql.SearchDealDateALllCount();
		 linkedHashMap.put("code",code);
		 linkedHashMap.put("UntreatedCount",UntreatedCount+"");
		 linkedHashMap.put("treatedCount",treatedCount+"");
		 linkedHashMap.put("undoCount",undoCount+"");
		 linkedHashMap.put("AllMessageCount",AllMessageCount+"");
		 List<UserApplication> MainDatalessonList= new ArrayList<UserApplication>();
		 MainDatalessonList=BackgroundSql.SearchMainData(curPage,row);
		 linkedHashMap.put("MainDatalessonList",MainDatalessonList);
		 
		 
		 return  linkedHashMap;
	 }
	
	//获取两个变量进行生成条件的sql
	

	public static String JoiningTogether(String date,String chooseUserId,String nickNameId) throws Exception{
		String conditions="";
		String getdate="";
		String getchooseUserId="";
		String getnickNameId="";
		if(null!=date){
			if(date.length()!=0||date.equals("")==false){
			
			getdate=date;
			//conditions=conditions+getdate;
			
			
			conditions=conditions+" and DATE_FORMAT(csl.firstAddTime,'%Y-%m-%d')='"+getdate+"'";
		}
			
		}
		if(null!=chooseUserId)
		{
			if(chooseUserId.equals("")==false||chooseUserId.length()!=0){
				getchooseUserId=chooseUserId;
				
				//conditions=conditions+getchooseUserId;
				conditions=conditions+" and csl.createId="+getchooseUserId;
			}
		}
		
		if(null!=nickNameId)
		{
			if(nickNameId.equals("")==false||nickNameId.length()!=0){
				getnickNameId=nickNameId;
				
				//conditions=conditions+getchooseUserId;
				conditions=conditions+" and csl.createId="+getnickNameId;
			}
		}
		return conditions;
		}
	
	
	
	public static LinkedHashMap<String, Object> getDealDataByCurPageAndConditions(int curPage,int row,String conditions) throws Exception{
		 LinkedHashMap<String, Object> linkedHashMap =  new LinkedHashMap<String, Object>();	
		 String msg=null;
		 String code="0";
		 int UntreatedCount=0;
		 UntreatedCount=BackgroundSql.SearchDealDateCount(1);
		 int treatedCount=0;
		 treatedCount=BackgroundSql.SearchDealDateCount(2);
		 int undoCount=0;
		 undoCount=BackgroundSql.SearchDealDateCount(3);
		 int AllMessageCount=0;
		 AllMessageCount=BackgroundSql.SearchDealDateALllCount();
		 
		 linkedHashMap.put("code",code);
		 linkedHashMap.put("UntreatedCount",UntreatedCount+"");
		 linkedHashMap.put("treatedCount",treatedCount+"");
		 linkedHashMap.put("undoCount",undoCount+"");
		 linkedHashMap.put("AllMessageCount",AllMessageCount+"");
		 
		 List<UserApplication> MainDatalessonList= new ArrayList<UserApplication>();
		 MainDatalessonList=BackgroundSql.SearchMainDataByConditions(curPage,row,conditions);
		 linkedHashMap.put("MainDatalessonList",MainDatalessonList);
		 return  linkedHashMap;
	 }
//获取晒选矿的两个列表 
	public static LinkedHashMap<String,List<UserApplication>> getUserNameandNickNameList() throws Exception{
		 LinkedHashMap<String,  List<UserApplication>> linkedHashMap =  new LinkedHashMap<String, List<UserApplication>>();	
		 List<UserApplication> MainDatalessonUserNameList= new ArrayList<UserApplication>();
		 List<UserApplication> MainDatalessonNickNameList= new ArrayList<UserApplication>();
		 MainDatalessonUserNameList=BackgroundSql.SearchChooserList();
		 MainDatalessonNickNameList=BackgroundSql.SearchNickNameList();
		 linkedHashMap.put("MainDatalessonUserNameList",MainDatalessonUserNameList);
		 linkedHashMap.put("MainDatalessonNickNameList",MainDatalessonNickNameList);
		 return  linkedHashMap;
	 }
	
	
	//获取携带名字手机号的加密字符串 map
		public static Map<String, Object> GeNameAndPhoneNumberByJobNumber(String jobName) throws Exception{
			String jsonString = "";
			jsonString=SMS.SetSMS(jobName);
			//json转String
			JSONObject  jsonObject = JSONObject.parseObject(jsonString);
			jsonString=jsonObject.toJSONString();
			//String 转map
			Map<String,Object> map = (Map<String,Object>)jsonObject;
			
//		    JSONObject  object = (JSONObject) jsonObject.get("user");
//		    String cPsn_name=object.get("cPsn_name").toString();
//		    String cPsn_mobile=object.get("cPsn_mobile").toString();
//		    String status= jsonObject.get("result").toString();
//		    String key = "a07fd996491b9c16";
//		    String ChooserName=AESCrypt.decrypt(cPsn_name, key);
//		    String ChooserTelNumber=AESCrypt.decrypt(cPsn_mobile, key);
		 // System.out.println("name值是："+ChooserName);
		  //System.out.println("手机号："+ChooserTelNumber);
		//  System.out.println("userName值是："+cPsn_name);
	   //   System.out.println("userTel值是："+cPsn_mobile);
		    return map;
		}
		


		//获取携带名字手机号的加密字符串 map 做出判断
			public static LinkedHashMap<String, Object> JudgeRegistered(Map<String, Object> map) throws Exception{
				LinkedHashMap<String, Object> linkedHashMap =  new LinkedHashMap<String, Object>();
				String key = "a07fd996491b9c16";
			//	String result= map.get("result").toString();
				String status= map.get("status").toString();
				if(status.equals("no")==true){
					linkedHashMap.put("msg","用户不存在");
					linkedHashMap.put("false","true");
					linkedHashMap.put("data","");
				}
				else if(status.equals("yes")==true){
					linkedHashMap.put("msg","用户已存在");
					linkedHashMap.put("success","true");
					//该工号存在 开始获取用户名字和手机号
					String user=map.get("user").toString();
					JSONObject  object = JSONObject.parseObject(user);
				//	String cPsn_name=object.get("cPsn_name").toString();
				//	String cPsn_mobile=object.get("cPsn_mobile").toString(); 
					
					
					String trueName=AESCrypt.decrypt(object.get("cPsn_name").toString(), key);
					String trueTel=AESCrypt.decrypt(object.get("cPsn_mobile").toString(), key);
			    	//System.out.println(trueName);
					//System.out.println(trueName+"-----"+trueTel);
					HashMap<String, Object> datamap=new HashMap<String, Object>();
					datamap.put("trueName",trueName);
					datamap.put("trueTel",trueTel);
					linkedHashMap.put("data",datamap);
				}
				else{
					linkedHashMap.put("msg", "数据异常");
					linkedHashMap.put("false","true");
					linkedHashMap.put("data","");
				}
			//	String user=map.get("user").toString();
			//	System.out.println(result+"---"+status+"----"+user);
				return linkedHashMap;
			}
		
	//名字与工号匹配判断的最终流程
			public static String insertlogic(String jobName) throws Exception{
				Map<String,Object> map =new HashMap<String,Object>();
				map=GeNameAndPhoneNumberByJobNumber(jobName);
				LinkedHashMap<String, Object> linkedHashMap =  new LinkedHashMap<String, Object>();
				linkedHashMap=JudgeRegistered(map);
				JSONObject json = new JSONObject(linkedHashMap);
				String jsonString = json.toString();
			    return jsonString;
			}	
	//获取nickName
				public static String GetNickNameByJobNumber(String jobNumber,String openid,String unionid) throws Exception{
					String jsonString = "";
					jsonString=SMSNick.SetSMSNick(jobNumber, openid, unionid);
					//json转String
					JSONObject  jsonObject = JSONObject.parseObject(jsonString);
					//jsonString=jsonObject.toJSONString();
					//String data= jsonObject.getString("data").toString();
					//System.out.println(data);
					  JSONArray jsarr=jsonObject.getJSONArray("data");//jsonobject对象取得some对应的jsonarray数组
					  JSONObject ao=jsarr.getJSONObject(0);
				//	 JSONObject  obj = JSONObject.parseObject(data);
				//	JSONObject  obj =  JSONObject.parseObject(data);
					String nickName=ao.get("nickname").toString();
				    return nickName;
				}
	//获取用户ip
				public  static String getRemortIP(HttpServletRequest request) {  
				    if (request.getHeader("x-forwarded-for") == null) {  
				        return request.getRemoteAddr();  
				    }  
				    return request.getHeader("x-forwarded-for");  
				}  		
	
		//发送状态更新的短信		
				public  static LinkedHashMap<String, Object> SendMsg(String chooseLogId,String method) {  
					LinkedHashMap<String, Object> map =  new LinkedHashMap<String, Object>();	
						try{
							List<UserApplication> lessonList= new ArrayList<UserApplication>();
							lessonList=BackgroundSql.SearchTelBychooseLogId(chooseLogId);
							String choosertel=null;
							choosertel=lessonList.get(0).getChooseUserTelNumber();
							String information=null;
							String content=null;
							information=lessonList.get(0).getInformation();
							System.out.println("content:------"+content);
						  //String employeeNumber=request.getParameter("employeeNumber");
							String resultName=null;
							resultName=lessonList.get(0).getChooseUserName();
							String res=null;
							String msg=null;
							//	String content=null;
							//获取用户提交的日志的那个电话号码
							if(choosertel!=null){
							    Pattern p = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");
								if(p.matcher(choosertel).matches()){
								String name="尊敬的"+resultName+"机主";
								String codeGen=AppMethods.codeGen();
								System.out.println(codeGen);
								switch(method){
								case "ProcessingRemind":
									content ="您的点歌请求已经在 被处理  以下是管理员的信息："+information;
									break;
								
								
								default:
								 break;
								}
								String templId = "32518";
								String operation= "onesms";
							//	System.out.println("机主"+choosertel+"名字"+name+"内容"+content+"短信类型编号"+templId+"单/群"+operation);
								//发送验证码
								try {
									
									res = SMSMessage.SetSMS(choosertel, name, content, templId, operation);
									System.out.println(res);
									} catch (Exception e) {
									e.printStackTrace();
									}
								 msg="验证码发送成功";
								 map.put("msg", msg);
								 map.put("success", true);
								 map.put("codeGen", codeGen);
//								 String json=JSON.toJSONString(map);  
//								 response.setContentType("text/javascript;charset=utf-8");
//								 response.setCharacterEncoding("utf-8");
//								 response.getWriter().print(json);			
								}
								
								
								else{
									//手机号类型不对 非法访问
									msg="请填写正确的手机号";
									map.put("msg", msg);
									map.put("false", true);
//									String json=JSON.toJSONString(map);  
//									response.setContentType("text/javascript;charset=utf-8");
//									response.setCharacterEncoding("utf-8");
//									response.getWriter().print(json);	
								}	
							}
							
							else{
								//employeetelNumber为空  无法发送不执行任何操作
							}
							//return map;
						}catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
						return map;
						
					}
					
					
					
						
	
				
				
				
				
				
				
	
	public static void main(String[] args) throws Exception {
//		 LinkedHashMap<String, Object> linkedHashMap =  new LinkedHashMap<String, Object>();
//		 linkedHashMap=BackgroundMethods.getDealData();
//		 System.out.println(linkedHashMap.get("code"));
//		 System.out.println(linkedHashMap.get("UntreatedCount"));
//		 System.out.println(linkedHashMap.get("treatedCount"));
//		 System.out.println(linkedHashMap.get("undoCount"));
		
//		String conditions="";
//	//	BackgroundMethods.JoiningTogether("","");
////		conditions=BackgroundMethods.JoiningTogether("","","");
//		conditions=BackgroundMethods.JoiningTogether(null,null,null);
//	     System.err.println("conditions:"+conditions);
		
		
	//	Map<String,Object> map =  new LinkedHashMap<String, Object>();	
	//	map=GeNameAndPhoneNumberByJobNumber("820056");
	//	System.out.println(map.toString());
	//	JudgeRegistered(map);
		
		
	//	LinkedHashMap<String, Object> linkedHashMap =  new LinkedHashMap<String, Object>();
//	    String insertString=insertlogic("820058");
//	    System.out.println(insertString);
	//	System.out.println(linkedHashMap.toString().trim());
	//	JSONObject json = new JSONObject(linkedHashMap);
	 //   System.out.println("Json对象是：" + json);
	//	System.out.println(linkedHashMap.get("data").toString());
	//	String userInfoString=linkedHashMap.get("data").toString();
	//	System.out.println(userInfoString);
	//	Map<String,Object> UserInfoMap =new HashMap<String,Object>();
		String jsonString = "";
		String openid="oVM1v1NnBgUGSne1DVJUCzkoefCE";
		String unionid="orYqF1Tr0aTN5dTxlnxA6d213XZE";
		jsonString=BackgroundMethods.GetNickNameByJobNumber("820056",openid,unionid);
		System.out.println(jsonString);
		
	}

}
