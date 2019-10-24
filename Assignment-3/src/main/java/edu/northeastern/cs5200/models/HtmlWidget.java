package edu.northeastern.cs5200.models;

public class HtmlWidget extends Widget{
	private String html;

	public HtmlWidget(Integer id, String name, Integer width, Integer height, String css_class, String css_style, String text,
			Integer order, String html) {
		super(id, name, width, height, css_class, css_style, text, order);
		// TODO Auto-generated constructor stub
		this.html = html;
	}


	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
}
