package sk.hudak.pricecomparator.server.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jan on 10. 12. 2015.
 */
public class TescoPictureConverter {

    private static final int IMG_WIDTH = 80;
    private static final int IMG_HEIGHT = 80;
    public static final String START_FORLDER = "C:\\price-comparator\\tesco\\pictures\\";
    public static final String TARGET_FORLDER = "C:\\price-comparator\\tesco\\converted\\";

    public static void main(String[] args) {

        File[] sorceFiles = new File(START_FORLDER).listFiles();
        for (int i = 0; i < sorceFiles.length; i++) {
            System.out.println(">> i " + (i + 1));
            File sourceFile = sorceFiles[i];
            String pictureName = sourceFile.getName();
            if (new File(START_FORLDER + pictureName).length() == 0) {
                continue;
            }
            if (new File(TARGET_FORLDER + pictureName).exists()) {
                continue;
            }
            convertImage(START_FORLDER + pictureName, TARGET_FORLDER + pictureName);
        }

        System.out.println("Done");
    }


    public static void convertImage(String inputPath, String outputPath) {
        try {
            BufferedImage resizedPicture = resizeImageWithHint(readImage(inputPath));
            ImageIO.write(resizedPicture, "jpeg", new File(outputPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage readImage(String imagePath) throws IOException {
        return ImageIO.read(new File(imagePath));
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage) {

        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }
}
