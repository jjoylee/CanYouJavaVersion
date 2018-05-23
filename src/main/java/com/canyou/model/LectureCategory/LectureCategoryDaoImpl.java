package com.canyou.model.LectureCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class LectureCategoryDaoImpl implements LectureCategoryDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(LectureCategoryVO vo) {
		String query = "Insert into LectureCategory(name) VALUES(?)";
        return jdbcTemplate.update(query, vo.getName());
	}

	@Override
	public int update(LectureCategoryVO vo) {
		String query = "UPDATE LectureCategory SET name = ? where id = ?";
        return jdbcTemplate.update(query,vo.getName(),vo.getId());
	}

	@Override
	public int delete(int id) {
		String query = "delete from LectureCategory where id= ?";
        return jdbcTemplate.update(query, id);
	}

	@Override
	public List<LectureCategoryVO> findAll() {
		String query = "select * from LectureCategory";
        return jdbcTemplate.query(query, new LectureCategoryMapper());
	}

	@Override
	public List<LectureCategoryVO> findLectureTypeExist() {
		String query = "select * from LectureCategory where id in (select distinct lectureCategoryId from LectureType)";
        return jdbcTemplate.query(query, new LectureCategoryMapper());
	}

}
