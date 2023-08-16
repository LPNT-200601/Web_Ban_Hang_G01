package estore.repository.service;

import java.util.List;

import estore.repository.Order;

public interface OrderService {

	Order getById(Long id);

	void create(Order item);

	void update(Order item);

	void deleteById(Long id);

	List<Order> findAll();

	
}
