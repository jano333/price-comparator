package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.server.async.ng.EshopTaskCallback;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoTaskEvent;

/**
 * Created by jan on 3. 7. 2016.
 */
@Deprecated
public class TescoProductPriceUpdaterCallback implements EshopTaskCallback<TescoTaskEvent> {

    @Override
    public void onEvent(TescoTaskEvent event) {
        //TODO
    }
}
