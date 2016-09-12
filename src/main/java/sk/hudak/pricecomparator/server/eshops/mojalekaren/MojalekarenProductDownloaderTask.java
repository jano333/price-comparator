package sk.hudak.pricecomparator.server.eshops.mojalekaren;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 12. 9. 2016.
 */
public class MojalekarenProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.MOJA_LEKAREN;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new MojalekarenProductParser();
    }
}
