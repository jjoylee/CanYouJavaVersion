package com.canyou.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccountMapper implements RowMapper<AccountVO>{

	@Override
	public AccountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AccountVO account = new AccountVO();
		account.setId(rs.getInt("id"));
		account.setEmail(rs.getString("email"));
		account.setPassword(rs.getString("password"));
		account.setState(rs.getString("state"));
		return account;
	}

}
