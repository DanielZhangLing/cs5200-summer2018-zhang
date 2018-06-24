package edu.neu.cs5200.models;

public class ImageWidget extends Widget {
	private int imageWidgetId;
	private String src;

	public ImageWidget() {
		super();
	}

	public ImageWidget(Widget widget, int imageWidgetId, String src) {
		super();
		this.id = widget.getId();
		this.name = widget.getName();
		this.width = widget.getWidth();
		this.height = widget.getHeight();
		this.cssClass = widget.getCssClass();
		this.cssStyle = widget.getCssStyle();
		this.text = widget.getText();
		this.order = widget.getOrder();
		this.type = widget.getType();
		this.pageId = widget.getPageId();
		this.imageWidgetId = imageWidgetId;
		this.src = src;
	}
	
	public ImageWidget(String name, int width, int height, String text, int order, String src) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.text = text;
		this.order = order;
		this.type = "image";
		this.src = src;
	}

	public int getImageWidgetId() {
		return imageWidgetId;
	}

	public void setImageWidgetId(int imageWidgetId) {
		this.imageWidgetId = imageWidgetId;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
