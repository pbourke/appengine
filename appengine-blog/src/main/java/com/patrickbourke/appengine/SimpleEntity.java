package com.patrickbourke.appengine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="SimpleEntity")
public class SimpleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( id == null || obj == null || !(obj instanceof SimpleEntity) ) {
			return super.equals(obj);
		}
		return this.id.equals( ((SimpleEntity)obj).id );
	}

	@Override
	public int hashCode() {
		if ( id == null ) {
			return super.hashCode();
		}
		return id.hashCode();
	}

	@Override
	public String toString() {
		return super.toString() + "(id="+getId()+")";
	}
}
