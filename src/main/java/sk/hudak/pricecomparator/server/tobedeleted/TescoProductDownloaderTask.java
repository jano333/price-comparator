package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Task, ktory aktualizuje vsetky produkty v eshope TESCO.
 * <p>
 * Created by jan on 3. 7. 2016.
 */
@Deprecated
public class TescoProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }


    @Override
    protected EshopProductParser createEshopProductParser() {
        return new TescoProductParser();
    }
}
