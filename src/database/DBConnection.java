package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
//import java.util.Scanner;

public class DBConnection {

	
	private static final String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dburl="jdbc:sqlserver://localhost:1433;DatabaseName=student_attend";
	private static final String userName="sa";
	private static final String userPwd="654321";
	private static Connection dbConn=null;
	//Connection dbConn;
	static//��̬�����
	{
				try
				{
					
					Class.forName(driverName);
		
				}catch( ClassNotFoundException e)
				{
					e.printStackTrace();
					System.out.println("����ʧ��");
				}
	
		}

	public static Connection getConnection() {
		// TODO �Զ����ɵķ������
		try{
			dbConn=DriverManager.getConnection(dburl, userName, userPwd);
			System.out.println("�������ݿ�ɹ�");
			return dbConn;
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("����ʧ��");
			return null;
		}
		
	}
	public static void close(Connection conn) {
		if (conn != null) { 
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) { 
			try {
				pstmt.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if (stmt != null) { 
			try {
				stmt.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) { 
			try {
				rs.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	public static void close(Connection conn,Statement stat,ResultSet rs)
//	{
//		try{
//			if(rs!=null)
//			{
//				rs.close();
//			}
//			if(stat!=null)
//			{
//				stat.close();
//			}
//			if(conn!=null)
//			{
//				conn.close();
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//	}
//	public static void close(Connection conn,Statement stat)
//	{
//		close(conn,stat,null);
//	}
}
