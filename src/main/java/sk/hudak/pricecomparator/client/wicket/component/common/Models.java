package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class Models {


    public static <T extends Object> IModel<String> ofEqual(IModel<T> model, final Object equalizer, final String ifEqual, final String ifNotEqual) {
        return new WModel<T>(model) {
            @Override
            public String transform(T object) {
                return equalizer.equals(object) ? ifEqual : ifNotEqual;
            }
        };
    }

    public static IModel<String> ofBoolean(IModel<Boolean> model, final String ifTrue, final String ifFalse) {
        return new WModel<Boolean>(model) {
            @Override
            public String transform(Boolean object) {
                if (object == null) {
                    return ifFalse;
                }
                return object ? ifTrue : ifFalse;
            }
        };
    }

    public static <T extends Enum<T>> IModel<String> ofEnum(final IModel<T> model, final EnumTranslator translator) {
        return new WModel<T>(model) {
            @Override
            public String transform(T object) {
                if (object == null) {
                    return "";
                }
                if (translator == null) {
                    return object.getClass().getSimpleName() + "-" + object.name();
                }
                return translator.translate(object);
            }
        };
    }

    public static interface EnumTranslator {
        public String translate(Enum<?> enu);
    }


    private abstract static class WModel<Z> extends LoadableDetachableModel<String> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private IModel<Z> target;

        public WModel(IModel<Z> model) {
            this.target = model;
        }

        @Override
        protected String load() {
            return transform(target.getObject());
        }

        public abstract String transform(Z object);

        @Override
        public void detach() {
            super.detach();
            if (target != null) {
                target.detach();
            }
        }

    }

}
