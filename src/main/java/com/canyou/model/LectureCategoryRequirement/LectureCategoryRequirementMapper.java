package com.canyou.model.LectureCategoryRequirement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LectureCategoryRequirementMapper implements RowMapper<LectureCategoryRequirementVO>{

	@Override
	public LectureCategoryRequirementVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LectureCategoryRequirementVO lectureCategoryRequirement = new LectureCategoryRequirementVO();
        lectureCategoryRequirement.setId(rs.getInt("id"));
        lectureCategoryRequirement.setAccountId(rs.getInt("accountId"));
        lectureCategoryRequirement.setLectureCategoryId(rs.getInt("lectureCategoryId"));            
        lectureCategoryRequirement.setLectureCategoryName(rs.getString("lectureCategoryName"));
        lectureCategoryRequirement.setCutline(rs.getInt("cutline"));
        return lectureCategoryRequirement;
	}

}
