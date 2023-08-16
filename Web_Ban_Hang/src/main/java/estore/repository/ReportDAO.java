package estore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportDAO extends JpaRepository<Order, Long>{
	
}
