package sk.hudak.pricecomparator.server.eshops.ng.tesco;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbstractProductInfoDownloaderTask;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 6. 11. 2016.
 */
@Named
public class TescoProductInfoDownloaderTask extends AbstractProductInfoDownloaderTask<TescoProductParser> {

    @Inject
    private TescoProductParser parser;

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }

    @Override
    public int getTaskPriority() {
        return 0;
    }

    @Override
    protected TescoProductParser getHtmlParser() {
        return parser;
    }
}
