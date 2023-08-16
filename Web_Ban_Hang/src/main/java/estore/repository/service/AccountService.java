package estore.repository.service;

import java.util.List;

import estore.repository.Account;

public interface AccountService {

	Account getByUsername(String username);

	void create(Account item);

	void update(Account item);

	void deleteByUsername(String username);

	List<Account> findAll();

	

	
	
}
