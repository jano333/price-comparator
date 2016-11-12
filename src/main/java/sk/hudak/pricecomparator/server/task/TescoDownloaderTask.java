package sk.hudak.pricecomparator.server.task;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.html.parser.impl.TescoProductParser;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 6. 11. 2016.
 */
@Named
public class TescoDownloaderTask extends AbstractDownloaderTask<TescoProductParser> {

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
