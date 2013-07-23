package com.paul.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.dao.AccountrecordDao;
import com.paul.model.Accountrecord;
import com.paul.service.AccountrecordManager;

@Service("accountrecordManager")
@WebService(serviceName = "AccountrecordService", endpointInterface = "com.paul.service.AccountrecordManager")
public class AccountrecordManagerImpl extends GenericManagerImpl<Accountrecord, Long> implements AccountrecordManager {
    AccountrecordDao accountrecordDao;

    @Autowired
    public AccountrecordManagerImpl(AccountrecordDao accountrecordDao) {
        super(accountrecordDao);
        this.accountrecordDao = accountrecordDao;
    }
}