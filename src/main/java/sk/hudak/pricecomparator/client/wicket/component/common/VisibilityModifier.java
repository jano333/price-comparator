package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public abstract class VisibilityModifier extends Behavior {

    private static final Logger log = LoggerFactory.getLogger(VisibilityModifier.class);

    private static final MetaDataKey<Boolean> REQUEST_CYCLE_COMPONENT_VISIBILITY = new MetaDataKey<Boolean>() {
    };

    private boolean invert = false;

    @Override
    public void onConfigure(Component component) {

        Boolean rcVisibility = component.getMetaData(REQUEST_CYCLE_COMPONENT_VISIBILITY);
        if (rcVisibility == null) {
            rcVisibility = Boolean.TRUE;
        }
        // it is not necessary test all that conditions if visibility is already marked to false
        if (rcVisibility) {
            boolean behaviorDependantVisibility = isVisible(component) ^ invert;
            rcVisibility = rcVisibility && behaviorDependantVisibility;
        }
        component.setVisibilityAllowed(rcVisibility);
        component.setMetaData(REQUEST_CYCLE_COMPONENT_VISIBILITY, rcVisibility);
    }

    @Override
    public void detach(Component component) {
        super.detach(component);
        component.setMetaData(REQUEST_CYCLE_COMPONENT_VISIBILITY, null);
    }

    protected abstract boolean isVisible(Component component);


    public VisibilityModifier setInvert(boolean invert) {
        this.invert = invert;
        return this;
    }

    public static abstract class ModelVisibilityModifier<T> extends VisibilityModifier {

        private IModel<T> model;

        public ModelVisibilityModifier(IModel<T> model) {
            this.model = model;
        }

        @Override
        protected final boolean isVisible(Component component) {
            return isVisible(model.getObject(), component);
        }

        protected abstract boolean isVisible(T object, Component component);

        @Override
        public void detach(Component component) {
            super.detach(component);
            if (model != null) {
                model.detach();
            }
        }
    }

    public static VisibilityModifier ofModel(final IModel<Boolean> model) {
        return new ModelVisibilityModifier<Boolean>(model) {
            @Override
            protected boolean isVisible(Boolean object, Component component) {
                return object;
            }
        };
    }

//	public static VisibilityModifier isTrue(final boolean condition) {
//		return new VisibilityModifier() {
//			@Override
//			protected boolean isVisible(Component component) {
//				return condition;
//			}
//		};
//	}
//
//	public static VisibilityModifier isFalse(final boolean condition) {
//		return isTrue(condition).setInvert(true);
//	}

    public static VisibilityModifier isTrue(Object modelObject, String expression) {
        return new ModelVisibilityModifier<Boolean>(new PropertyModel<Boolean>(modelObject, expression)) {
            @Override
            protected boolean isVisible(Boolean object, Component component) {
                return object;
            }
        };
    }

    public static VisibilityModifier isFalse(Object modelObject, String expression) {
        return isTrue(modelObject, expression).setInvert(true);
    }

    public static VisibilityModifier equal(Object modelObject, String expression, final Object equalizer) {
        return new ModelVisibilityModifier<Object>(new PropertyModel<Object>(modelObject, expression)) {
            @Override
            protected boolean isVisible(Object object, Component component) {
//				log.debug("equaling {} with {}", equalizer, object);
                return equalizer.equals(object);
            }
        };
    }

    public static VisibilityModifier notEqual(Object modelObject, String expression, final Object equalizer) {
        return equal(modelObject, expression, equalizer).setInvert(true);
    }

    public static VisibilityModifier notNull() {
        return new VisibilityModifier() {
            @Override
            protected boolean isVisible(Component component) {
                return component.getDefaultModelObject() != null;
            }
        };
    }

    public static VisibilityModifier zeroSize(Object modelObject, String expression) {
        return new ModelVisibilityModifier<Collection<?>>(new PropertyModel<Collection<?>>(modelObject, expression)) {
            @Override
            protected boolean isVisible(Collection<?> object, Component component) {
                return object.size() == 0;
            }
        };
    }

    public static VisibilityModifier notZeroSize(Object modelObject, String expression) {
        return zeroSize(modelObject, expression).setInvert(true);
    }

    public static <T> VisibilityModifier zeroSize(final IModel<List<T>> model) {
        return new VisibilityModifier() {
            @Override
            protected boolean isVisible(Component component) {
                return model.getObject().size() == 0;
            }
        };
    }

    public static <T> VisibilityModifier notZeroSize(final IModel<List<T>> model) {
        return zeroSize(model).setInvert(true);
    }

}



