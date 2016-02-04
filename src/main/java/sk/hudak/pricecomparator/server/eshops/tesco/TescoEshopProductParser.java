package sk.hudak.pricecomparator.server.eshops.tesco;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jan on 13. 10. 2015.
 */
public class TescoEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        //cena
        Elements elements = document.select("span[class=linePrice]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb = sb.deleteCharAt(sb.length() - 1);
        sb = sb.deleteCharAt(sb.length() - 1);
        final String cenaZaBalenie = sb.toString().replace(",", ".");

        //akcia
        Elements select = document.select("div[class=promo] p[class=promoUntil]");
        final boolean action = select.size() > 0;
        Date actionTo = null;
        if (action) {
            // akcia platna do:
            sb = new StringBuffer(select.text());
            sb = sb.delete(0, "Cena je platná pri dodaní do ".length());
            sb = sb.deleteCharAt(sb.length() - 1);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                actionTo = sdf.parse(sb.toString());
            } catch (ParseException e) {
                //TODO vynimka
                e.printStackTrace();
                actionTo = null;
            }
        }
        final Date finalActionTo = actionTo;

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public ProductAction getAction() {
                return action ? ProductAction.IN_ACTION : ProductAction.NON_ACTION;
            }

            @Override
            public Date getActionValidTo() {
                return finalActionTo;
            }

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }


        };
    }


}
