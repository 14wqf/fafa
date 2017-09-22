package com.GG.test.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.GG.test.model.t_user;

public class SetUserRepositroller {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	public void save(t_user user) {
		getSession().save(user);
	}
	public List<t_user> getUser(){
		DetachedCriteria dc = DetachedCriteria.forClass(t_user.class);
		Criteria criteria = dc.getExecutableCriteria(getSession());
		List<t_user> user =criteria.list();
		return user;
	}
}
