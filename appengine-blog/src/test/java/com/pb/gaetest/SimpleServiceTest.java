package com.pb.gaetest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.dev.LocalDatastoreService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"service-beans.xml"})
public class SimpleServiceTest {
	@Autowired
	private SimpleServiceInterface service;
	
	@Before
	public void setUp() {
	    ApiProxy.setEnvironmentForCurrentThread(new StubEnvironment());
		ApiProxyLocalImpl apiProxyLocalImpl = new ApiProxyLocalImpl(new File(".")) {};
        apiProxyLocalImpl.setProperty(LocalDatastoreService.NO_STORAGE_PROPERTY,Boolean.TRUE.toString()); 	
		ApiProxy.setDelegate(apiProxyLocalImpl);
    }
	
	@Test
	@Transactional
	public void testAdd() {
		assertTrue(service != null);
		final int sizeBeforeAdd = service.allItems().size();
		final SimpleEntity entity = new SimpleEntity();
		service.addItem(entity);
		final List results = new LinkedList<SimpleEntity>(service.allItems());
		assertTrue( results.contains(entity) );
		assertEquals(sizeBeforeAdd + 1, results.size());
	}
}
