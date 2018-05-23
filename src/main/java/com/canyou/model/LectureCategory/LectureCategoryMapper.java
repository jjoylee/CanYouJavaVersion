package com.canyou.model.LectureCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LectureCategoryMapper implements RowMapper<LectureCategoryVO>{

	@Override
	public LectureCategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LectureCategoryVO lectureCategory = new LectureCategoryVO();
        lectureCategory.setId(rs.getInt("id"));
        lectureCategory.setName(rs.getString("name"));
        return lectureCategory;
	}

}
