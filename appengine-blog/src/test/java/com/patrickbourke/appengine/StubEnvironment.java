package com.patrickbourke.appengine;

import java.io.File;

import com.google.appengine.api.datastore.dev.LocalDatastoreService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;

/**
 * A Stub AppEngine Environment for JUnit tests.
 *
 * @author Patrick Bourke <pbourke@gmail.com>
 */
public class StubEnvironment implements ApiProxy.Environment {
	public String getAppId() {
		return "Unit Tests";
	}

	public String getVersionId() {
		return "1.0";
	}

	public void setDefaultNamespace(String s) {
	}

	public String getRequestNamespace() {
		return "gmail.com";
	}

	public String getDefaultNamespace() {
		return "";
	}

	public String getAuthDomain() {
		return "gmail.com";
	}

	public boolean isLoggedIn() {
		return false;
	}

	public String getEmail() {
		return "";
	}

	public boolean isAdmin() {
		return false;
	}

	/**
	 * Establishes the AppEngine test environment. Should be called in a JUnit setup method.
	 */
    public static void setUpAppEngineTest() {
        ApiProxy.setEnvironmentForCurrentThread(new StubEnvironment());
        final File localProxyDirectory = new File(System.getProperty("java.io.tmpdir"));
    	ApiProxyLocalImpl apiProxyLocalImpl = new ApiProxyLocalImpl(localProxyDirectory) {};
        apiProxyLocalImpl.setProperty(LocalDatastoreService.NO_STORAGE_PROPERTY,Boolean.TRUE.toString()); 	
    	ApiProxy.setDelegate(apiProxyLocalImpl);
    }
}
