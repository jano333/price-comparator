package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import java.io.Serializable;
import java.util.List;

public abstract class IdListView<T> extends ListView<T> {

    public IdListView(final String id, final IModel<? extends List<T>> model) {
        super(id, model);
    }

    @Override
    protected final void populateItem(ListItem<T> item) {
        populateItem((IdListItem<T>) item);

    }

    protected abstract void populateItem(IdListItem<T> item);

    @Override
    protected IModel<T> getListItemModel(IModel<? extends List<T>> listViewModel, int index) {
        return new IdModel<T>(listViewModel, index) {
            @Override
            protected T loadLazyById(Serializable id) {
                return IdListView.this.loadLazyById(id);
            }

            @Override
            protected Serializable getObjectId(T object) {
                return IdListView.this.getObjectId(object);
            }

            ;
        };
    }

    @Override
    protected ListItem<T> newItem(int index, IModel<T> itemModel) {
        List<T> litViewObject = getModel().getObject();
        T object = litViewObject.get(index);
        return new IdListItem<T>(index, getObjectId(object), getListItemModel(getModel(), index));
    }


    protected T loadLazyById(Serializable id) {
        return null;
    }

    protected Serializable getObjectId(T object) {
        return null;
    }


    public static class IdListItem<T> extends ListItem<T> {

        private Serializable objectId;

        public IdListItem(int index, Serializable objectId, IModel<T> model) {
            super(index, model);
            this.objectId = objectId;
        }

        public Serializable getObjectId() {
            return objectId;
        }
    }


    private abstract static class IdModel<T> implements IModel<T> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private T object;
        private Serializable objectId = null;
        private IModel<? extends List<T>> listViewModel;
        private int index;

        public IdModel(IModel<? extends List<T>> listViewModel, int index) {
            this.listViewModel = listViewModel;
            this.index = index;
            setObject(listViewModel.getObject().get(index));
        }

        @Override
        public void detach() {
            object = null;
        }

        @Override
        public T getObject() {
            if (object == null) {
                if (objectId == null) {
                    System.out.println("Object is detached and Id is NOT filled");
                    setObject(listViewModel.getObject().get(index));
                } else {
                    System.out.println("Object is detached, Id is filled, so loading lazy ...");
                    setObject(loadLazyById(objectId));
                }
            }
            return object;
        }

        @Override
        public void setObject(T data) {
            this.object = data;
            this.objectId = getObjectId(data);
        }

        protected abstract T loadLazyById(Serializable id);

        protected abstract Serializable getObjectId(T object);
    }


}
