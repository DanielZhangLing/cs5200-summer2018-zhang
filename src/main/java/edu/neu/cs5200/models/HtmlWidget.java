package edu.neu.cs5200.models;

public class HtmlWidget extends Widget {
	private int htmlWidgetId;
	private String html;

	public HtmlWidget() {
		super();
	}

	public HtmlWidget(Widget widget, int htmlWidgetId, String html) {
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
		this.htmlWidgetId = htmlWidgetId;
		this.html = html;
	}

	public int getHtmlWidgetId() {
		return htmlWidgetId;
	}

	public void setHtmlWidgetId(int htmlWidgetId) {
		this.htmlWidgetId = htmlWidgetId;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
