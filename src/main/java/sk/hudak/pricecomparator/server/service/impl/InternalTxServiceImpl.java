package sk.hudak.pricecomparator.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.service.InternalTxService;
import sk.hudak.pricecomparator.server.service.NewProductService;
import sk.hudak.pricecomparator.server.to.NewProductCreateDto;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jan on 29. 12. 2016.
 */
@Named
public class InternalTxServiceImpl implements InternalTxService {

    private static Logger logger = LoggerFactory.getLogger(InternalTxServiceImpl.class);

    @Inject
    private NewProductService newProductService;

    @Override
    @Transactional(readOnly = true)
    public List<String> getListOfSearchQueries() {
        //TODO nacitat z DB
        return Arrays.asList(/*"Pampers",*/ "Nutrilon");
    }

    @Override
    @Transactional
    public void addNewProducts(List<NewProductCreateDto> result, EshopType eshopType) {
        logger.debug(">> addNewProducts");
        newProductService.addNewProducts(result, eshopType);
        logger.debug("<< addNewProducts");
    }
}
