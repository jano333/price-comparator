package sk.hudak.pricecomparator.server.task.tesco;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.product.picture.AbstractPictureDownloaderTask;

import javax.inject.Named;

/**
 * Created by jan on 19. 11. 2016.
 */
@Named
public class TescoPictureDownloaderTask extends AbstractPictureDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }

    @Override
    public void taskJob() {
        // TODO v DB musi byt zaznam ci exituje obrazok k produktu alebo nie !!!
        //1. vytiahnut
        // toto


    }
}
