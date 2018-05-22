package com.canyou.model.Account;

public interface AccountDao {
	int insert(AccountVO vo);
	int update(AccountVO vo);
	AccountVO findByEmail(String email);
	int updateState(int accountId, String state);
}
