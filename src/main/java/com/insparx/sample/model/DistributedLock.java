package com.insparx.sample.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class DistributedLock implements Serializable {

	private static final long serialVersionUID = -48297666082098398L;

	@Id
	private int id;

	@Indexed(unique = true)
	private String key;

	private Boolean locked;

	public DistributedLock(int id, String key, Boolean locked) {
		this.id = id;
		this.key = key;
		this.locked = locked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public String toString() {
		return "DistributedLock [id=" + id + ", key=" + key + ", locked=" + locked + "]";
	}

}
