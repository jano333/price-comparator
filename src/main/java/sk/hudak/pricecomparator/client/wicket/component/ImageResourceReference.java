package sk.hudak.pricecomparator.client.wicket.component;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

/**
 * Created by jan on 25. 3. 2016.
 */
public class ImageResourceReference extends ResourceReference {

    //TODO cesta...
    private static final String C_PRICE_COMPARATOR_IMAGES = "C:\\price-comparator\\images\\";

    public ImageResourceReference() {
        super(ImageResourceReference.class, "imagesDemo");
    }

    @Override
    public IResource getResource() {
        return new ImageResource();
    }

    private static class ImageResource extends DynamicImageResource {

        @Override
        protected byte[] getImageData(Attributes attributes) {
            try {
                PageParameters parameters = attributes.getParameters();
                StringValue name = parameters.get("name");
                File imageFile = new File(C_PRICE_COMPARATOR_IMAGES, name.toString());
                if (!imageFile.exists()) {
                    return null;
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Files.copy(imageFile.toPath(), baos);
                baos.flush();
                byte[] imageData = baos.toByteArray();
                baos.close();
                return imageData;

            } catch (Exception e) {
                //TODO
                e.printStackTrace();
                return null;
            }

        }

        // Needed by ResourceMapper to be able to match the request Url with
        // the mounted ResourceReference
        @Override
        public boolean equals(Object that) {
            return that instanceof ImageResource;
        }
    }
}
