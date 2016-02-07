package sk.hudak.pricecomparator.server.eshops.tesco;

import sk.hudak.pricecomparator.middle.to.AutamatResult;
import sk.hudak.pricecomparator.middle.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopCreateDto;

import javax.inject.Named;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by jan on 6. 2. 2016.
 */
@Named
public class TescoCreateProductManager {

    public static final String CARE_PATH = "C:\\price-comparator\\tesco\\care.txt";

//    public static void main(String[] args) {
//        new TescoCreateProductManager().removeFirst();
//    }


    /**
     * Odstrani prvy riadok zo suboru care.txt
     */
    public void removeFirst() {
        try {
            Path path = new File(CARE_PATH).toPath();
            List<String> sourceLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            System.out.println(">> " + sourceLines.size());
            sourceLines.remove(0);
            System.out.println(">> 2 " + sourceLines.size());

            // musim ho odmazat, lebo posledny riadok zle zapise.
            Files.deleteIfExists(path);


            BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            for (String sourceLine : sourceLines) {
                System.out.println(sourceLine);
                bw.write(sourceLine);
                bw.newLine();
            }
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public AutamatResult getNextUnprecessedProduct() {
        try {
            List<String> sourceLines = Files.readAllLines(new File(CARE_PATH).toPath(), StandardCharsets.UTF_8);
            String line = sourceLines.get(0);
            return prepare(line);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * pipe oddeleny nazov productu a url
     *
     * @param lineFromFile
     * @return
     */
    private AutamatResult prepare(String lineFromFile) {
        ProductCreateDto productCreateDto = new ProductCreateDto();
        ProductInEshopCreateDto productInEshopCreateDto = new ProductInEshopCreateDto();

        //TODO
        StringTokenizer st = new StringTokenizer(lineFromFile, "|", false);
        if (st.hasMoreTokens()) {
            String name = st.nextToken();
            productCreateDto.setName(name);
        }
        if (st.hasMoreTokens()) {
            String url = st.nextToken();
            productInEshopCreateDto.setEshopProductPage(url);
        }

        precessUnitAndCountOfUnit(productCreateDto);

        return new AutamatResult(productCreateDto, productInEshopCreateDto);
    }

    private void precessUnitAndCountOfUnit(ProductCreateDto productCreateDto) {
        String name = productCreateDto.getName();


    }

    private static Set<String> VAHA_UNITS = new HashSet<>();

    static {
        VAHA_UNITS.add("90 g");
        VAHA_UNITS.add("125 g");
        VAHA_UNITS.add("190 g");
        VAHA_UNITS.add("250 g");
    }

}
