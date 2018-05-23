package com.canyou.model.LectureTypeRequirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class LectureTypeRequirementDaoImpl implements LectureTypeRequirementDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(LectureTypeRequirementVO vo) {
		String query = "Insert into LectureTypeRequirement(accountId,lectureTypeId,cutline) VALUES(?,?,?)";
        return jdbcTemplate.update(query,vo.getAccountId(),vo.getLectureTypeId(),vo.getCutline());
	}

	@Override
	public int update(LectureTypeRequirementVO vo) {
		String query = "UPDATE LectureTypeRequirement SET lectureTypeId = ?, accountId = ?, cutline=? where id = ?";
		return jdbcTemplate.update(query,vo.getLectureTypeId(),vo.getAccountId(),vo.getCutline(),vo.getId());
	}

	@Override
	public int delete(int id) {
		String query = "delete from LectureTypeRequirement where id=?";
		return jdbcTemplate.update(query,id);
	}

	@Override
	public List<LectureTypeRequirementVO> findByAccountId(int accountId) {
		StringBuilder query = new StringBuilder();
		query.append("select a.*, b.name lectureTypeName ,c.name lectureCategoryName, ");
		query.append("c.id lectureCategoryId from lectureTypeRequirement a inner join lectureType b ");
		query.append("on a.lectureTypeId=b.id inner join lectureCategory c on b.lectureCategoryId = c.id ");
		query.append("where accountId=?");
        return jdbcTemplate.query(query.toString(), new Object[]{accountId}, new LectureTypeRequirementMapper());
	}

	@Override
	public LectureTypeRequirementVO findById(int id) {
		StringBuilder query = new StringBuilder();
		query.append("select a.*, b.name lectureTypeName ,c.name lectureCategoryName, c.id lectureCategoryId ");
		query.append("from lectureTypeRequirement a inner join lectureType b on a.lectureTypeId=b.id inner join ");
		query.append("lectureCategory c on b.lectureCategoryId = c.id where a.id = ?");
        List<LectureTypeRequirementVO> list = jdbcTemplate.query(query.toString(), new Object[]{id}, new LectureTypeRequirementMapper());
        return (list.size()==0) ? null : list.get(0);
	}

	@Override
	public LectureTypeRequirementVO findByAccountAndTypeId(int accountId, int type) {
		StringBuilder query = new StringBuilder();
		query.append("select a.*, b.name lectureTypeName ,c.name lectureCategoryName, c.id lectureCategoryId ");
		query.append("from lectureTypeRequirement a inner join lectureType b on a.lectureTypeId=b.id inner join ");
		query.append("lectureCategory c on b.lectureCategoryId = c.id where a.accountId = ? and a.lectureTypeId = ?");
		List<LectureTypeRequirementVO> list = jdbcTemplate.query(query.toString(), new Object[]{accountId, type}, new LectureTypeRequirementMapper());
        return (list.size()==0) ? null : list.get(0);
	}

}
