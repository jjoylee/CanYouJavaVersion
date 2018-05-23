package com.canyou.model.SectionRequirement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SectionRequirementMapper implements RowMapper<SectionRequirementVO>{

	@Override
	public SectionRequirementVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SectionRequirementVO sectionRequirement = new SectionRequirementVO();
        sectionRequirement.setId(rs.getInt("id"));
        sectionRequirement.setLectureTypeId(rs.getInt("lectureTypeId"));
        sectionRequirement.setAccountId(rs.getInt("accountId"));
        sectionRequirement.setCutline(rs.getInt("Cutline"));
        return sectionRequirement;
	}

}
