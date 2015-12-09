package sk.hudak.pricecomparator.server.database;

import sk.hudak.pricecomparator.middle.api.service.TescoService;
import sk.hudak.pricecomparator.middle.api.to.TescoProductInfoDto;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by jan on 6. 12. 2015.
 */
public class TescoServiceImpl implements TescoService {

    public static final String PRODUCT_FILE_INPUT = "C:\\price-comparator\\tesco\\Jogurty a dezerty.txt";

    @Override
    public List<TescoProductInfoDto> getAllUnprocessedProducts() {
        try {
            List<TescoProductInfoDto> result = new ArrayList<>();

            File unprocesedFile = new File(PRODUCT_FILE_INPUT);
            for (String line : Files.readAllLines(unprocesedFile.toPath(), StandardCharsets.UTF_8)) {
                StringTokenizer st = new StringTokenizer(line, "|", false);
                TescoProductInfoDto dto = new TescoProductInfoDto();
                if (st.hasMoreElements()) {
                    dto.setNazov((String) st.nextElement());
                }
                if (st.hasMoreElements()) {
                    dto.setUrl((String) st.nextElement());
                }
                String tmp = dto.getUrl().substring(dto.getUrl().lastIndexOf("/"), dto.getUrl().length());

                dto.setImagePath("C:\\price-comparator\\tesco\\" + tmp + ".jpeg");
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();

        }

    }
}
