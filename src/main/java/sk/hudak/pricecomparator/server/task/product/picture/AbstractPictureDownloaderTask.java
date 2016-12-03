package sk.hudak.pricecomparator.server.task.product.picture;

import sk.hudak.pricecomparator.server.task.AbtractEshopTask;

import javax.inject.Inject;

/**
 * Created by jan on 19. 11. 2016.
 */
public abstract class AbstractPictureDownloaderTask extends AbtractEshopTask {

    @Inject
    private PictureDownloaderBean downloader;


}
