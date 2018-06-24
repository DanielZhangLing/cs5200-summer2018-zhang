package edu.neu.cs5200.models;

public class YouTubeWidget extends Widget {
	private int youTubeWidgetId;
	private String url;
	private boolean sharable;
	private boolean expandable;

	public YouTubeWidget() {
		super();
	}

	public YouTubeWidget(Widget widget, int youTubeWidgetId, String url, boolean sharable, boolean expandable) {
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
		this.youTubeWidgetId = youTubeWidgetId;
		this.url = url;
		this.sharable = sharable;
		this.expandable = expandable;
	}
	
    public YouTubeWidget(String name,  String text, int order, int width, int height, String url){
        this.name=name;
        this.text=text;
        this.order=order;
        this.type="youtube";
        this.width = width;
        this.height = height;
        this.url = url;
    }

	public int getYouTubeWidgetId() {
		return youTubeWidgetId;
	}

	public void setYouTubeWidgetId(int youTubeWidgetId) {
		this.youTubeWidgetId = youTubeWidgetId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isSharable() {
		return sharable;
	}

	public void setSharable(boolean sharable) {
		this.sharable = sharable;
	}

	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

}
