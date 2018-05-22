package com.canyou.model.Account;

import java.sql.Types;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(AccountVO vo) {
		String query = "Insert into account(email,password,state) VALUES(?,?,?)";
		jdbcTemplate.update(query, vo.getEmail(),vo.getPassword(),vo.getState());
       return 1;
	}

	@Override
	public int update(AccountVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AccountVO findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateState(int accountId, String state) {
		// TODO Auto-generated method stub
		return 0;
	}

}
