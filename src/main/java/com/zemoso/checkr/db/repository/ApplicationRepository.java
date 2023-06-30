package com.zemoso.checkr.db.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationRepository {
    protected Logger logger;

    protected ApplicationRepository(Class<?> classType){
        logger = LogManager.getLogger(classType);
    }
}
