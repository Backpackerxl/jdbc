package com.mysql.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.activation.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.Connection;

public class DBUtilPro {
	private static javax.sql.DataSource dataSource;
	static {
		try {
			InputStream in = new FileInputStream("D:/Users/Administrator/eclipse-workspace/JDBC/lib/druid.properties");
			Properties p = new Properties();
			// ���������ļ�����ȡ�Ĵ���������Դ������Ӷ���ͨ������Դ����
			p.load(in);
			dataSource = DruidDataSourceFactory.createDataSource(p);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��������Դ����
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return (DataSource) dataSource;

	}

	/**
	 * ͨ�����ݿ����ӳش�������
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static java.sql.Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * �رս����
	 * 
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر�Ԥ�������
	 * 
	 * @param ps
	 */
	public static void closePreparedStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر�����
	 * 
	 * @param cn
	 */
	public static void closeConnection(Connection cn) {
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * �����ͷŲ�ѯ����Դ
	 * 
	 * @param cn
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection cn, PreparedStatement ps, ResultSet rs) {
		closeResultSet(rs);
		closePreparedStatement(ps);
		closeConnection(cn);
	}

	/**
	 * �����ͷ���ɾ�ĵ���Դ
	 * 
	 * @param cn
	 * @param ps
	 */
	public static void close(Connection cn, PreparedStatement ps) {
		closePreparedStatement(ps);
		closeConnection(cn);
	}

}
