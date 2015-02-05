package com.imanage;

public class Session {
	private String username;
	private static Session session;
	
	private Session() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public static Session getSessionInstance(){
		if(session == null)
			session = new Session();
		return session;
	}
}
