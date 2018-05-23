package com.canyou.model.Section;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SectionDaoImpl implements SectionDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SectionVO> findAll() {
		String query = "select * from Section";
        return jdbcTemplate.query(query, new SectionMapper());
	}

	@Override
	public List<SectionVO> findByTypeId(int typeId) {
		 String query = "select * from Section where lectureTypeId = ?";
         return jdbcTemplate.query(query, new Object[]{typeId},new SectionMapper());
	}

}
