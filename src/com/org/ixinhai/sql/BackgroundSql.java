package com.org.ixinhai.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.org.ixinhai.entity.UserApplication;
import com.org.ixinhai.until.ConnectionFactory;

/**
* @author Tony
* @version 创建时间：2018年8月4日 下午1:21:42
* @ClassName 类名称
* @Description 类描述
*/
public class BackgroundSql {
	
	
	public static int SearchDealDateCount(int status) throws SQLException {
		int count=0;
	//	Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			
			String sql = "SELECT count(*) FROM ixinhai_chooses_song_log csl where csl.status="+status;
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
	
	
	public static List<UserApplication> SearchMainData(int curPage,int row) throws SQLException {
		List<UserApplication> lessonList= new ArrayList<UserApplication>();
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		curPage=(curPage-1)*row;
		try {
			
		//	String sql = "select csl.*,dict.name as statusValue from ixinhai_chooses_song_log csl  left join ixinhai_dictionary dict on dict.value=csl.status where  dict.type='status' order by csl.firstAddTime desc limit "+curPage+","+row;
			String sql2 = "select csl.*,dict.name as statusValue,el.nickName  from ixinhai_chooses_song_log csl  left join ixinhai_dictionary dict on dict.value=csl.status  left join  ixinhai_employee_login el on el.id=csl.createId  where  dict.type='status' order by csl.firstAddTime desc limit "+curPage+","+row;
			System.out.println("Sql>"+sql2);
			rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				UserApplication ua= new UserApplication();
				ua.setId(rs.getInt(1));
				ua.setChooseUserName(rs.getString(2));
				ua.setChooseDepartment(rs.getString(3));
				ua.setChooseSongName(rs.getString(4));
				ua.setChooseSingerName(rs.getString(5));
				ua.setChooseWish(rs.getString(6));
				ua.setChooseUserTelNumber(rs.getString(7));
				ua.setFirstAddTime(rs.getTimestamp(8));
				ua.setStatus(rs.getInt(12));
				ua.setStatusValue(rs.getString(14));
				ua.setUpdateTime(rs.getTimestamp(10));
				ua.setInformation(rs.getString(13));
				ua.setNickName(rs.getString(15));
				lessonList.add(ua);
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
	
	//更新 log单个的状态

			public static void UpdatelogStatusbyChooseId(String chooseLogId,String additional,String status) throws SQLException{
				Connection conn= ConnectionFactory.makeConnection();
				Statement stmt = conn.createStatement();
				Timestamp now = new Timestamp(System.currentTimeMillis());
				try{
				//	String sql="SELECT npmls.*,e.name FROM eight_factory.`new_precision_machining_log _statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where npmls.id="+id;
					String sql2="UPDATE ixinhai_chooses_song_log csl SET  updateTime='"+now+"' ,status='"+status+"',information='"+additional+"' WHERE id = "+chooseLogId;
					System.out.println(sql2);
					stmt.executeUpdate(sql2);						
				}catch(Exception e){e.printStackTrace();}
				
				
			}
	
	//获取所有的信息条数
			public static int SearchDealDateALllCount() throws SQLException {
				int count=0;
			//	Timestamp now = new Timestamp(System.currentTimeMillis());
				Connection conn = ConnectionFactory.makeConnection();
				// System.err.println(conn);
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				try {
					
					String sql = "SELECT count(*) FROM ixinhai_chooses_song_log csl" ;
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
//带条件查询条数
			public static int SearchCountByConditions(String conditions) throws SQLException {
				Connection conn = ConnectionFactory.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				int count =0;
				try {
					
					String sql = "select count(*) from ixinhai_chooses_song_log csl  left join ixinhai_dictionary dict on dict.value=csl.status where  dict.type='status' "+conditions ;
					System.out.println("Sql>"+sql);
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						count=rs.getInt(1);
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
			
			
//有条件的查询
			public static List<UserApplication> SearchMainDataByConditions(int curPage,int row,String conditions) throws SQLException {
				List<UserApplication> lessonList= new ArrayList<UserApplication>();
				Connection conn = ConnectionFactory.makeConnection();
				// System.err.println(conn);
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				curPage=(curPage-1)*row;
				try {
					
					String sql = "select csl.*,dict.name as statusValue,el.nickName from ixinhai_chooses_song_log csl  left join ixinhai_dictionary dict on dict.value=csl.status left join ixinhai_employee_login el on el.id=csl.createId where  dict.type='status' "+conditions+" order by csl.firstAddTime desc limit "+curPage+","+row;
					System.out.println("Sql>"+sql);
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						UserApplication ua= new UserApplication();
						ua.setId(rs.getInt(1));
						ua.setChooseUserName(rs.getString(2));
						ua.setChooseDepartment(rs.getString(3));
						ua.setChooseSongName(rs.getString(4));
						ua.setChooseSingerName(rs.getString(5));
						ua.setChooseWish(rs.getString(6));
						ua.setChooseUserTelNumber(rs.getString(7));
						ua.setFirstAddTime(rs.getTimestamp(8));
						ua.setStatus(rs.getInt(12));
						ua.setStatusValue(rs.getString(14));
						ua.setUpdateTime(rs.getTimestamp(10));
						ua.setInformation(rs.getString(13));
						ua.setNickName(rs.getString(15));
						lessonList.add(ua);
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
			
//获取后台所有写过信息的人的id和名字 group by username
			public static List<UserApplication> SearchChooserList() throws SQLException {
				List<UserApplication> lessonList= new ArrayList<UserApplication>();
				Connection conn = ConnectionFactory.makeConnection();
				// System.err.println(conn);
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				try {
					
					String sql = "SELECT csl.id,csl.chooseUserName,count(*) as count FROM ixinhai_chooses_song_log csl group by csl.chooseUserName  order by count";
					System.out.println(sql);
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						UserApplication ua= new UserApplication();
						ua.setId(rs.getInt(1));
						ua.setChooseUserName(rs.getString(2));
						ua.setMsgCount(rs.getInt(3));
						lessonList.add(ua);
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

	//获取后台所有写过信息的人的id和名字 group by username
			public static List<UserApplication> SearchNickNameList() throws SQLException {
				List<UserApplication> lessonList= new ArrayList<UserApplication>();
				Connection conn = ConnectionFactory.makeConnection();
				// System.err.println(conn);
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				try {
					
					String sql = "SELECT csl.id,el.nickName,count(*) as count FROM ixinhai_chooses_song_log csl left join ixinhai_employee_login el on el.id=csl.createId group by el.nickName  order by count";
					System.out.println(sql);
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						UserApplication ua= new UserApplication();
						ua.setId(rs.getInt(1));
						ua.setNickName(rs.getString(2));
						ua.setMsgCount(rs.getInt(3));
						lessonList.add(ua);
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
	//根据点歌日志的id查询电话号码
			public static List<UserApplication> SearchTelBychooseLogId(String chooseLogId) throws SQLException {
				List<UserApplication> lessonList= new ArrayList<UserApplication>();
				Connection conn = ConnectionFactory.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				//String tel=null;
				try {
					
					String sql = "select csl.chooseUserTelNumber,csl.information,csl.chooseUserName from ixinhai_chooses_song_log csl  where csl.id="+chooseLogId ;
					System.out.println("Sql>"+sql);
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						UserApplication ua= new UserApplication();
						ua.setChooseUserTelNumber(rs.getString(1));
						ua.setInformation(rs.getString(2));
						ua.setChooseUserName(rs.getString(3));
						lessonList.add(ua);
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
					
			
	public static void main(String[] args) throws SQLException {
		//int count =BackgroundSql.SearchDealDateCount(3);
		//System.out.println(count);
		
//		List<UserApplication> lessonList= new ArrayList<UserApplication>();
//		lessonList=BackgroundSql.SearchMainData(1,15);
//		for(int i=0;i<lessonList.size();i++){
//		System.out.println(lessonList.get(i).getId()
//				);
//		}
//		
//		List<UserApplication> lessonList= new ArrayList<UserApplication>();
//		lessonList=BackgroundSql.SearchNickNameList();
//		for(int i=0;i<lessonList.size();i++){
//		System.out.println(lessonList.get(i).getNickName());
		List<UserApplication> lessonList= new ArrayList<UserApplication>();				
		lessonList=BackgroundSql.SearchTelBychooseLogId("32");
		for(int i=0;i<lessonList.size();i++){
			System.out.println(lessonList.get(i).getInformation());
		}
	//		}
	}
	
	
	

}
