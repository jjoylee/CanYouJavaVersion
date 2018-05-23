package com.canyou.model.Section;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SectionMapper implements RowMapper<SectionVO>{

	@Override
	public SectionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SectionVO section = new SectionVO();
        section.setId(rs.getInt("id"));
        section.setLectureTypeId(rs.getInt("lectureTypeId"));
        section.setName(rs.getString("name"));
        return section;
	}

}
