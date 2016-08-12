package sk.hudak.pricecomparator.server.eshops.novalekaren;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 18. 7. 2016.
 */
public class NovaLekarenProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.NOVA_LEKAREN;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new NovaLekarenProductParser();
    }
}
