//package edu.poly.shop.repository;
//
//import java.util.List;
//
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.JpaRepository;
//import edu.poly.shop.domain.Product;
//import edu.poly.shop.model.Report;
//
//public interface ReportDao extends JpaRepository <Product, Integer> {
//	
//	@Query("SELECT new Report(o.category, sum(o.price), count(o)) "
//			+ " FROM Product o "
//			+ " GROUP BY o.category"
//			+ " ORDER BY sum(o.price) DESC")
//			List<Report> getInventoryByCategory();
//}
