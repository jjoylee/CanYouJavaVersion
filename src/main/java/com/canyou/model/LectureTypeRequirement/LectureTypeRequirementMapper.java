package com.canyou.model.LectureTypeRequirement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LectureTypeRequirementMapper implements RowMapper<LectureTypeRequirementVO>{

	@Override
	public LectureTypeRequirementVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LectureTypeRequirementVO lectureTypeRequirement = new LectureTypeRequirementVO();
        lectureTypeRequirement.setAccountId(rs.getInt("accountId"));
        lectureTypeRequirement.setCutline(rs.getInt("cutline"));
        lectureTypeRequirement.setId(rs.getInt("id"));
        lectureTypeRequirement.setLectureTypeId(rs.getInt("lectureTypeId"));
        lectureTypeRequirement.setLectureTypeName(rs.getString("lectureTypeName"));
        lectureTypeRequirement.setLectureCategoryName(rs.getString("lectureCategoryName"));
        lectureTypeRequirement.setLectureCategoryId(rs.getInt("lectureCategoryId"));
        return lectureTypeRequirement;
	}

}
