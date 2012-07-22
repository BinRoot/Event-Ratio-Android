package com.hackathon.eventratio;

public class FacebookToken {
private FacebookToken token;
	
	public FacebookToken(){
		//NOPE
	}
	
	public FacebookToken getInstance() {
		if (token == null) {
			token = new FacebookToken();
		}
		return token;
	} 
	
	
}
