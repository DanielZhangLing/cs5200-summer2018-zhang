package edu.neu.cs5200;

import java.sql.Date;
import java.util.Collection;

import edu.neu.cs5200.dao.DeveloperDao;
import edu.neu.cs5200.dao.PageDao;
import edu.neu.cs5200.dao.RoleDao;
import edu.neu.cs5200.dao.WebsiteDao;
import edu.neu.cs5200.dao.WidgetDao;
import edu.neu.cs5200.models.Developer;
import edu.neu.cs5200.models.Website;
import edu.neu.cs5200.models.Widget;
import edu.neu.cs5200.models.YouTubeWidget;
import edu.neu.cs5200.models.Enum.RoleEnum;
import edu.neu.cs5200.models.HeadingWidget;
import edu.neu.cs5200.models.HtmlWidget;
import edu.neu.cs5200.models.ImageWidget;
import edu.neu.cs5200.models.Page;

public class hw_jdbc_last_first {

	private DeveloperDao developerDao = DeveloperDao.getInstance();
	private WebsiteDao websiteDao = WebsiteDao.getInstance();
	private RoleDao roleDao = RoleDao.getInstance();
	private PageDao pageDao = PageDao.getInstance();
	private WidgetDao widgetDao = WidgetDao.getInstance();

	public static void main(String[] args) {
		hw_jdbc_last_first jdbc = new hw_jdbc_last_first();
		jdbc.createDeveloper();
		jdbc.createWebsite();
		jdbc.createPage();
		jdbc.createWidget();
		jdbc.implementUpdate();
		jdbc.implementDelete();
		jdbc.disconnected();
	}

	public void createDeveloper() {
		Developer alice = new Developer("Alice", "Wonder", "alice", "alice", "alice@wonder.com", null, "4321rewq");
		Developer bob = new Developer("Bob", "Marley", "bob", "bob", "bob@marley.com", null, "5432trew");
		Developer charlie = new Developer("Charles", "Garcia", "charlie", "charlie", "chuch@garcia.com", null,
				"6543ytre");
		Developer dan = new Developer("Dan", "Martin", "dan", "dan", "dan@martin.com", null, "7654fda");
		Developer ed = new Developer("Ed", "Karaz", "ed", "ed", "ed@kar.com", null, "5678dfgh");
		developerDao.createDeveloper(alice);
		developerDao.createDeveloper(bob);
		developerDao.createDeveloper(charlie);
		developerDao.createDeveloper(dan);
		developerDao.createDeveloper(ed);
	}

