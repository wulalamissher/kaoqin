/**
 * 
 */
package com.ljx.dao;

import com.ljx.bean.TeacherEntity;

/**
 * @author Administrator
 *
 */
public interface TeacherEntityDAO {
	public String login(int id, String pwd);
	public TeacherEntity queryTeacherEntityById(int id);
	public TeacherEntity[] queryTeacherEntityAll();
	public int updateTeacherEntity(TeacherEntity student);
	public int updateTeacherPwd(int id, String oldPwd, String newPwd);
	public int updateTeacherEmail(int id, String email);
}
