package sk.hudak.pricecomparator.server.async.ng;

/**
 * Created by jan on 3. 7. 2016.
 */
public interface EshopTaskCallbackNg<T extends EshopTaskEventNg> {

    void onEvent(T event);
}
