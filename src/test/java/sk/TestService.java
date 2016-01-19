package sk;

import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;
import sk.hudak.pricecomparator.server.parser.FeedoEshopProductParser;
import sk.hudak.pricecomparator.server.parser.HejEshopProductParser;
import sk.hudak.pricecomparator.server.parser.MetroEshopProductParser;
import sk.hudak.pricecomparator.server.parser.TescoEshopProductParser;
import sk.hudak.pricecomparator.server.xml.service.PriceComparatorXmlService;

/**
 * Created by jan on 15. 10. 2015.
 */
public class TestService {

    private PriceComparatorService service = new PriceComparatorXmlService();

    public static void main(String[] args) {
        new TestService().init();
    }

    private void init() {
        createEshops();

    }


    private void createEshops() {
        createEshop("Metro", MetroEshopProductParser.class.getName(), "www.metro.sk");
        createEshop("Tesco", TescoEshopProductParser.class.getName(), "http://potravinydomov.itesco.sk/");
        createEshop("Feedo", FeedoEshopProductParser.class.getName(), "www.feedo.sk");
        createEshop("Hej", HejEshopProductParser.class.getName(), "www.hej.sk");
    }


    private void createEshop(String name, String parserClassName, String homePage) {
        EshopCreateDto dto = new EshopCreateDto();
        dto.setName(name);
        dto.setParserClassName(parserClassName);
        dto.setHomePage(homePage);
        service.createEshop(dto);
    }


}
