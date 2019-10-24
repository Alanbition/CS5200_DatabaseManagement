package edu.northeastern.cs5200.models;

import java.sql.Date;




public class Widget {

	private Integer id;
	private String name;
	private Integer width;
	private Integer height;
	private String css_class;
	private String css_style;
	private String text;
	private Integer order;
	private String url;
	private Boolean shareble;
	private Boolean expandable;
	private String src;
	private String html;
	private Integer size;
	private enum type {
		  heading,
		  youtube,
		  html,
		  image
		}
	private type dtype;
	Page pages;
	
	
	
	public Widget(Integer id, String name, Integer width, Integer height, String css_class, String css_style, String text, Integer order, String url, Boolean shareble, Boolean expandable, String src, String html, Integer size, type dtype) {
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.css_class = css_class;
		this.css_style = css_style;
		this.text = text;
		this.order = order;
		this.url = url;
		this.shareble = shareble;
		this.expandable = expandable;
		this.src = src;
		this.html = html;
		this.size = size;
		this.dtype = dtype;
	}

	public Widget(Integer id, String name, Integer width, Integer height, String css_class, String css_style, String text, Integer order) {
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.css_class = css_class;
		this.css_style = css_style;
		this.text = text;
		this.order = order;
		this.url = url;
		this.shareble = shareble;
		this.expandable = expandable;
		this.src = src;
		this.html = html;
		this.size = 2;
	}	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getCss_class() {
		return css_class;
	}
	public void setCss_class(String css_class) {
		this.css_class = css_class;
	}
	public String getCss_style() {
		return css_style;
	}
	public void setCss_style(String css_style) {
		this.css_style = css_style;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}

	//Youtube
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Boolean getExpandable() {
		return expandable;
	}
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}	
	

	public Boolean getShareble() {
		return shareble;
	}
	public void setShareble(Boolean shareble) {
		this.shareble = shareble;
	}

	//Image
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src  = src;
	}
	
	//Html
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}

	//Heading
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

	//ENUM
	public type getType() {
		return dtype;
	}
	public void setType(type dtype) {
		this.dtype = dtype;
	}
}
