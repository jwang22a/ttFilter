package commonUtil;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class JDBC {
	public static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    //String url = "jdbc:mysql://10.76.26.17:3306/tzyd";
	    //String username = "root";
	    //String password = "root";
	    String url = "jdbc:mysql://10.76.2.54:3306/app";
	    String username = "app";
	    String password = "1qaz2wsx";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
	    /*String url = "jdbc:mysql://10.76.26.17:3306/tzyd";
	    String username = "root";
	    String password = "root";*/
		String url = "jdbc:mysql://10.76.2.54:3306/app";
	    String username = "app";
	    String password = "1qaz2wsx";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	        System.out.println("1111111111"+conn);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    //return conn;
	}
}
