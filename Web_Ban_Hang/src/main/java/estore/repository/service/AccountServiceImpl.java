package estore.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estore.repository.Account;
import estore.repository.AccountDAO;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO dao;

	@Override
	public Account getByUsername(String username) {
		return dao.getById(username);
	}

	@Override
	public void create(Account item) {
		dao.save(item);
		
	}

	@Override
	public void update(Account item) {
		dao.save(item);
		
	}

	@Override
	public void deleteByUsername(String username) {
		dao.deleteById(username);
		
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	
}