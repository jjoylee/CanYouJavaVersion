package com.canyou.model.SectionRequirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SectionRequirementDaoImpl implements SectionRequirementDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(SectionRequirementVO vo) {
		String query = "Insert into SectionRequirement(accountId,lectureTypeId,cutline) VALUES(?,?,?)";
        return jdbcTemplate.update(query,vo.getAccountId(),vo.getLectureTypeId(),vo.getCutline());
	}

	@Override
	public int update(SectionRequirementVO vo) {
		String query = "UPDATE SectionRequirement SET lectureTypeId = ?, accountId=?, cutline = ? where id = ?";
		return jdbcTemplate.update(query,vo.getLectureTypeId(),vo.getAccountId(),vo.getCutline(),vo.getId());
	}

	@Override
	public int delete(int id) {
		String query = "delete from SectionRequirement where id= ?";
		return jdbcTemplate.update(query, id);
	}

	@Override
	public List<SectionRequirementVO> findByAccountId(int accountId) {
		String query = "select * from SectionRequirement where accountId=?";
        return jdbcTemplate.query(query, new Object[]{accountId}, new SectionRequirementMapper());
	}

	@Override
	public SectionRequirementVO findById(int id) {
		String query = "select * from SectionRequirement where id = ?";
        List<SectionRequirementVO> list = jdbcTemplate.query(query, new Object[]{id},new SectionRequirementMapper());
        return (list.size() == 0)? null : list.get(0);
	}

	@Override
	public SectionRequirementVO findByAccountIdForCheck(int accountId) {
		List<SectionRequirementVO> list = findByAccountId(accountId);
		return (list.size() == 0)? null : list.get(0);	
	}
}
