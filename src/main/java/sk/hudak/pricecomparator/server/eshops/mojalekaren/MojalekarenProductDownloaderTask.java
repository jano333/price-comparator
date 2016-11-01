package sk.hudak.pricecomparator.server.eshops.mojalekaren;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 12. 9. 2016.
 */
public class MojalekarenProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.MOJA_LEKAREN;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new MojalekarenProductParser();
    }
}
