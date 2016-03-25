package sk.hudak.pricecomparator.client.wicket;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.resource.loader.ClassStringResourceLoader;
import org.apache.wicket.resource.loader.IStringResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.client.wicket.component.ImageResourceReference;
import sk.hudak.pricecomparator.client.wicket.locale.ErrorScope;
import sk.hudak.pricecomparator.client.wicket.locale.PageLocalize;
import sk.hudak.pricecomparator.client.wicket.page.group.GroupProductPriceListPage;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

import java.util.List;

/**
 * Created by jan on 8. 3. 2016.
 */
public class PriceComparatorApplication extends AbstractPriComApplication {

    private static final Logger log = LoggerFactory.getLogger(PriceComparatorApplication.class);

    @Override
    public Class<? extends Page> getHomePage() {
//        return ProductListPerEshopPage.class;
//        return ProductPricesPerEshopsPage.class;
        return GroupProductPriceListPage.class;
    }

    @Override
    protected void configureResourceLoader() {
//        getResourceSettings().addStringResourceLoader(new ClassStringResourceLoader(PageLocalize.class));
//        getResourceSettings().addStringResourceLoader(new ClassStringResourceLoader(ValidatorScope.class));
//        getResourceSettings().addStringResourceLoader(new ClassStringResourceLoader(ErrorScope.class));

        //TODO zatila nefunguje, lebo nenajde v resoursoch
        List<IStringResourceLoader> resourceLoaders = getResourceSettings().getStringResourceLoaders();
        //TODO
        resourceLoaders.add(new ClassStringResourceLoader(PageLocalize.class));
        resourceLoaders.add(new ClassStringResourceLoader(ErrorScope.class));

    }

    @Override
    protected void init() {
        super.init();

        mountResource("/images/${name}", new ImageResourceReference());

//        mountBookmarkablePage("/logout", LogoutPage.class);
//        mountBookmarkablePage("/manage/login", LoginPage.class);
//
//        mount(new KeepAliveRequestTargetUrlCodingStrategy("/keepalive"));
//
//        getApplicationSettings().setPageExpiredErrorPage(WarnPage.class);
//        getApplicationSettings().setInternalErrorPage(InternalErroPage.class);
//
//        addComponentInitializationListener(new IComponentInitializationListener() {
//
//            @Override
//            public void onInitialize(Component component) {
//                if (component instanceof AbstractTextComponent) {
//                    log.debug("Maximum validator added on {}", component);
//                    ((AbstractTextComponent) component).add(new GlobalFieldMaximumValidator());
//                }
//            }
//        });

//        addPreComponentOnBeforeRenderListener(new AuthorizationRenderListener(LoginPage.class, AccountDetailPage.class));

        //TODO captcha
//        ReCaptchaFactory.setRecaptcha(new ReCaptcha(
//        ));
    }


    public static PriceComparatorApplication get() {
        return (PriceComparatorApplication) Application.get();
    }

    private PriceComparatorService service;

    public static PriceComparatorService getApi() {
        return get().getService();
    }

    public PriceComparatorService getService() {
        return service;
    }

    public void setService(PriceComparatorService service) {
        this.service = service;
    }
}
