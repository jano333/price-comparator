package sk.hudak.pricecomparator.client.swing.components;

import sk.hudak.pricecomparator.middle.api.to.ProductListDto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 24. 1. 2016.
 */
public class BasicTableImpl extends BasicTable<ProductListDto> {

    public BasicTableImpl(List<? extends BasicColumn> columns) {
        super(columns);
    }

    @Override
    protected List<ProductListDto> loadData() {
        List<ProductListDto> data = new ArrayList<>();
        ProductListDto dto = new ProductListDto();
        dto.setName("aloha");
        data.add(dto);

        dto = new ProductListDto();
        dto.setName("cau");
        data.add(dto);

        return data;
    }

    // -------------------------------------------------------------

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //columns
        List<BasicColumn> columns = new ArrayList<>();
        columns.add(new TextColumn("name", "hlavicka1", 125));
        columns.add(new TextColumn("name", "hlavicka2", 125));
        columns.add(new TextColumn("name", "hlavicka3", 125));

        //Create and set up the content pane.
        BasicTableImpl newContentPane = new BasicTableImpl(columns);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}
