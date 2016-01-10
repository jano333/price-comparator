package sk.hudak.jef.server;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import sk.hudak.jef.middle.paging.PageData;
import sk.hudak.jef.middle.paging.Paging;
import sk.hudak.pricecomparator.middle.api.to.FindDto;
import sk.hudak.pricecomparator.server.core.JefValidator;
import sk.hudak.pricecomparator.server.core.LongIdEntity;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by jan on 24. 3. 2015.
 */
public abstract class JefDao<T extends LongIdEntity> {

    @Inject
    protected SessionFactory sessionFactory;

    @Inject
    protected JefValidator val;

    public Long create(T entity) {
        val.notNull(entity, "entity is null.");

        return (Long) getCurrentSession().save(entity);
    }

    public void update(T entity) {
        val.notNull(entity, "entity is null.");

        getCurrentSession().update(entity);
    }

    public Long createOrUpdate(T entity) {
        val.notNull(entity, "entity is null.");

        getCurrentSession().saveOrUpdate(entity);
        return entity.getId();
    }

    protected void delete(T entity) {
        val.notNull(entity, "entity is null.");

        getCurrentSession().delete(entity);
    }

    protected T read(Long id, Class<T> entityClass) {
        val.notNull(id, "id is null.");
        val.notNull(entityClass, "entity class is null.");

        return (T) getCurrentSession().get(entityClass, id);
    }

    protected abstract T readMandatory(Long id);

    protected T readMandatory(Long id, Class<T> entityClass) {
        T entity = read(id, entityClass);
        val.notNull(entity, "entity with id " + id + " not found");
        return entity;
    }

    protected PageData<T> createPageData(Criteria criteria, FindDto findDto) {
        val.notNull(criteria, "criteria is null.");
        val.notNull(findDto, "findDto is null.");
        val.notNull(findDto.getPaging(), "paging in find dto is null.");

        Paging paging = findDto.getPaging();
        criteria.setMaxResults(paging.getPageSize());
        criteria.setFirstResult(paging.getOffset());
        List<T> result = criteria.list();

        return new PageData<>(result.size(), paging, result);
    }

    protected Criteria addAscOrder(Criteria criteria, String property) {
        return criteria.addOrder(Order.asc(property));
    }

    protected Criteria addDescOrder(Criteria criteria, String property) {
        return criteria.addOrder(Order.desc(property));
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    protected Criteria createCriteria(Class<?> enityClass) {
        return getCurrentSession().createCriteria(enityClass);
    }

}
