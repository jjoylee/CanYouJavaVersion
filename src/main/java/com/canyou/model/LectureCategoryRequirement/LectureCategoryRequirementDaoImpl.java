package com.canyou.model.LectureCategoryRequirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LectureCategoryRequirementDaoImpl implements LectureCategoryRequirementDao {
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(LectureCategoryRequirementVO vo) {
		String query = "Insert into LectureCategoryRequirement(accountId,lectureCategoryId,cutline) VALUES(?,?,?)";
        return jdbcTemplate.update(query, vo.getAccountId(),vo.getLectureCategoryId(),vo.getCutline());
	}

	@Override
	public int update(LectureCategoryRequirementVO vo) {
		String query = "UPDATE LectureCategoryRequirement SET accountId = ?, cutline = ?, lectureCategoryId = ? where id = ?";
        return jdbcTemplate.update(query, vo.getAccountId(), vo.getCutline(), vo.getLectureCategoryId(), vo.getId());
	}

	@Override
	public int delete(int id) {
		String query = "delete from LectureCategoryRequirement where id=?";
        return jdbcTemplate.update(query, id); 
	}

	@Override
	public List<LectureCategoryRequirementVO> findByAccountId(int accountId) {
		StringBuilder query = new StringBuilder();
        query.append("select a.*, b.name lectureCategoryName from lectureCategoryRequirement ");
        query.append("a inner join lectureCategory b on a.lectureCategoryId=b.id where accountId=?");
        return jdbcTemplate.query(query.toString(), new Object[]{accountId}, new LectureCategoryRequirementMapper());
	}

	@Override
	public LectureCategoryRequirementVO findByAccountAndCategoryId(int accountId, int category) {
		StringBuilder query = new StringBuilder();
		query.append("select a.*,b.name lectureCategoryName from lectureCategoryRequirement ");
		query.append("a inner join lectureCategory b on a.lectureCategoryId=b.id where accountId=? ");
		query.append("and lectureCategoryId = ?");
		List<LectureCategoryRequirementVO> list = jdbcTemplate.query(query.toString(), new Object[]{accountId,category}, new LectureCategoryRequirementMapper());
        return (list.size()==0) ? null : list.get(0);
	}

	@Override
	public LectureCategoryRequirementVO findById(int id) {
		 StringBuilder query = new StringBuilder(); 
		 query.append(" select a.*,b.name lectureCategoryName from lectureCategoryRequirement ");
		 query.append("a inner join lectureCategory b on a.lectureCategoryId=b.id where a.id = ?");
		 List<LectureCategoryRequirementVO> list = jdbcTemplate.query(query.toString(), new Object[]{id}, new LectureCategoryRequirementMapper());
	     return (list.size()==0) ? null : list.get(0);
	}

}
