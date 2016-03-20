package sk.hudak.pricecomparator.client.wicket.page.common;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;

/**
 * Created by jan on 12. 3. 2016.
 */
public class BasicPage extends WebPage {

    public BasicPage() {
        add(new Label("pagetitle", getTitleModel()));
    }

    @Override
    protected void setHeaders(WebResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-store, no-cache, max-age=0, must-revalidate");
    }

    protected IModel<String> getTitleModel() {
        return new Model<>(getTitle());
    }

    protected String getTitle() {
        return "TODO";
    }

}
