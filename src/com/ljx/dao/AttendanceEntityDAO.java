/**
 * 
 */
package com.ljx.dao;

import com.ljx.bean.AttendanceEntity;
import com.ljx.bean.TeacherEntity;

/**
 * @author Administrator
 *
 */
public interface AttendanceEntityDAO {
	
	public int insertAttendanceEntity(AttendanceEntity attEntity);	
	public AttendanceEntity[] queryAttendanceEntityByTeacherId(int teacherId);
	public AttendanceEntity[] queryAttendanceEntityByStuIdAndCourse(int studentId, int course_id);	
	public AttendanceEntity[] queryAttendanceEntityByTchIdAndCourse(int teacherId, int course_id);
	public int updateAttendanceEntity(AttendanceEntity attEntity);
}
