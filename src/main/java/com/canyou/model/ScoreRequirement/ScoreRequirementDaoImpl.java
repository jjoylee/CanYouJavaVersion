package com.canyou.model.ScoreRequirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreRequirementDaoImpl implements ScoreRequirementDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public int insert(ScoreRequirementVO vo) {
		String query = "Insert into ScoreRequirement(accountId,cutline) VALUES(?, ?)";
      	return jdbcTemplate.update(query,vo.getAccountId(),vo.getCutline());
	}

	@Override
	public int update(ScoreRequirementVO vo) {
		String query = "UPDATE ScoreRequirement SET accountId = ?, cutline=? where id = ?";
		return jdbcTemplate.update(query,vo.getAccountId(),vo.getCutline(),vo.getId());
	}

	@Override
	public int delete(int id) {
		String query = "delete from ScoreRequirement where id=?d";
		return jdbcTemplate.update(query,id);
	}

	@Override
	public List<ScoreRequirementVO> findByAccountId(int accountId) {
		 String query = "select *from ScoreRequirement where accountId=?";
         return jdbcTemplate.query(query, new Object[]{accountId}, new ScoreRequirementMapper());
	}

	@Override
	public ScoreRequirementVO findById(int id) {
		String query = "select *from ScoreRequirement where id=?";
        List<ScoreRequirementVO> list = jdbcTemplate.query(query, new Object[]{id}, new ScoreRequirementMapper());
        return (list.size() == 0)? null : list.get(0);
	}

	@Override
	public ScoreRequirementVO findByAccountIdForCheck(int accountId) {
		List<ScoreRequirementVO> list = findByAccountId(accountId);
		return (list.size() == 0)? null : list.get(0);
	}

}
