package com.canyou.service.Account;

import org.springframework.beans.factory.annotation.Autowired;

import com.canyou.model.Account.AccountDao;
import com.canyou.model.Account.AccountVO;

public class AccountServiceImpl implements AccountService{
	@Autowired
	public AccountDao accountDao;

	public int insert(AccountVO vo) {
		return accountDao.insert(vo);
	}

	public int update(AccountVO vo) {
		return accountDao.update(vo);		
    }

	public AccountVO findByEmail(String email) {
		return accountDao.findByEmail(email);
	}

	public int updateState(int accountId, String state) {
		return accountDao.updateState(accountId, state);
	} 
}
