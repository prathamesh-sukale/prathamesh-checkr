package com.zemoso.checkr.core.service;

import com.zemoso.checkr.db.datastore.IDataStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationService {
    protected Logger logger;

    @Autowired
    protected IDataStore iDataStore;

    protected ApplicationService(Class<?> classType){
        logger = LogManager.getLogger(classType);
    }
}
