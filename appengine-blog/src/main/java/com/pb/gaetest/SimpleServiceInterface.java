package com.pb.gaetest;

import java.util.List;

public interface SimpleServiceInterface {

	public abstract List allItems();

	public abstract void addItem(final SimpleEntity se);

}