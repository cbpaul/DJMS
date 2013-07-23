package com.paul.service;

import javax.jws.WebService;

import com.paul.model.Accountrecord;

@WebService
public interface AccountrecordManager extends GenericManager<Accountrecord, Long> {
    
}