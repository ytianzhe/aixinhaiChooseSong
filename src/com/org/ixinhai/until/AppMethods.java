package com.org.ixinhai.until;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.entity.UserInformation;
import com.org.ixinhai.sql.SqlMethods;

/**
* @author Tony
* @version 创建时间：2018年7月27日 下午1:23:58
* @ClassName 类名称
* @Description 类描述
*/
public class AppMethods {

	 /**
	 * @param ChooseUserName
	 * @param ChooseDepartment
	 * @param ChooseSongName
	 * @param ChooseSingerName
	 * @param userId
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public static LinkedHashMap<String, String> AddSongLog(String userId,String chooseUserName,String chooseUserTelNumber,String chooseDepartment,String chooseSongName,String chooseSingerName,String chooseWish) throws Exception{
		  LinkedHashMap<String, String> linkedHashMap =  new LinkedHashMap<String, String>();	
		  String msg=null;
		 int status=SqlMethods.SearchUserStatusByUserId(Integer.parseInt(userId));
		 	//检测添加的权限 userid  判断status  是不是为1 
			//通过检测就直接开始添加 
			// 判断歌手和歌的名字有 在今天有没有点过
			//如果有就返回msg 已经今天已经点过该歌手的歌曲  请不要重复点歌  
			//没有就添加库 返回msg  点歌成功  
			//插入操作
			if(status!=1){
				msg="用户权限不足 无法申请";
				linkedHashMap.put("code","1");
				linkedHashMap.put("msg", msg);
				
			}
			else{
				msg="用户权限通过可以申请";
				
				//判断歌曲是否重复
				
				int count=SqlMethods.repeatSingerAndSong(chooseSingerName, chooseSongName);
				System.out.println("歌曲和歌手是否在今日重复："+count);
				if(count==0){
					System.out.println("没有记录可以插入");
					
					//这里执行插入
					linkedHashMap.put("code","0");
					AppMethods.insertSongLog(userId,chooseUserName,chooseUserTelNumber,chooseDepartment,chooseSongName, chooseSingerName, chooseWish);
					//日志 
					AppMethods.AddLog(userId,"点歌申请");
					msg="添加成功";
					linkedHashMap.put("msg", msg);
					
				}
				else{
					//System.out.println("异常停止插入");
					msg="异常停止插入";
					linkedHashMap.put("code","1");
					linkedHashMap.put("msg", msg);
				}
			}
		
		 return  linkedHashMap;
	 }
	//管理日志的记录
		public static void AddLog(String userId,String logContent	) throws Exception{
			SqlMethods.insertLog(userId,logContent);
	}	
		
	//插入歌曲
		public static void insertSongLog(String userId,String chooseUserName,String chooseUserTelNumber,String chooseDepartment,String chooseSongName,String chooseSingerName,String chooseWish) throws Exception{
			SqlMethods.insertUserChooseSongLog(chooseUserName,chooseUserTelNumber, chooseDepartment, chooseSongName, chooseSingerName, chooseWish, userId);
	}	
		
	//获取单个点歌信息的实例
		public static List<UserApplication>SearchChooseLogByLogid(String chooseLogId) throws Exception{
			List<UserApplication> lessonList= new ArrayList<UserApplication>();
			lessonList=SqlMethods.SearchChooseLogByLogid(chooseLogId);
			return lessonList;
		}	
		
		//更新单个点歌实例信息
		public static void UpdateChooseLogbyid(String chooseUserName,String chooseDepartment,String chooseSongName,String chooseSingerName,String chooseWish,String chooseUserTelNumber,String userId,String chooseLogId) throws Exception{
			SqlMethods.UpdatelogbyChooseId(chooseUserName, chooseDepartment, chooseSongName, chooseSingerName, chooseWish, chooseUserTelNumber, userId, chooseLogId);
		}
		//更新单个点歌实例信息状态（撤销）
		public static void UpdatelogStatusbyChooseId(String userId,String chooseLogId) throws Exception{
					SqlMethods.UpdatelogStatusbyChooseId(userId, chooseLogId,"3");
				}	
		//获取用户的信息条数byuserId
				public static int SearchCountByUserId(String userId) throws Exception{
							int count=SqlMethods.SearchCountByUserId(userId);
							return count;
						}	
		
		
				
				
		public static int PagingClass(String methods,int curPage,int pageCount) throws Exception{
					int newcurPage=0;
					  switch(methods){
						case "HomePage":
							newcurPage=1;
						break;
						case "PreviousPage":
							if(curPage>1&&curPage<=pageCount)
							{
								newcurPage=curPage-1;
							}
							else
							{
								newcurPage=curPage;
							}
							break;
						case "NextPage":
							if(curPage>=1&&curPage<pageCount)
							{
								newcurPage=curPage+1;
							}
							else
							{
								newcurPage=curPage;
							}
							break;
						case "EndPage":
							if(curPage>=1&&curPage<pageCount)
							{
								newcurPage=pageCount;
							}
							else
							{
								newcurPage=curPage;
							}
							break;
						case "null":
							newcurPage=curPage;
							break;
						}
					
					 return  newcurPage;
				 }	
		
		//测试获取工号 返回的名字 和手机号字符串 
	   public static void AAA() throws Exception{
			String jsonString = "";
			jsonString=SMS.SetSMS("820056");
			System.err.println(jsonString);
		}
	
		  //生成四位不重复的验证码
	   public static String codeGen(){
	        char [] codeSequence={'a','b','c','d','e','f','g','h','i','j',
	    'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
	    '1','2','3','4','5','6','7','8','9'};
	        
	        
	        char [] codeSequence2={'1','2','3','4','5','6','7','8','9'};
	        
	        Random random =new Random();
	        StringBuilder sb=new StringBuilder();//动态字符串，String创建的字符串不能修改
	        int count=0;//计数器确定产生的是四位验证码
	        while(true){
	            //随机产生一个下标，通过下标取出字符数组对应的字符
	            char c=codeSequence2[random.nextInt(codeSequence2.length)];
	            //假设取出来的字符在动态字符串中不存在，代表没有重复
	            if (sb.indexOf(c+"")==-1) {
	                sb.append(c);//追加到动态字符串中
	                count++;
	                if (count==4) {
	                    break;
	                }
	            }
	        }

	    return sb.toString();

	        }
	//检测用户是否注册
	   public static int SearchCountByjobNumber(String jobNumber) throws Exception{
		   int count=0;
		   SqlMethods.SearchCountByjobNumber(jobNumber);
			return count;
		}
	///注册用户信息 
	
	   public static void insertUserInfo(String realName,String jobNumber,String password,int states,String nickName,String openid,String unionid,int isload,String mobile) throws Exception{
		   SqlMethods.insertUserInfo(realName,jobNumber,password,states,nickName,openid,unionid,isload,mobile);
		}
	//检测openid 和unionid是否已经被注册
	   public static int SearchCountByOpenidandUnionid(String openid ,String unionid) throws Exception{
		   int count=0;
		   count=SqlMethods.SearchCountByOpenidandUnionid(openid,unionid);
			return count;
		}
	 //检测openid 和unionid携带的账号是否是直接登入
	   public static int SearchisLoadByOpenidandUnionid(String openid ,String unionid) throws Exception{
		   int isload=0;
		   isload=SqlMethods.SearchisLoadByOpenidandUnionid(openid,unionid);
			return isload;
		}
	//检测用户的账号和密码
	   public static List<UserInformation> SearchAccountAndPasswordByOpenidandUnionid(String openid ,String unionid) throws Exception{
		   List<UserInformation> lessonList= new ArrayList<UserInformation>();
		   lessonList=SqlMethods.SearchAccountAndPasswordByOpenidandUnionid(openid,unionid);
			return lessonList;
		}
	  //检测酷狗中是否这首歌
	   public static String SearchSongNameCount(String SongName) throws Exception{
		   //int count=0;
		   String result=null;
		   String url="http://songsearch.kugou.com/song_search_v2?keyword="+SongName+"&clientver=0&platform=WebFilter";
		   result=SMSKUGOU.get(url);
		   return result;
		}
	  //处理 酷狗返回字段
	   public static int DealKuGoString(String str) throws Exception{
		   int count=0;
		   JSONObject json;
		   System.out.println("str:"+str);
			json = JSONObject.parseObject(str);
			String data=json.get("data").toString();
			//System.out.println("data:"+data);
			 JSONObject datajson;
			 datajson = JSONObject.parseObject(data);
			// System.out.println("datajson:"+datajson.toString());
			 String lists=datajson.get("lists").toString();
			// System.out.println("lists:"+lists.toString());
			 int listlength=0;
			 listlength=lists.length();
			// System.out.println("listlength:"+listlength);
			 if(listlength>2){
				 count=1;
			 }
			 else if(listlength==2){
				 
				 count=0;
			 }
			 else{
				 
				 count=0; 
			 }
		   return count;
		}
	   
	   //检测歌曲是否存在     最终方法
	   public static int SearchSongNameInKuGu(String SongName) throws Exception{
		   int count=0;
		   String result=SearchSongNameCount(SongName);
		   count=DealKuGoString(result);
		   return count;
		}
	   
	public static void main(String[] args) throws Exception {
		//test();
//	String name=	GeNameAndPhoneNumberByJobNumber("820056");
//	System.err.println(name);
		String result=SearchSongNameCount("分手快乐");
	//	System.out.println(result);
		int count=DealKuGoString(result);
		System.out.println(count);
		//int count=SearchSongNameInKuGu("赤壁111dsadasdsadasd");
		//System.out.println(count);
		
	}
		
}