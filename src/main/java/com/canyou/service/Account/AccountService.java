package com.canyou.service.Account;


import com.canyou.model.Account.AccountVO;

public interface AccountService {
	int insert(AccountVO vo);
	int update(AccountVO vo);
	AccountVO findByEmail(String email);
	int updateState(int accountId, String state);
}
