package sk.hudak.pricecomparator.server.eshops.ng.alza;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbstractProductInfoDownloaderTask;

import javax.inject.Inject;

/**
 * Created by jan on 2. 2. 2017.
 */
//@Named
public class AlzaProductInfoDownloaderTask extends AbstractProductInfoDownloaderTask<AlzaProductParser> {

    @Inject
    private AlzaProductParser parser;

    @Override
    public EshopType getEshopType() {
        return EshopType.ALZA;
    }

    @Override
    public int getTaskPriority() {
        return 0;
    }

    @Override
    protected AlzaProductParser getHtmlParser() {
        return parser;
    }
}
