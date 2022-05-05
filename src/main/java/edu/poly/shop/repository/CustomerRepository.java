package edu.poly.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
List<Customer> findByNameContaining(String name);
Page<Customer> findByNameContaining(String name,Pageable pageable);
List<Customer> findByName(String name);

Optional<Customer> findByEmail(String email);

}
