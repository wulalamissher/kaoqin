package com.ljx.dao;

import com.ljx.bean.AdministratorEntity;
import com.ljx.bean.TeacherEntity;

public interface AdministratorEntityDAO {

	public String login(int id, String pwd);
	public AdministratorEntity queryAdministratorEntityById(int id);
	public int updateAdministratorEntity(AdministratorEntity student);
	public int updateAdministratorPwd(int id, String oldPwd, String newPwd);
	public int updateAdministratorEmail(int id, String email);
	public TeacherEntity queryTeacherEntityById(int id);
	public TeacherEntity[] queryTeacherEntityAll();
	public int updateTeacherEntity(TeacherEntity student);
}
