package com.canyou.model.LectureType;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LectureTypeMapper implements RowMapper<LectureTypeVO>{

	@Override
	public LectureTypeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LectureTypeVO lectureType = new LectureTypeVO();
        lectureType.setId(rs.getInt("id"));
        lectureType.setLectureCategoryId(rs.getInt("lectureCategoryId"));
        lectureType.setName(rs.getString("name"));
        return lectureType;
	}

}
