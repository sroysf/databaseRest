package com.mybatis3.mappers;

import com.mybatis3.entity.Account;
import java.util.List;

public interface AccountMapper
{
    List<Account> findAllAccounts();
    Account findAccountById(Integer id);
    void insertAccount(Account account);
}