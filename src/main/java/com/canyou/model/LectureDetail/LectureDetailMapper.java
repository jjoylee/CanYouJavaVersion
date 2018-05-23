package com.canyou.model.LectureDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LectureDetailMapper implements RowMapper<LectureDetailVO>{

	@Override
	public LectureDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LectureDetailVO lectureDetail = new LectureDetailVO();
        lectureDetail.setId(rs.getInt("id"));
        lectureDetail.setLectureCategoryId(rs.getInt("lectureCategoryId"));
        lectureDetail.setLectureTypeId(rs.getInt("lectureTypeId"));
        lectureDetail.setName(rs.getString("name"));
        lectureDetail.setScore(rs.getString("score"));
        lectureDetail.setSectionId(rs.getInt("sectionId"));
        lectureDetail.setAccountId(rs.getInt("accountId"));
        lectureDetail.setCredit(rs.getInt("credit"));
        lectureDetail.setLectureCategoryName(rs.getString("lectureCategoryName"));
        lectureDetail.setLectureTypeName(rs.getString("lectureTypeName"));
        lectureDetail.setSectionName(rs.getString("sectionName"));
        return lectureDetail;
	}
}
