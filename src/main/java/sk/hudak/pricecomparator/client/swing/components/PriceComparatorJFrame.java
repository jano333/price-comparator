package sk.hudak.pricecomparator.client.swing.components;

import sk.hudak.pricecomparator.client.swing.pages.EshopsPerProductListPage;
import sk.hudak.pricecomparator.client.swing.pages.ProductInEshopCreatePage;
import sk.hudak.pricecomparator.client.swing.pages.ProductsInEshopListPage;
import sk.hudak.pricecomparator.client.swing.pages.eshop.EshopCreatePage;
import sk.hudak.pricecomparator.client.swing.pages.eshop.EshopListPage;
import sk.hudak.pricecomparator.client.swing.pages.group.GroupOfProductAddProductPage;
import sk.hudak.pricecomparator.client.swing.pages.group.GroupOfProductListPage;
import sk.hudak.pricecomparator.client.swing.pages.group.GroupOfProductsCreatePage;
import sk.hudak.pricecomparator.client.swing.pages.product.ProductCreatePage;
import sk.hudak.pricecomparator.client.swing.pages.product.ProductListPage;
import sk.hudak.pricecomparator.client.swing.pages.tesco.TescoSelectionPage;

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

    private GroupOfProductListPage groupOfProductListPage = new GroupOfProductListPage();
    private GroupOfProductsCreatePage groupOfProductsCreatePage = new GroupOfProductsCreatePage();
    private GroupOfProductAddProductPage groupOfProductAddProductPage = new GroupOfProductAddProductPage();
    //    private GroupOfProductListPage2 groupOfProductListPage = new GroupOfProductListPage2();
    private TescoSelectionPage tescoSelectionPage = new TescoSelectionPage();


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

        setSize(1000, 600);

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

        cardContainer.add(tescoSelectionPage.getClass().getSimpleName(), tescoSelectionPage);

        getContentPane().add(cardContainer);

        //zabezpocim zobprazenie stranky eshopsPerProductListPage:
        CardLayout cl = (CardLayout) (cardContainer.getLayout());
        //TODO odkomentovat
        cl.show(cardContainer, eshopsPerProductListPage.getClass().getSimpleName());
        eshopsPerProductListPage.init();
//          cl.show(cardContainer, tescoSelectionPage.getClass().getSimpleName());


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


        // Eshop Menu
        createEshopMenus(menuBar);


        // Produkt Menu
        createProductMenu(menuBar);


        // Skupina MENU
        createSkupinaMenu(menuBar);

        createAdminMenu(menuBar);

    }

    private void createAdminMenu(JMenuBar menuBar) {
        JMenu mnSpravaAdmin = new JMenu("Admin");
        mnSpravaAdmin.setMnemonic(KeyEvent.VK_A);
        menuBar.add(mnSpravaAdmin);

        JMenuItem mnTesctoSelectCareProducts = new JMenuItem("Tesco care products");
        mnSpravaAdmin.add(mnTesctoSelectCareProducts);
        mnTesctoSelectCareProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cardContainer.getLayout());
                cl.show(cardContainer, tescoSelectionPage.getClass().getSimpleName());
                tescoSelectionPage.init();
            }
        });
    }

    private void createEshopMenus(JMenuBar menuBar) {
        JMenu mnSpravaEshop = new JMenu("Eshopy");
        mnSpravaEshop.setMnemonic(KeyEvent.VK_E);
        menuBar.add(mnSpravaEshop);


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
    }

    private void createProductMenu(JMenuBar menuBar) {
        JMenu mnSpravaProductov = new JMenu("Produkty");
        mnSpravaProductov.setMnemonic(KeyEvent.VK_P);
        menuBar.add(mnSpravaProductov);


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
    }

    private void createSkupinaMenu(JMenuBar menuBar) {

        JMenu mnSpravaGroupOfProducts = new JMenu("Skupina produktov");
        mnSpravaGroupOfProducts.setMnemonic(KeyEvent.VK_S);
        menuBar.add(mnSpravaGroupOfProducts);

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
