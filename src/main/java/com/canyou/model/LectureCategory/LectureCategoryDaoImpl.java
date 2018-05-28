package com.canyou.model.LectureCategory;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LectureCategoryDaoImpl implements LectureCategoryDao{
	@Autowired
	JdbcTemplate jdbcTemplate;

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
