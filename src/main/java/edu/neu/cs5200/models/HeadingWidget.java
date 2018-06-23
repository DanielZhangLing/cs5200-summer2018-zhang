package edu.neu.cs5200.models;

public class HeadingWidget extends Widget {
	private int headingWidgetId;
	private int size;

	public HeadingWidget() {
		super();
	}

	public HeadingWidget(Widget widget, int headingWidgetId, int size) {
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
		this.headingWidgetId = headingWidgetId;
		this.size = size;
	}

	public int getHeadingWidgetId() {
		return headingWidgetId;
	}

	public void setHeadingWidgetId(int headingWidgetId) {
		this.headingWidgetId = headingWidgetId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
