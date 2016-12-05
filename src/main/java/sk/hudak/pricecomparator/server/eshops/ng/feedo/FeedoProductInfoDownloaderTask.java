package sk.hudak.pricecomparator.server.eshops.ng.feedo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbstractProductInfoDownloaderTask;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 4. 12. 2016.
 */
@Named
public class FeedoProductInfoDownloaderTask extends AbstractProductInfoDownloaderTask<FeedoProductParser> {

    @Inject
    private FeedoProductParser parser;

    @Override
    public EshopType getEshopType() {
        return EshopType.FEEDO;
    }

    @Override
    public int getTaskPriority() {
        return 0;
    }

    @Override
    protected FeedoProductParser getHtmlParser() {
        return parser;
    }
}
