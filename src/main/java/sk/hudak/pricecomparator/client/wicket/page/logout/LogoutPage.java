package sk.hudak.pricecomparator.client.wicket.page.logout;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

//@AccessPolicy(Access.FREE)
public class LogoutPage extends WebPage {

    public LogoutPage(PageParameters pageParameters) {
        super(pageParameters);

//		setRedirect(true);
        Session.get().invalidateNow();

        //TODO ??
//		throw new RedirectException(Application.get().getHomePage());
    }

}
