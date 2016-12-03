package sk.hudak.pricecomparator.server.task.tesco;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.html.parser.impl.TescoProductParser;
import sk.hudak.pricecomparator.server.task.product.info.AbstractProductInfoDownloaderTask;

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
    protected TescoProductParser getHtmlParser() {
        return parser;
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }
}
