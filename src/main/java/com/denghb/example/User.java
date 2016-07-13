package com.denghb.example;

import java.io.Serializable;

/**
 * 一个普通的java bean(pojo)
 * 
 * @author denghb
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2454078853070723625L;

	private String name;

	private String email;

	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", mobile=" + mobile + "]";
	}

}
