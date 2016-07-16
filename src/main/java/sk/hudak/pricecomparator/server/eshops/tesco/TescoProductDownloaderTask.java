package sk.hudak.pricecomparator.server.eshops.tesco;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Task, ktory aktualizuje vsetky produkty v eshope TESCO.
 * <p>
 * Created by jan on 3. 7. 2016.
 */
public class TescoProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }


    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new TescoProductParser();
    }
}