	public void createWebsite() {
		Website facebook = new Website(0, "Facebook", "an online social media and social networking service", null,
				null, 1234234, 0);
		int facebookId = websiteDao
				.createWebsiteForDeveloper(developerDao.findDeveloperByUsername("alice").getDeveloperId(), facebook);
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), facebookId,
				RoleEnum.EDITOR.value());
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), facebookId,
				RoleEnum.ADMIN.value());
		Website twitter = new Website(0, "Twitter", "an online news and social networking service", null, null, 4321543,
				0);
		int twitterId = websiteDao
				.createWebsiteForDeveloper(developerDao.findDeveloperByUsername("bob").getDeveloperId(), twitter);
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), twitterId,
				RoleEnum.EDITOR.value());
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), twitterId,
				RoleEnum.ADMIN.value());
		Website wikipedia = new Website(0, "Wikipedia", "a free online encyclopedia", null, null, 3456654, 0);
		int wikipediaId = websiteDao
				.createWebsiteForDeveloper(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), wikipedia);
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), wikipediaId,
				RoleEnum.EDITOR.value());
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), wikipediaId,
				RoleEnum.ADMIN.value());
		Website cnn = new Website(0, "CNN", "an American basic cable and satellite television news channel", null, null,
				6543345, 0);
		int cnnId = websiteDao.createWebsiteForDeveloper(developerDao.findDeveloperByUsername("alice").getDeveloperId(),
				cnn);
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), cnnId,
				RoleEnum.EDITOR.value());
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), cnnId,
				RoleEnum.ADMIN.value());
		Website cnet = new Website(0, "CNET",
				"an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",
				null, null, 5433455, 0);
		int cnetId = websiteDao.createWebsiteForDeveloper(developerDao.findDeveloperByUsername("bob").getDeveloperId(),
				cnet);
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), cnetId,
				RoleEnum.EDITOR.value());
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), cnetId,
				RoleEnum.ADMIN.value());
		Website gizmodo = new Website(0, "Gizmodo",
				"a design, technology, science and science fiction website that also writes articles on politics", null,
				null, 4322345, 0);
		int gizmodoId = websiteDao
				.createWebsiteForDeveloper(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), gizmodo);
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), gizmodoId,
				RoleEnum.EDITOR.value());
		roleDao.assignWebsiteRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), gizmodoId,
				RoleEnum.ADMIN.value());
	}

	public void createPage() {
		Page home = new Page(0, "Home", "Landing page", null, null, 123434, 0);
		int homeId = pageDao.createPageForWebsite(websiteDao.findWebsiteByName("CNET").getId(), home);
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), homeId,
				RoleEnum.EDITOR.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), homeId,
				RoleEnum.REVIEWER.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), homeId,
				RoleEnum.WRITER.value());
		Page about = new Page(0, "About", "Website description", null, null, 234545, 0);
		int AboutId = pageDao.createPageForWebsite(websiteDao.findWebsiteByName("Gizmodo").getId(), about);
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), AboutId,
				RoleEnum.EDITOR.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), AboutId,
				RoleEnum.REVIEWER.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), AboutId,
				RoleEnum.WRITER.value());
		Page contact = new Page(0, "Contact", "Addresses, phones, and contact info", null, null, 345656, 0);
		int contactId = pageDao.createPageForWebsite(websiteDao.findWebsiteByName("Wikipedia").getId(), contact);
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), contactId,
				RoleEnum.EDITOR.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), contactId,
				RoleEnum.REVIEWER.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), contactId,
				RoleEnum.WRITER.value());
		Page preferences = new Page(0, "Preferences", "Where users can configure their preferences", null, null, 456776,
				0);
		int preferencesId = pageDao.createPageForWebsite(websiteDao.findWebsiteByName("CNN").getId(), preferences);
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), preferencesId,
				RoleEnum.EDITOR.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), preferencesId,
				RoleEnum.REVIEWER.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), preferencesId,
				RoleEnum.WRITER.value());
		Page profile = new Page(0, "Profile", "Users can configure their personal information", null, null, 567878, 0);
		int profileId = pageDao.createPageForWebsite(websiteDao.findWebsiteByName("CNET").getId(), profile);
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("bob").getDeveloperId(), profileId,
				RoleEnum.EDITOR.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("charlie").getDeveloperId(), profileId,
				RoleEnum.REVIEWER.value());
		roleDao.assignPageRole(developerDao.findDeveloperByUsername("alice").getDeveloperId(), profileId,
				RoleEnum.WRITER.value());

	}

	public void createWidget() {
		HeadingWidget head123 = new HeadingWidget("head123", "Welcome", 0, 0);
		widgetDao.createWidgetForPage(pageDao.findPageByTitle("Home").getId(), head123);
		HtmlWidget post234 = new HtmlWidget("post234", "<p>Lorem</p>", 0, null);
		widgetDao.createWidgetForPage(pageDao.findPageByTitle("About").getId(), post234);
		HeadingWidget head345 = new HeadingWidget("head345", "Hi", 1, 0);
		widgetDao.createWidgetForPage(pageDao.findPageByTitle("Contact").getId(), head345);
		HtmlWidget intro456 = new HtmlWidget("intro456", "<h1>Hi</h1>", 2, null);
		widgetDao.createWidgetForPage(pageDao.findPageByTitle("Contact").getId(), intro456);
		ImageWidget image345 = new ImageWidget("image345", 50, 100, null, 3, "/img/567.png");
		widgetDao.createWidgetForPage(pageDao.findPageByTitle("Contact").getId(), image345);
		YouTubeWidget video456 = new YouTubeWidget("video456", null, 0, 400, 300, "https://youtu.be/h67VX51QXiQ");
		widgetDao.createWidgetForPage(pageDao.findPageByTitle("Preferences").getId(), video456);
	}

	public void implementUpdate() {
		this.implementUpdateOne();
		this.implementUpdateTwo();
		this.implementUpdateThree();
	}

	private void implementUpdateOne() {
		Collection<Widget> widgets = widgetDao.findAllWidgets();
		Widget head345 = widgetDao.findWidgetByName("head345");
		int page = head345.getPageId();
		for (Widget widget : widgets) {
			if (widget.getPageId() == page) {
				int previousOrder = widget.getOrder();
				if (widget.getName().equals("head345"))
					widget.setOrder(3);
				else if (previousOrder <= 3)
					widget.setOrder(previousOrder - 1);
				else
					widget.setOrder(previousOrder + 1);
				widgetDao.updateWidget(widget.getId(), widget, false);
			}
		}
	}

	private void implementUpdateTwo() {
		Website cnet = websiteDao.findWebsiteByName("CNET");
		Collection<Page> pages = pageDao.findPagesForWebsite(cnet.getId());
		for (Page page : pages) {
			page.setTitle("CNET - " + page.getTitle());
			pageDao.updatePage(page.getId(), page);
		}
	}

	private void implementUpdateThree() {
		Page cnetHome = pageDao.findPageByTitle("CNET - Home");
		int pageId = cnetHome.getId();
		Developer bob = developerDao.findDeveloperByUsername("bob");
		Developer charlie = developerDao.findDeveloperByUsername("charlie");
		int bobId = bob.getId();
		int charlieId = charlie.getId();
		int bobRole = roleDao.findRoleByPageDeveloper(pageId, bobId);
		int charlieRole = roleDao.findRoleByPageDeveloper(pageId, charlieId);
		roleDao.updatePageRole(bobId, pageId, charlieRole);
		roleDao.updatePageRole(charlieId, pageId, bobRole);
	}

	public void implementDelete() {
		this.implementDeleteOne();
		this.implementDeleteTwo();
		this.implementDeleteThree();
	}

	public void implementDeleteOne() {
		Page contact = pageDao.findPageByTitle("Contact");
		Collection<Widget> widgets = widgetDao.findWidgetsForPage(contact.getId());
		int max = Integer.MIN_VALUE;
		Widget targetWidget = null;
		for (Widget widget : widgets) {
			if (widget.getOrder() > max) {
				max = widget.getOrder();
				targetWidget = widget;
			}
		}
		widgetDao.deleteWidget(targetWidget.getId());
	}

	public void implementDeleteTwo() {
		Website wiki = websiteDao.findWebsiteByName("Wikipedia");
		Collection<Page> pages = pageDao.findPagesForWebsite(wiki.getId());
		Date latest = new Date(Long.MIN_VALUE);
		Page targetPage = null;
		for (Page page : pages) {
			if (page.getUpdated().compareTo(latest) > 0) {
				latest = page.getUpdated();
				targetPage = page;
			}
		}
		pageDao.deletePage(targetPage.getId());

	}

	public void implementDeleteThree() {
		Website cnet = websiteDao.findWebsiteByName("CNET");
		websiteDao.deleteWebsite(cnet.getId());
	}
	
	public void disconnected() {
		developerDao.closeConnection();
		websiteDao.closeConnection();
		roleDao.closeConnection();
		pageDao.closeConnection();
		widgetDao.closeConnection();
	}
}
