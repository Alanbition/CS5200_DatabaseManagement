package edu.northeastern.cs5200.models;

public class ImageWidget extends Widget{

	private String src;
	
	public ImageWidget(Integer id, String name, Integer width, Integer height, String css_class, String css_style, String text,
			Integer order, String src) {
		super(id, name, width, height, css_class, css_style, text, order);
		// TODO Auto-generated constructor stub
		this.src = src;
	}
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src  = src;
	}
}
