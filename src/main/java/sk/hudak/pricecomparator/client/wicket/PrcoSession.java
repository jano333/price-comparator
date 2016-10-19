package sk.hudak.pricecomparator.client.wicket;

import org.apache.wicket.request.Request;

/**
 * Created by jan on 16. 10. 2016.
 */
public class PrcoSession extends CSRFSession {

    private int countPerPage = 20;

    public PrcoSession(Request request) {
        super(request);
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getCountPerPage() {
        return countPerPage;
    }
}
