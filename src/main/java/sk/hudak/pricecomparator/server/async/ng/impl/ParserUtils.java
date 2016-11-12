package sk.hudak.pricecomparator.server.async.ng.impl;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

/**
 * Pouzivaj {@link sk.hudak.pricecomparator.server.html.parser.JsoupParserUtils}
 * <p>
 * Created by jan on 9. 7. 2016.
 */
@Deprecated
public class ParserUtils {

    public static boolean notExistElement(Document document, String cssQuery) {
        return document.select(cssQuery).isEmpty();
    }

    public static boolean existElement(Document document, String cssQuery) {
        return !notExistElement(document, cssQuery);
    }

    /**
     * Odstrani <code>count</code> znakov z konca retazca <code>str</code>.
     *
     * @param count
     */
    public static String removeLastCharacters(String str, int count) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        if (count < 1) {
            return str;
        }
        StringBuffer sb = new StringBuffer(str);
        for (int i = 0; i < count; i++) {
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String replaceAllCommaForDot(String text) {
        text = text.replace(",", ".");
        return text;
    }
}
