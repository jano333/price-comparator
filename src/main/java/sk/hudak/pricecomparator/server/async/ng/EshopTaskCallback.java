package sk.hudak.pricecomparator.server.async.ng;

/**
 * Created by jan on 3. 7. 2016.
 */
public interface EshopTaskCallback<T extends EshopTaskEvent> {

    void onEvent(T event);
}
