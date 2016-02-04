package sk;

import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;
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
        createEshop("Metro", "www.metro.sk");
        createEshop("Tesco", "http://potravinydomov.itesco.sk/");
        createEshop("Feedo", "www.feedo.sk");
        createEshop("Hej", "www.hej.sk");
    }


    private void createEshop(String name, String homePage) {
        EshopCreateDto dto = new EshopCreateDto();
        dto.setName(name);
        dto.setHomePage(homePage);
        service.createEshop(dto);
    }


}
