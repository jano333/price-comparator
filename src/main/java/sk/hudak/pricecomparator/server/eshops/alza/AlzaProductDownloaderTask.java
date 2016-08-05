package sk.hudak.pricecomparator.server.eshops.alza;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 3. 8. 2016.
 */
public class AlzaProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.ALZA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new AlzaEshopProductParser();
    }
}
