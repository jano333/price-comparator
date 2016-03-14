package sk.hudak.pricecomparator.client.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.javascript.DefaultJavaScriptCompressor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import java.util.Locale;

//import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
//import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
//import org.apache.wicket.request.IRequestCodingStrategy;
//import org.apache.wicket.request.IRequestCycleProcessor;
//import org.apache.wicket.util.crypt.KeyInSessionSunJceCryptFactory;

//import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;

public abstract class AbstractPriComApplication extends WebApplication {

    //TODO cele prest co vsetko chyba voci W a dopnit... resp zistit ci je potrebne
    // nieco som vymazal uplne...

    @Override
    protected void init() {
        super.init();
        configureCommonApplicationSettings();

        //TODO
//        addComponentInstantiationListener(new IComponentInstantiationListener() {
//
//            @Override
//            public void onInstantiation(Component component) {
//                if (component instanceof FormComponent<?>) {
//                    component.add(new MarkErrorFieldBehavior());
//                }
//            }
//        });

    }


    private void configureCommonApplicationSettings() {
        // MARKUP
        // Strip wicket tags
//        getMarkupSettings().setStripWicketTags(true);
        // Strip comments
//        getMarkupSettings().setStripComments(true);
        // Compress white spaces
        getMarkupSettings().setCompressWhitespace(true);
        // Strip xml declaration
        //TODO
//        getMarkupSettings().setStripXmlDeclarationFromOutput(true);
        // Default encodinf for *.html files
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        // Request-Response encoding
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

        // RESOURCES
        // Javascript minifying
        getResourceSettings().setJavaScriptCompressor(new DefaultJavaScriptCompressor());
        // GZIP resources
        //TODO
//        getResourceSettings().setDisableGZipCompression(false);

        getResourceSettings().setThrowExceptionOnMissingResource(false);

        //TODO
//        getPageSettings().setAutomaticMultiWindowSupport(false);

//        getDebugSettings().setAjaxDebugModeEnabled(false);

        configureResourceLoader();

        //TODO
//        getSecuritySettings().setCryptFactory(new KeyInSessionSunJceCryptFactory());

    }

    protected abstract void configureResourceLoader();


    //TODO
//    @Override
//    protected IRequestCycleProcessor newRequestCycleProcessor() {
//        return new WebRequestCycleProcessor() {
//            @Override
//            protected IRequestCodingStrategy newRequestCodingStrategy() {
//                return new CryptoMapper(new WebRequestCodingStrategy());
//            }
//        };
//    }


    @Override
    public Session newSession(Request request, Response response) {
        WebSession session = new CSRFSession(request);
//        session.setLocale(new Locale("SK"));
        session.setLocale(new Locale("sk"));
        return session;
    }

    //TODO
//    @Override
//    protected IConverterLocator newConverterLocator() {
//        ConverterLocator converterLocator = new ConverterLocator();
//        converterLocator.set(BigDecimal.class, new BigDecimalConverter());
//        return converterLocator;
//    }

    //TODO
//    @Override
//    public RuntimeConfigurationType getConfigurationType() {
//        return ...Config.webApp().isDevelopment() ?
//                RuntimeConfigurationType.DEVELOPMENT :
//                RuntimeConfigurationType.DEPLOYMENT;
//
//    }


}
