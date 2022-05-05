package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.repository.AccountRepository;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CustomerService;
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
private AccountRepository accountRepository;
	@Autowired
	CustomerService customerService;
	// phuowng thuwcs loging
	@Override
	public Account login( String username , String password) {
		// goi thuc hien tim kiem u ser name
		Optional<Account> opExist = findById(username);
		// kieemr tra su ton tai cua uare vaf so sanh voi user duoc truyen vao trong fomt
		if(opExist.isPresent() && bCryptPasswordEncoder.matches(password, opExist.get().getPassword())) {
			// thuc hien xoa trang mat khau de trar ve vi khong can hien thi
			opExist.get().setPassword("");
			// tra ve account tim kiem va get
			return opExist.get();
		}
		// neu nguoc lai thi tra ve null ket qua login fail
		return null;
		
	}
	
	
	@Override
	public Customer loginUser(String email , String password) {
		Optional<Customer> opExist = customerService.findByEmail(email);
		if (opExist.isPresent() && opExist.get().getPassword().matches(password) ) {	
			
			opExist.get().setPassword("");
			return opExist.get();
		}
		return null;
		
	}

@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	// phuonwg thuwcs suwr dung bean de tao ma passs
	public <S extends Account> S save(S entity) {
		// tim kiem thong tin cua acccount 
		Optional<Account> opExist = findById(entity.getUsername());
		// kieemr tra toonf taij , neeus toonf taij thi thuc hien cap nhap thong tin
		if(opExist.isPresent()) {
			// kieemr tra string utltis trong tinhf huoongs khong nhap pass thi se khong uupdate 
			if(StringUtils.isEmpty(entity.getPassword())) {
				// thuwc hieen lay lai mat khau cu
				entity.setPassword(opExist.get().getPassword());
			}else {
				// neu co thay doi thi ma hoa pass 
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));

			}
		}
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		return accountRepository.save(entity);
	}

	@Override
	public <S extends Account> Optional<S> findOne(Example<S> example) {
		return accountRepository.findOne(example);
	}
	

	@Override
	public Page<Account> findAll(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public List<Account> findAll(Sort sort) {
		return accountRepository.findAll(sort);
	}

	@Override
	public List<Account> findAllById(Iterable<String> ids) {
		return accountRepository.findAllById(ids);
	}

	
	@Override
	public Optional<Account> findById(String id) {
		return accountRepository.findById(id);
	}

	@Override
	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
		return accountRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		accountRepository.flush();
	}

	@Override
	public <S extends Account> S saveAndFlush(S entity) {
		return accountRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(String id) {
		return accountRepository.existsById(id);
	}

	@Override
	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
		return accountRepository.saveAllAndFlush(entities);
	}

	@Override
	public void deleteInBatch(Iterable<Account> entities) {
		accountRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Account> entities) {
		accountRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return accountRepository.count();
	}

	@Override
	public void deleteById(String id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		accountRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Account entity) {
		accountRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		accountRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		accountRepository.deleteAllInBatch();
	}

	@Override
	public Account getOne(String id) {
		return accountRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Account> entities) {
		accountRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		accountRepository.deleteAll();
	}

	@Override
	public Account getById(String id) {
		return accountRepository.getById(id);
	}
	
}
