package com.org.ixinhai.sql;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.entity.UserInformation;
import com.org.ixinhai.until.ConnectionFactory;






/**
* @author Tony
* @version 创建时间：2018年6月1日 下午3:13:30
* @ClassName 类名称
* @Description 类描述
*/
public class SqlMethods {
   // 验证账号密码  如果对就 返回true  数量异常 就返回false
	public static boolean SearchName(String employeeUserName,String employeePassword) throws SQLException {
		int count=0;
		boolean bl = true;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {

			String sql = "select count(*) from  ixinhai_employee_login el where el.employeeUserName='"+employeeUserName+"' and el.employeePassword='"+employeePassword+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			switch(count){
			case 1:
				bl=true;
				break;
			case 0:
				bl=false;
				break;
			 default :
				bl=false;
				break;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return bl;
	}
	
	
	
	
	public static int RepeatCheck(int UserId,String chooseSongName,String choosesSingerName) throws SQLException {
		int count=0;
	//	Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			
			String sql = "select count(*) from  ixinhai_chooses_song_log csl where csl.createId='"+UserId+"' and  csl.chooseSongName='"+chooseSongName+"'  and  csl.choosesSingerName='"+choosesSingerName+"' and to_days(csl.firstAddTime) = to_days(now()) ";
			System.out.println("Sql>"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return count;
	}
	
//登入验证	
	public static int SearchUserIdByUserNameAndPassWord(String employeeUserName,String employeePassword) throws SQLException {
		int userId=0;
		//Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select el.id from  ixinhai_employee_login el where el.employeeUserName='"+employeeUserName+"' and el.employeePassword='"+employeePassword+"'";
			System.out.println("Sql>"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userId;
	}
	
	//获取用户的信息
	public static List<UserInformation> SearchUserInformation(String employeeUserName,String employeePassword) throws SQLException {
		
		List<UserInformation> lessonList= new ArrayList<UserInformation>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT el.*,dict.name FROM ixinhai_employee_login el  left join ixinhai_dictionary dict on dict.value=el.status where el.employeeUserName='"+employeeUserName+"' and el.employeePassword='"+employeePassword+"' and dict.type='userType'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserInformation ui= new UserInformation();
				ui.setId(rs.getInt(1));
				ui.setEmployeeName(rs.getString(2));
				ui.setEmployeeUserName(rs.getString(3));
				ui.setEmployeePassWord(rs.getString(4));
				ui.setStatus(rs.getInt(6));
				ui.setUserAuthority(rs.getString(12));
				lessonList.add(ui);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}
	
	
	
	
	
	
	//获取用户的点歌实例
	
	public static List<UserApplication> SearchUserDateByUserID(int userId,int curPage,int row ) throws SQLException {
		List<UserApplication> lessonList= new ArrayList<UserApplication>();
		//Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		curPage=(curPage-1)*row;
		try {
			//String sql = "select * from ixinhai_chooses_song_log csl where createId="+userId +" order by csl.firstAddTime desc limit "+curPage+","+row;
//			String sql2="select csl.*,dict.name as statusValue from ixinhai_chooses_song_log csl  left join ixinhai_dictionary dict on dict.value=csl.status where createId="+userId +"  and dict.type='status' order by csl.firstAddTime desc limit "+curPage+","+row;
			String sql3="select csl.*,dict.name as statusValue,el.nickName from ixinhai_chooses_song_log csl  left join ixinhai_dictionary dict on dict.value=csl.status left join  ixinhai_employee_login el on el.id=csl.createId where createId="+userId +"  and dict.type='status' order by csl.firstAddTime desc limit "+curPage+","+row;
			System.out.println(sql3);
			rs = stmt.executeQuery(sql3);
			while (rs.next()) {
				UserApplication uaf =new UserApplication();
				uaf.setId(rs.getInt(1));
				uaf.setChooseUserName(rs.getString(2));
				uaf.setChooseDepartment(rs.getString(3));
				uaf.setChooseSongName(rs.getString(4));
				uaf.setChooseSingerName(rs.getString(5));
				uaf.setChooseWish(rs.getString(6));
				uaf.setFirstAddTime(rs.getTimestamp(8));
				uaf.setStatus(rs.getInt(12));
				uaf.setStatusValue(rs.getString(14));
				uaf.setUpdateTime(rs.getTimestamp(10));
				uaf.setNickName(rs.getString(15));
				lessonList.add(uaf);
			//	userId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}
	
	
	
	public static int SearchUserDateCountByUserID(int userId ) throws SQLException {
		int count=0;
		//Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from ixinhai_chooses_song_log csl where createId="+userId;
			System.out.println("Sql>"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count=rs.getInt(1);	
			//	userId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
//获取用户的状态
	
	public static int SearchUserStatusByUserId(int userId ) throws SQLException {
		int status=0;
		//Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select el.status from ixinhai_employee_login el where el.id="+userId;
			System.out.println("Sql>"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				status=rs.getInt(1);	
			//	userId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return status;
	}
	
//添加申请
	public static void insertUserChooseSongLog(String chooseUserName,String chooseUserTelNumber,String chooseDepartment,String chooseSongName,String chooseSingerName,String chooseWish,String userId)throws Exception{
		
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			String sql = "INSERT INTO `ixinhai_chooses_song_log` (chooseUserName,chooseUserTelNumber,chooseDepartment,chooseSongName,chooseSingerName,chooseWish,firstAddTime,createId,status)" +
					"VALUES ('" + chooseUserName + "','"+chooseUserTelNumber+"','" + chooseDepartment+ "','" + chooseSongName + "','" + chooseSingerName + "','" + chooseWish + "','" + now + "','" + userId + "',1)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//判断是否是歌曲和歌手是否重复
	
	
	public static int repeatSingerAndSong(String  chooseSingerName,String chooseSongName)throws SQLException{
		int count =0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from ixinhai_chooses_song_log csl where csl.chooseSongName='"+chooseSongName+"' and csl.chooseSingerName='"+chooseSingerName+"' and to_days(csl.firstAddTime) = to_days(now())";
			System.out.println("Sql>"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count=rs.getInt(1);	
			//	userId = rs.getInt(1);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return count;
		
		
	}
	
	
	//日志的插入 Start
public static void insertLog(String userId,String logContent)throws Exception{
		
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			String sql = "INSERT INTO `ixinhai_log` (content,createTime,createId,status)" +
					"VALUES ('" + logContent + "','" + now+ "','" + userId + "',1)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//日志的插入 End
	


//获取单个用户的点歌实例

	public static List<UserApplication> SearchChooseLogByLogid(String chooseLogId) throws SQLException {
		List<UserApplication> lessonList= new ArrayList<UserApplication>();
		//Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			//String sql = "select * from ixinhai_chooses_song_log csl where createId="+userId +" order by csl.firstAddTime desc limit "+curPage+","+row;
			String sql2="select csl.* from ixinhai_chooses_song_log csl where csl.id='"+chooseLogId+"'";
			System.out.println(sql2);
			rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				UserApplication uaf =new UserApplication();
				uaf.setId(rs.getInt(1));
				uaf.setChooseUserName(rs.getString(2));
				uaf.setChooseDepartment(rs.getString(3));
				uaf.setChooseSongName(rs.getString(4));
				uaf.setChooseSingerName(rs.getString(5));
				uaf.setChooseWish(rs.getString(6));
				uaf.setChooseUserTelNumber(rs.getString(7));
				uaf.setFirstAddTime(rs.getTimestamp(8));
				uaf.setStatus(rs.getInt(12));
				lessonList.add(uaf);
			//	userId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}
	//获取单个用户的点歌实例end
	
	//更新单个用户实例
	//更新编辑页的工作信息
	public static void UpdatelogbyChooseId(String chooseUserName,String chooseDepartment,String chooseSongName,String chooseSingerName,String chooseWish,String chooseUserTelNumber,String userId,String chooseLogId) throws SQLException{
		Connection conn= ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try{
		//	String sql="SELECT npmls.*,e.name FROM eight_factory.`new_precision_machining_log _statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where npmls.id="+id;
			String sql2="UPDATE ixinhai_chooses_song_log csl SET  chooseUserName = '"+chooseUserName+"', chooseDepartment='"+chooseDepartment+"',chooseSongName='"+chooseSongName+"' ,chooseSingerName='"+chooseSingerName+"',chooseWish='"+chooseWish+"',chooseUserTelNumber='"+chooseUserTelNumber+"',updateTime='"+now+"',updateUserId='"+userId+"' WHERE id = "+chooseLogId;
			System.out.println(sql2);
			stmt.executeUpdate(sql2);						
		}catch(Exception e){e.printStackTrace();}
		
		
	}
	
	//更新单个用户实例end
	
	//更新单个chooseLog的状态
		public static void UpdatelogStatusbyChooseId(String userId,String chooseLogId,String status) throws SQLException{
			Connection conn= ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			try{
			//	String sql="SELECT npmls.*,e.name FROM eight_factory.`new_precision_machining_log _statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where npmls.id="+id;
				String sql2="UPDATE ixinhai_chooses_song_log csl SET  updateTime='"+now+"',updateUserId='"+userId+"' ,status='"+status+"' WHERE id = "+chooseLogId;
				System.out.println(sql2);
				stmt.executeUpdate(sql2);						
			}catch(Exception e){e.printStackTrace();}
			
			
		}
		
		//更新单个用户实例end
		
		
		//获取单个chooseLog的状态
				public static String SearchlogStatusbyChooseId(String chooseLogId) throws SQLException{
					Connection conn= ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
					String status=null;
					try{
						String sql = "SELECT csl.status FROM ixinhai_chooses_song_log  csl where  csl.id="+chooseLogId;
						System.out.println(sql);
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							status = rs.getString(1);
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return status;
				}
		//获取单个chooseLog的状态结束
		
		//id对应的记录条数
				public static int SearchCountByUserId(String userId) throws SQLException{
					Connection conn= ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
					int count=0;
					try{
						String sql = "SELECT count(*) FROM ixinhai_chooses_song_log  csl where  csl.createId="+userId;
						System.out.println(sql);
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							count = rs.getInt(1);
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return count;
				}		
				//id对应的记录条数
				
	
	//查询工号是否被注册
				public static int SearchCountByjobNumber(String jobNumber) throws SQLException{
					Connection conn= ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
					int count=0;
					try{
						String sql = "SELECT count(*) FROM ixinhai_employee_login  el where  el.employeeUserName='"+jobNumber+"'";
						System.out.println(sql);
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							count = rs.getInt(1);
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return count;
				}	
				
				
		//查询openid和unionid是否被注册
				public static int SearchCountByOpenidandUnionid(String openid,String unionid) throws SQLException{
					Connection conn= ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
					int count=0;
					try{
						String sql = "SELECT count(*) FROM ixinhai_employee_login  el where  el.openid='"+openid+"' and el.unionid='"+unionid+"'";
						System.out.println(sql);
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							count = rs.getInt(1);
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return count;
				}	
				
				
				//查询openid和unionid是否被注册
				public static int SearchisLoadByOpenidandUnionid(String openid,String unionid) throws SQLException{
					Connection conn= ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
					int isload=0;
					try{
						String sql = "SELECT el.isload FROM ixinhai_employee_login  el where  el.openid='"+openid+"' and el.unionid='"+unionid+"'";
						System.out.println(sql);
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							isload = rs.getInt(1);
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return isload;
				}				
				
				
				//查询openid和unionid对应的账号.密码
				public static List<UserInformation> SearchAccountAndPasswordByOpenidandUnionid(String openid,String unionid) throws SQLException{
					List<UserInformation> lessonList= new ArrayList<UserInformation>();
					Connection conn= ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
				//	int isload=0;
					try{
						String sql = "SELECT el.employeeUserName,el.employeePassword FROM ixinhai_employee_login  el where  el.openid='"+openid+"' and el.unionid='"+unionid+"'";
						System.out.println(sql);
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							UserInformation uaf =new UserInformation();
							uaf.setEmployeeUserName(rs.getString(1));
							uaf.setEmployeePassWord(rs.getString(2));
							lessonList.add(uaf);
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return lessonList;
				}				
				
				
				
				
				
	//注册用户信息
	public static void insertUserInfo(String realName,String jobNumber,String password,int states,String nickName,String openid,String unionid,int isload,String mobile)throws Exception{
						Connection conn = ConnectionFactory.makeConnection();
						Statement stmt = conn.createStatement();
						Timestamp now = new Timestamp(System.currentTimeMillis());
						try {
							String sql = "INSERT INTO `ixinhai_employee_login` (employeeName,employeeUserName,employeePassword,firstAddTime,status,nickName,openid,unionid,isload,employeeTelNumber)" +
									"VALUES ('" + realName + "','" + jobNumber+ "','" + password + "','" + now + "','" + states + "','" + nickName + "','" + openid + "','" + unionid + "','" + isload + "','"+mobile+"')";
							System.out.println(sql);
							stmt.execute(sql);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
	//注册用户信息end		
	
	//获取用户信息 名字和手机号byuserId  start
	
	//获取用户的信息
	public static List<UserInformation> SearchUserNameAndTelByUserId(String userId) throws SQLException {
		
		List<UserInformation> lessonList= new ArrayList<UserInformation>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT el.id,el.employeeName,el.employeeTelNumber FROM ixinhai_employee_login el where el.id='"+userId+"'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserInformation ui= new UserInformation();
				ui.setId(rs.getInt(1));
				ui.setEmployeeName(rs.getString(2));
				ui.setEmployeeTelNumber(rs.getString(3));
				lessonList.add(ui);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}
	
				
	//获取用户信息byuserId   end		
	public static void main(String[] args) throws Exception {

		
	//Boolean bl=	SqlMethods.SearchName("81205","123456");
	//System.out.println("bl:"+bl);
//		List<UserApplication> lessonList= new ArrayList<UserApplication>();
//		lessonList=SqlMethods.SearchUserDateByUserID(1, 0, 15);
//		System.out.println(lessonList.get(0).getId());
//		System.out.println(lessonList.get(0).getChooseUserName());
//		System.out.println(lessonList.get(0).getChooseDepartment());
//		System.out.println(lessonList.get(0).getChooseSongName());
//		System.out.println(lessonList.get(0).getChooseSingerName());
//		System.out.println(lessonList.get(0).getChooseWish());
//		System.out.println(lessonList.get(0).getFirstAddTime());
//		System.out.println(lessonList.get(0).getStatus());
//		System.out.println(lessonList.get(0).getInformation());
		
	// SqlMethods.insertLog("1","日志更新测试");
		List<UserInformation> lessonList= new ArrayList<UserInformation>();
		lessonList=SqlMethods.SearchUserNameAndTelByUserId("1");
		System.out.println(lessonList.get(0).getId()+lessonList.get(0).getEmployeeName()+lessonList.get(0).getEmployeeTelNumber());
	}	
		
}
