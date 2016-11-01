package sk.hudak.pricecomparator.server.eshops.novalekaren;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 18. 7. 2016.
 */
public class NovaLekarenProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.NOVA_LEKAREN;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new NovaLekarenProductParser();
    }
}
