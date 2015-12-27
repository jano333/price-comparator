package sk.hudak.jef.server;

import sk.hudak.pricecomparator.server.core.JefValidator;

import javax.inject.Inject;

/**
 * Created by jan on 27. 12. 2015.
 */
public abstract class JefFacade {

//    @PersistenceContext
//    protected EntityManager em;

    @Inject
    protected JefValidator val;

}
