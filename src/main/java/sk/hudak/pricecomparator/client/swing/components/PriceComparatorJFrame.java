package sk.hudak.pricecomparator.client.swing.components;

import sk.hudak.pricecomparator.client.swing.panel.page.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by jan on 25. 10. 2015.
 */
public class PriceComparatorJFrame extends JFrame {

    private TaskManager taskManager;

    private final MyContainer cardContainer;

    private EshopListPage eshopListPage = new EshopListPage();
    private EshopCreatePage eshopCreatePage = new EshopCreatePage();

    private ProductListPage productListPage = new ProductListPage();
    private EshopsPerProductListPage eshopsPerProductListPage = new EshopsPerProductListPage();
    private ProductsInEshopListPage productsInEshopListPage = new ProductsInEshopListPage();
    private ProductCreatePage productCreatePage = new ProductCreatePage();
    private ProductInEshopCreatePage productInEshopCreatePage = new ProductInEshopCreatePage();

    private ProductInEshopListPage productInEshopListPage = new ProductInEshopListPage();

    private GroupOfProductListPage groupOfProductListPage = new GroupOfProductListPage();
    private GroupOfProductsCreatePage groupOfProductsCreatePage = new GroupOfProductsCreatePage();
    private GroupOfProductAddProductPage groupOfProductAddProductPage = new GroupOfProductAddProductPage();
//    private GroupOfProductListPage2 groupOfProductListPage = new GroupOfProductListPage2();


    public PriceComparatorJFrame(TaskManager taskManager) throws HeadlessException {
        super("Price Comparator ver.: 1.0");

        this.taskManager = taskManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("TOTO");
                PriceComparatorJFrame.this.taskManager.stopDownloading();
            }
        });

        setSize(800, 600);

        createMenu();

        cardContainer = new MyContainer();

        cardContainer.add(eshopListPage.getClass().getSimpleName(), eshopListPage);
        cardContainer.add(eshopCreatePage.getClass().getSimpleName(), eshopCreatePage);

        cardContainer.add(productListPage.getClass().getSimpleName(), productListPage);
        cardContainer.add(eshopsPerProductListPage.getClass().getSimpleName(), eshopsPerProductListPage);
        cardContainer.add(productsInEshopListPage.getClass().getSimpleName(), productsInEshopListPage);
        cardContainer.add(productCreatePage.getClass().getSimpleName(), productCreatePage);
        cardContainer.add(productInEshopCreatePage.getClass().getSimpleName(), productInEshopCreatePage);

//        cardContainer.add(productInEshopListPage.getClass().getSimpleName(), productInEshopListPage);

        cardContainer.add(groupOfProductListPage.getClass().getSimpleName(), groupOfProductListPage);
        cardContainer.add(groupOfProductsCreatePage.getClass().getSimpleName(), groupOfProductsCreatePage);
        cardContainer.add(groupOfProductAddProductPage.getClass().getSimpleName(), groupOfProductAddProductPage);

        getContentPane().add(cardContainer);

        //zabezpocim zobprazenie stranky eshopsPerProductListPage:
        CardLayout cl = (CardLayout) (cardContainer.getLayout());
        cl.show(cardContainer, eshopsPerProductListPage.getClass().getSimpleName());
        eshopsPerProductListPage.init();

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
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, eshopListPage.getClass().getSimpleName());
                eshopListPage.init();
            }
        });

        JMenuItem mnCreateEshop = new JMenuItem("Pridanie noveho eshopu");
        mnSpravaEshop.add(mnCreateEshop);
        mnCreateEshop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, eshopCreatePage.getClass().getSimpleName());
                eshopCreatePage.init();
            }
        });


        // Produkt Menu
        JMenuItem mnzoznamProdukt = new JMenuItem("Zoznam produktov");
        mnSpravaProductov.add(mnzoznamProdukt);
        mnzoznamProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, productListPage.getClass().getSimpleName());
                productListPage.init();
            }
        });

        JMenuItem mnEshopsPreProduct = new JMenuItem("Zoznam eshopov per produkt");
        mnSpravaProductov.add(mnEshopsPreProduct);
        mnEshopsPreProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, eshopsPerProductListPage.getClass().getSimpleName());
                eshopsPerProductListPage.init();
            }
        });

//        productsInEshopListPage
        JMenuItem mnProductsPerEshopListPage = new JMenuItem("Zoznam produktov v eshope");
        mnSpravaProductov.add(mnProductsPerEshopListPage);
        mnProductsPerEshopListPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, productsInEshopListPage.getClass().getSimpleName());
                productsInEshopListPage.init();
            }
        });

        JMenuItem mnCreateProdukt = new JMenuItem("Pridanie noveho produktu");
        mnSpravaProductov.add(mnCreateProdukt);
        mnCreateProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, productCreatePage.getClass().getSimpleName());
                productCreatePage.init();
            }
        });

        JMenuItem mnCreateProduktPerEhop = new JMenuItem("Pridanie produktu do eshopu");
        mnSpravaProductov.add(mnCreateProduktPerEhop);
        mnCreateProduktPerEhop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, productInEshopCreatePage.getClass().getSimpleName());
                productInEshopCreatePage.init();
            }
        });


        // skupina produktov
        JMenuItem mnListGroupOfProdukt = new JMenuItem("Zoznam skupiny");
        mnSpravaGroupOfProducts.add(mnListGroupOfProdukt);
        mnListGroupOfProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, groupOfProductListPage.getClass().getSimpleName());
                groupOfProductListPage.init();
            }
        });

        JMenuItem mnCreateGroupOfProdukt = new JMenuItem("Vytvorenie skupiny");
        mnSpravaGroupOfProducts.add(mnCreateGroupOfProdukt);
        mnCreateGroupOfProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, groupOfProductsCreatePage.getClass().getSimpleName());
                groupOfProductsCreatePage.init();
            }
        });

        JMenuItem mnAddProductToGroupOfProdukt = new JMenuItem("Pridaj produkt do skupiny");
        mnSpravaGroupOfProducts.add(mnAddProductToGroupOfProdukt);
        mnAddProductToGroupOfProdukt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, groupOfProductAddProductPage.getClass().getSimpleName());
                groupOfProductAddProductPage.init();
            }
        });


    }
}
