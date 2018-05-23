package com.canyou.model.Account;

import java.util.List;
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
		return jdbcTemplate.update(query, vo.getEmail(),vo.getPassword(),vo.getState());
	}

	@Override
	public int update(AccountVO vo) {
		String query = "UPDATE account SET password = ?, email = ?, state = ? where id = ?";
		return jdbcTemplate.update(query, vo.getPassword(),vo.getEmail(),vo.getState(),vo.getId());
	}

	@Override
	public AccountVO findByEmail(String email) {
		String query = "select * from account where email=?";
		List<AccountVO> list = jdbcTemplate.query(query, new Object[]{email}, new AccountMapper());
        return (list.size()==0) ? null : list.get(0);
	}

	@Override
	public int updateState(int accountId, String state) {
		String query = "UPDATE account SET state = ? where id = ?";
		return jdbcTemplate.update(query, state, accountId);
	}

}
