package sk.hudak.pricecomparator.server.service;

import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jan on 29. 12. 2016.
 */
@Named
public class InternalTxServiceImpl implements InternalTxService {

    @Override
    @Transactional(readOnly = true)
    public List<String> getListOfSearchQueries() {
        //TODO nacitat z DB
        return Arrays.asList("Nutrilon", "Pampers");
    }
}
