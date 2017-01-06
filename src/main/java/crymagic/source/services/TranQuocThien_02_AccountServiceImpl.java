package crymagic.source.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.repositories.TranQuocThien_02_AccountRepository;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
@Service
public class TranQuocThien_02_AccountServiceImpl implements TranQuocThien_02_AccountService{
	
	//Override lại các phương thức bên interface TranQuocThien_02_AccountService
	private TranQuocThien_02_AccountRepository accountRepository;
	
	@Autowired
	public void setAccountRepository(TranQuocThien_02_AccountRepository accountRepository){
		this.accountRepository = accountRepository;
	}
	@Override
	public Iterable<TranQuocThien_02_Acount> getAllAccounts(){
		return accountRepository.findAll();
	}
	@Override
	public TranQuocThien_02_Acount getAccountById(Long id){
		return accountRepository.findOne(id);
	}
	@Override
	public TranQuocThien_02_Acount createAccount(TranQuocThien_02_Acount account){
		return accountRepository.save(account);
	}
	@Override
	public TranQuocThien_02_Acount updateAccount(TranQuocThien_02_Acount account){
		return accountRepository.save(account);
	}
	@Override
	public void deleteAccountById(Long id){
		accountRepository.delete(id);
	}
	@Override
	public Integer checkLogin(String email, String password){
		return accountRepository.checkLogin(email, password);
	}
	@Override
	public Integer getRoleByEmail(String email){
		return accountRepository.getRoleByEmail(email);
	}
	@Override
	public String getFullnameByEmail(String email){
		return accountRepository.getFullnameByEmail(email);
	}
	@Override
	public TranQuocThien_02_Acount findByEmail(String email){
		return accountRepository.findByEmail(email);
	}
	@Override
	public String getAvatarByEmail(String email){
		return accountRepository.getAvatarByEmail(email);
	}
	@Override
	public TranQuocThien_02_Acount getIdByEmail(String email){
		return accountRepository.getId(email);
	}
	@Override
	public Integer countUser(){
		return accountRepository.countUser();
	}
}
