package edu.poly.shop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.service.CustomerService;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
