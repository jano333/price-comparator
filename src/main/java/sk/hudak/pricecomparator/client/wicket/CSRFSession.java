package sk.hudak.pricecomparator.client.wicket;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import java.util.UUID;

public class CSRFSession extends WebSession {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String csrfToken;

    public CSRFSession(Request request) {
        super(request);
        csrfToken = UUID.randomUUID().toString();
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }


}
