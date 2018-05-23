package com.canyou.model.LectureType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class LectureTypeDaoImpl implements LectureTypeDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(LectureTypeVO vo) {
		String query = "Insert into LectureType(lectureCategoryId,name) VALUES(?,?)";
		return jdbcTemplate.update(query,vo.getLectureCategoryId(),vo.getName());
	}

	@Override
	public int update(LectureTypeVO vo) {
		String query = "UPDATE LectureType SET name = ?, lectureCategoryId = ? where id = @Id";
        return jdbcTemplate.update(query,vo.getName(),vo.getLectureCategoryId(),vo.getId());
	}

	@Override
	public int delete(int id) {
		String query = "delete from LectureType where id= ?";
		return jdbcTemplate.update(query,id);
	}

	@Override
	public List<LectureTypeVO> findByCategoryId(int id) {
		String query = "select * from LectureType where lectureCategoryId = ?";
        return jdbcTemplate.query(query, new Object[]{id}, new LectureTypeMapper());
	}

	@Override
	public List<LectureTypeVO> findAll() {
		String query = "select * from LectureType";
        return jdbcTemplate.query(query, new LectureTypeMapper());
	}
}
