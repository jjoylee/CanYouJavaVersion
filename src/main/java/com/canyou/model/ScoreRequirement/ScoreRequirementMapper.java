package com.canyou.model.ScoreRequirement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ScoreRequirementMapper implements RowMapper<ScoreRequirementVO>{

	@Override
	public ScoreRequirementVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ScoreRequirementVO scoreRequirement = new ScoreRequirementVO();
        scoreRequirement.setAccountId(rs.getInt("accountId"));
        scoreRequirement.setCutline(rs.getInt("cutline"));
        scoreRequirement.setId(rs.getInt("id"));
        return scoreRequirement;
	}
}
