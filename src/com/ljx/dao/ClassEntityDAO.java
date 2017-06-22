/**
 * 
 */
package com.ljx.dao;

import com.ljx.bean.ClassEntity;

/**
 * @author Administrator
 *
 */
public interface ClassEntityDAO {
	
	public ClassEntity[] queryClassEntityByStudentId(int studentId);
	public ClassEntity[] queryClassEntityByTeacherId(int teacherId);
	
}
