package estore.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estore.repository.Order;
import estore.repository.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;

	@Override
	public Order getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void create(Order item) {
		dao.save(item);
		
	}

	@Override
	public void update(Order item) {
		dao.save(item);
		
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}
}
