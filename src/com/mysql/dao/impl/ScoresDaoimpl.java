package com.mysql.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import com.alibaba.druid.util.StringUtils;
import com.mysql.dao.ScoresDao;
import com.mysql.module.Scores;
import com.mysql.util.DBUtilPro;

public class ScoresDaoimpl implements ScoresDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Vector> selectAllScores(Scores sco) {
		List<Vector> list = new ArrayList<>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = DBUtilPro.getConnection();
			StringBuilder sql = new StringBuilder("select * from stu_sro where 1=1	");
			if (!StringUtils.isEmpty(sco.getName())) {
				sql.append("and name like '%" + sco.getName() + "%'");
			}
			System.out.println(sql.toString());
			ps = cn.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getInt(4));
				v.add(rs.getInt(5));
				v.add(rs.getInt(6));
				sco.setLinux(rs.getInt(4));
				sco.setHtml(rs.getInt(5));
				sco.setMysql(rs.getInt(6));
				list.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closePreparedStatement(ps);
			closeConnection(cn);
		}
		return list;
	}

	private void closeConnection(Connection cn) {
		// TODO Auto-generated method stub
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closePreparedStatement(PreparedStatement ps) {
		// TODO Auto-generated method stub
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int deleteStudent(int result) {
		Connection cn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			cn = DBUtilPro.getConnection();
			ps = cn.prepareStatement("delete from stu_sro  where id =?");
			ps.setInt(1, result);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeConnection(cn);
		}
		return i;
	}

	@Override
	public int updateAdmin(Scores sco) {
		Connection cn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			cn = DBUtilPro.getConnection();
			ps = (PreparedStatement) cn.prepareStatement(
					"update stu_sro set   studentid = ? ,name = ? ,linux = ?, html = ? ,mysql = ? where id = ?");
			ps.setNString(1, sco.getStudentid());
			ps.setNString(2, sco.getName());
			ps.setInt(3, sco.getLinux());
			ps.setInt(4, sco.getHtml());
			ps.setInt(5, sco.getMysql());
			ps.setInt(6, sco.getId());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeConnection(cn);
		}
		return i;
	}

	@Override
	public int register(Scores stu) {
		int i = 0;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = DBUtilPro.getConnection();
			ps = cn.prepareStatement("insert into stu_sro value(id,?,?,?,?,?)");
			ps.setNString(1, stu.getStudentid());
			ps.setNString(2, stu.getName());
			ps.setInt(3, stu.getLinux());
			ps.setInt(4, stu.getHtml());
			ps.setInt(5, stu.getMysql());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
			closeConnection(cn);
		}
		return i;
	}

}
