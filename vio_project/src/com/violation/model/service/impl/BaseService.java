package com.violation.model.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.violation.Constants;


@Service
public class BaseService implements Constants{
    
	@Autowired
    private SessionFactory sessionFactory;
    private Session session;

    
    public SessionFactory getSessionFactory() {
        
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
}

