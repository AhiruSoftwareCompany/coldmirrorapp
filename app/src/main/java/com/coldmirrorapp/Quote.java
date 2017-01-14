package com.coldmirrorapp;

/**
 * Holds Quotes
 */

public class Quote {
	private String id;
	private String name;

	public Quote(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
