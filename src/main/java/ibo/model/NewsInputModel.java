package ibo.model;

/**
 * just used to capture user request body of xml, json 
 */
public class NewsInputModel {
	
	private String text;
	
	public NewsInputModel() {
		
	}


	public NewsInputModel(String text) {

		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	

}
