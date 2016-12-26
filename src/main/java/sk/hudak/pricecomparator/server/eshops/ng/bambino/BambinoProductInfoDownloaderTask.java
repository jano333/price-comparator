package sk.hudak.pricecomparator.server.eshops.ng.bambino;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbstractProductInfoDownloaderTask;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 26. 12. 2016.
 */
@Named
public class BambinoProductInfoDownloaderTask extends AbstractProductInfoDownloaderTask<BambinoProductParser> {

    @Inject
    private BambinoProductParser parser;

    @Override
    public EshopType getEshopType() {
        return EshopType.BAMBINO;
    }

    @Override
    public int getTaskPriority() {
        return 1;
    }

    @Override
    protected BambinoProductParser getHtmlParser() {
        return parser;
    }
}
