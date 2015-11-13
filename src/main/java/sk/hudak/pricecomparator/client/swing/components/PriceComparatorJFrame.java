package sk.hudak.pricecomparator.client.swing.components;

import sk.hudak.pricecomparator.client.swing.panel.page.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by jan on 25. 10. 2015.
 */
public class PriceComparatorJFrame extends JFrame {

    private final MyContainer cardContainer;

    private EshopCreatePage eshopCreatePage = new EshopCreatePage();
    private EshopListPage eshopListPage = new EshopListPage();

    private ProductCreatePage productCreatePage = new ProductCreatePage();
    private ProductListPage productListPage = new ProductListPage();
    private ProductInEshopCreatePage productInEshopCreatePage = new ProductInEshopCreatePage();
    private EshopsPerProductListPage eshopsPerProductListPage = new EshopsPerProductListPage();

    private ProductInEshopListPage productInEshopListPage = new ProductInEshopListPage();

    private GroupOfProductsCreatePage groupOfProductsCreatePage = new GroupOfProductsCreatePage();
    private GroupOfProductAddProductPage groupOfProductAddProductPage = new GroupOfProductAddProductPage();
        private GroupOfProductListPage groupOfProductListPage = new GroupOfProductListPage();
//    private GroupOfProductListPage2 groupOfProductListPage = new GroupOfProductListPage2();


    public PriceComparatorJFrame() throws HeadlessException {
        super("Price Comparator ver.: 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        createMenu();

        cardContainer = new MyContainer();

        cardContainer.add(eshopListPage.getClass().getSimpleName(), eshopListPage);
        cardContainer.add(eshopCreatePage.getClass().getSimpleName(), eshopCreatePage);

        cardContainer.add(productListPage.getClass().getSimpleName(), productListPage);
        cardContainer.add(eshopsPerProductListPage.getClass().getSimpleName(), eshopsPerProductListPage);
        cardContainer.add(productCreatePage.getClass().getSimpleName(), productCreatePage);
        cardContainer.add(productInEshopCreatePage.getClass().getSimpleName(), productInEshopCreatePage);

//        cardContainer.add(productInEshopListPage.getClass().getSimpleName(), productInEshopListPage);

        cardContainer.add(groupOfProductListPage.getClass().getSimpleName(), groupOfProductListPage);
        cardContainer.add(groupOfProductsCreatePage.getClass().getSimpleName(), groupOfProductsCreatePage);
        cardContainer.add(groupOfProductAddProductPage.getClass().getSimpleName(), groupOfProductAddProductPage);

        getContentPane().add(cardContainer);


        //Display the window.
//        frame.pack();
        setVisible(true);
    }

    private static class MyContainer extends JPanel {
        public MyContainer() {
            super(new CardLayout());
        }


    }

    private void createMenu() {

        //Create the menu bar.
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnSpravaEshop = new JMenu("Eshopy");
        mnSpravaEshop.setMnemonic(KeyEvent.VK_E);
        menuBar.add(mnSpravaEshop);

        JMenu mnSpravaProductov = new JMenu("Produkty");
        mnSpravaProductov.setMnemonic(KeyEvent.VK_P);
        menuBar.add(mnSpravaProductov);

        JMenu mnSpravaGroupOfProducts = new JMenu("Skupina produktov");
        mnSpravaGroupOfProducts.setMnemonic(KeyEvent.VK_S);
        menuBar.add(mnSpravaGroupOfProducts);


        // Eshop Menu
        JMenuItem mnzoznamEshop = new JMenuItem("Zoznam eshopov");
        mnSpravaEshop.add(mnzoznamEshop);
        mnzoznamEshop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eshopListPage.reloadData();
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, eshopListPage.getClass().getSimpleName());
            }
        });

        JMenuItem mnCreateEshop = new JMenuItem("Pridanie noveho eshopu");
        mnSpravaEshop.add(mnCreateEshop);
        mnCreateEshop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, eshopCreatePage.getClass().getSimpleName());
            }
        });


        // Produkt Menu
        JMenuItem mnzoznamProdukt = new JMenuItem("Zoznam produktov");
        mnSpravaProductov.add(mnzoznamProdukt);
        mnzoznamProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productListPage.reloadData();
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, productListPage.getClass().getSimpleName());
            }
        });

        JMenuItem mnEshopsPreProduct = new JMenuItem("Zoznam eshopov per produkt");
        mnSpravaProductov.add(mnEshopsPreProduct);
        mnEshopsPreProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, eshopsPerProductListPage.getClass().getSimpleName());
            }
        });
        JMenuItem mnCreateProdukt = new JMenuItem("Pridanie noveho produktu");
        mnSpravaProductov.add(mnCreateProdukt);
        mnCreateProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, productCreatePage.getClass().getSimpleName());
            }
        });

        JMenuItem mnCreateProduktPerEhop = new JMenuItem("Pridanie produktu do eshopu");
        mnSpravaProductov.add(mnCreateProduktPerEhop);
        mnCreateProduktPerEhop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productInEshopCreatePage.reloadData();
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, productInEshopCreatePage.getClass().getSimpleName());
            }
        });


        // skupina produktov
        JMenuItem mnListGroupOfProdukt = new JMenuItem("Zoznam skupiny");
        mnSpravaGroupOfProducts.add(mnListGroupOfProdukt);
        mnListGroupOfProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO odkomentovat
                groupOfProductListPage.reloadData();
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, groupOfProductListPage.getClass().getSimpleName());
            }
        });

        JMenuItem mnCreateGroupOfProdukt = new JMenuItem("Vytvorenie skupiny");
        mnSpravaGroupOfProducts.add(mnCreateGroupOfProdukt);
        mnCreateGroupOfProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, groupOfProductsCreatePage.getClass().getSimpleName());
            }
        });

        JMenuItem mnAddProductToGroupOfProdukt = new JMenuItem("Pridaj produkt do skupiny");
        mnSpravaGroupOfProducts.add(mnAddProductToGroupOfProdukt);
        mnAddProductToGroupOfProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, groupOfProductAddProductPage.getClass().getSimpleName());
            }
        });


    }
}