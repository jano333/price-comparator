package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.product.*;

import java.util.List;

/**
 * Zakladne operacie(CRUD) nad entitou Product.
 * <p>
 * Created by jan on 14. 10. 2015.
 */
@Deprecated
public interface ProductService {

    /**
     * Vytvorenie noveho produktu.
     *
     * @param createDto data pre vytvorenie noveho produktu
     * @return db id novovytvoreneho produtku
     * @throws PriceComparatorBusinesException bussines chyba v pripade zlyhania
     */
    @Deprecated
    Long createProduct(ProductCreateDto createDto) throws PriceComparatorBusinesException;

    /**
     * Update existujuceho produktu.
     *
     * @param updateDto data pre update existujuceho produktu
     * @throws PriceComparatorBusinesException bussines chyba v pripade zlyhania
     */
    @Deprecated
    void updateProduct(ProductUpdateDto updateDto) throws PriceComparatorBusinesException;

    /**
     * Nacitanie detailov produktu.
     *
     * @param productId db id produktu.
     * @return detaily produktu
     */
    @Deprecated
    ProductDto getProduct(Long productId) throws PriceComparatorBusinesException;

    //TODO impl delete

    /**
     * @param filter
     * @return
     */
    @Deprecated
    PageList<ProductListDto> findProducts(ProductFindDto filter);

    /**
     * for lazy loading in table...
     *
     * @param productId
     * @return
     */
    @Deprecated
    ProductListDto getProductListById(Long productId);

    /**
     * @param productId
     * @return
     */
    @Deprecated
    ProductIdNameDto getProductIdNameById(Long productId);

    /**
     * @return
     */
    @Deprecated
    List<ProductIdNameDto> findAllProductForSelection();

}
