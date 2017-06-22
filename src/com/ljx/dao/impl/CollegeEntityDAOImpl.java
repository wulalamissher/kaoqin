package com.ljx.dao.impl;

//import java.awt.Image;
//import java.awt.image.RenderedImage;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.sql.Blob;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;

//import com.ljx.bean.ClassEntity;
import com.ljx.bean.CollegeEntity;
//import com.ljx.bean.TeacherEntity;
//import com.ljx.dao.ClassEntityDAO;
import com.ljx.dao.CollegeEntityDAO;
//import com.ljx.dao.TeacherEntityDAO;
import database.DBConnection;

public class CollegeEntityDAOImpl implements CollegeEntityDAO {

	// ��ȡ���н�ʦ��Ϣ
	public CollegeEntity[] queryCollegeEntityAll() {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String querySQL = "SELECT * FROM collegeinfo";
		CollegeEntity[] college = null;
		try {
			// �����ɹ����Ľ����
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // ���������, ִ�в�ѯ
			rs.last();
			college = new CollegeEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				college[i] = new CollegeEntity();
				college[i].setCollege_name(rs.getString("college_name"));
				college[i].setCollege_addr(rs.getString("college_address"));
				college[i].setCollege_contact(rs.getString("college_contact"));
				college[i].setCollege_tel(rs.getString("college_tel"));
				i++;
			}
			DBConnection.close(rs); // �رս����
			DBConnection.close(stmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return college;
	}
}
