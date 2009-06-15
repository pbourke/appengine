package com.pb.gaetest;

import java.util.List;

import javax.persistence.EntityManager;

public class SimpleService implements SimpleServiceInterface {
	private EntityManager entityManager;
	
	public void setEntityManager(final EntityManager em) {
		entityManager = em;
	}
	
	public List allItems() {
		List resultList = entityManager.createQuery("SELECT e FROM com.pb.gaetest.SimpleEntity AS e").getResultList();
		resultList.size();
		return resultList;
	}
	
	public void addItem(final SimpleEntity se) {
		entityManager.persist(se);
	}
}
