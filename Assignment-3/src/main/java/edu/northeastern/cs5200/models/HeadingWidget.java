package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget{
	private Integer size;

	public HeadingWidget(Integer id, String name, Integer width, Integer height, String css_class, String css_style, String text,
			Integer order, Integer size) {
		super(id, name, width, height, css_class, css_style, text, order);
		// TODO Auto-generated constructor stub
		this.size = size;
	}


	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
