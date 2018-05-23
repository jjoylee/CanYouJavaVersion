package com.canyou.model.LectureType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class LectureTypeDaoImpl implements LectureTypeDao{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<LectureTypeVO> findByCategoryId(int id) {
		String query = "select * from LectureType where lectureCategoryId = ?";
        return jdbcTemplate.query(query, new Object[]{id}, new LectureTypeMapper());
	}
}
