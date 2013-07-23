package com.paul.dao.hibernate;

import com.paul.model.Accountrecord;
import com.paul.dao.AccountrecordDao;
import com.paul.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("accountrecordDao")
public class AccountrecordDaoHibernate extends GenericDaoHibernate<Accountrecord, Long> implements AccountrecordDao {

    public AccountrecordDaoHibernate() {
        super(Accountrecord.class);
    }
}
