package com.coldmirrorapp;

class Quote {
	private String id;
	private String name;
	private Category category;

	Quote(Category category, String id, String name) {
		this.category = category;
		this.id = id;
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	String getColor() {
		return category.toString();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
