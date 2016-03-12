package sk.hudak.pricecomparator.client.swing.pages.tesco;

import sk.hudak.pricecomparator.client.swing.panel.TescoProductInfoListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.to.TescoProductInfoDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by jan on 5. 2. 2016.
 */
public class TescoSelectionPage extends JPanel {

    public static final String CARE_PATH = "C:\\price-comparator\\tesco\\care.txt";
    public static final String NOT_CARE_PATH = "C:\\price-comparator\\tesco\\not_care.txt";
    private final JTextField tfVstupnySubor;
    private final JButton btNacitajData;
    private TescoProductInfoListView lvUnporocessed;
//    private TescoProductInfoListView lvCare;
//    private TescoProductInfoListView lvNotCare;

    private List<TescoProductInfoDto> unprocessedList = new ArrayList<>();
//    private List<TescoProductInfoDto> careList;
//    private List<TescoProductInfoDto> unprocessedList;

    public TescoSelectionPage() {
        setLayout(null);

        // 1 riadok
        int rowNumber = 1;
        add(GuiUtils.label("Vstupny subor: ", rowNumber));
        add(tfVstupnySubor = GuiUtils.textField(rowNumber));

        rowNumber++;
        btNacitajData = GuiUtils.button("Nacitaj");
        btNacitajData.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btNacitajData.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        btNacitajData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNacitajData();
            }
        });
        add(btNacitajData);

        rowNumber++;
        lvUnporocessed = new TescoProductInfoListView() {
            @Override
            public List<TescoProductInfoDto> readData() {
                return unprocessedList;
            }

            @Override
            protected void onKeyPressed(int keyCode, TescoProductInfoDto selectedValue) {
                TescoSelectionPage.this.onKeyPressed(keyCode, selectedValue);
            }
        };
        lvUnporocessed.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                350);

        add(lvUnporocessed);

    }

    private void onKeyPressed(int keyCode, TescoProductInfoDto selectedValue) {
        if (KeyEvent.VK_F4 == keyCode) {
            onCareSelected(selectedValue);

        } else if (KeyEvent.VK_F9 == keyCode) {
            onNotCareSelected(selectedValue);
        }
    }

    private void onCareSelected(TescoProductInfoDto selectedValue) {
        onWrite(CARE_PATH, selectedValue);

        unprocessedList.remove(selectedValue);
        lvUnporocessed.reloadData();
        lvUnporocessed.setFirstSelected();
    }

    private void onNotCareSelected(TescoProductInfoDto selectedValue) {
        onWrite(NOT_CARE_PATH, selectedValue);

        unprocessedList.remove(selectedValue);
        lvUnporocessed.reloadData();
        lvUnporocessed.setFirstSelected();
    }

    private void onWrite(String pathToFile, TescoProductInfoDto selected) {
        if (selected == null) {
            return;
        }
        try {
            File careFile = new File(pathToFile);
            BufferedWriter bw = Files.newBufferedWriter(careFile.toPath(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);

            bw.write(selected.getNazov());
            bw.write("|");
            bw.write(selected.getUrl());
            bw.newLine();

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void init() {
        lvUnporocessed.reloadData();
        lvUnporocessed.setFirstSelected();
        lvUnporocessed.requestFocus();

    }

    private void onNacitajData() {
        try {
//            tfVstupnySubor.setText("C:\\price-comparator\\tesco\\Starostlivosť o dieťa.txt");
//            tfVstupnySubor.setText("C:\\price-comparator\\tesco\\Nápoje.txt");
//            tfVstupnySubor.setText("C:\\price-comparator\\tesco\\Mlieka a smotany.txt");
//            tfVstupnySubor.setText("C:\\price-comparator\\tesco\\Jogurty a dezerty.txt");
//            tfVstupnySubor.setText("C:\\price-comparator\\tesco\\Vajcia a droždie.txt");

            String path = tfVstupnySubor.getText();
            File careFile = new File(CARE_PATH);
            if (!careFile.exists()) {
                careFile.createNewFile();
            }

            File notCareFile = new File(NOT_CARE_PATH);
            if (!notCareFile.exists()) {
                notCareFile.createNewFile();
            }

            List<String> sourceLines = Files.readAllLines(new File(path).toPath(), StandardCharsets.UTF_8);
            List<String> careLines = Files.readAllLines(careFile.toPath(), StandardCharsets.UTF_8);
            List<String> notCareLines = Files.readAllLines(notCareFile.toPath(), StandardCharsets.UTF_8);

            // odstranim tie, ktore som uz presiel
            List<String> unprocessed = new ArrayList<>(sourceLines);
            unprocessed.removeAll(careLines);
            unprocessed.removeAll(notCareLines);

            unprocessedList = createList(unprocessed);

            lvUnporocessed.reloadData();
            lvUnporocessed.setFirstSelected();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private List<TescoProductInfoDto> createList(List<String> unprocessed) {
        List<TescoProductInfoDto> result = new ArrayList<>();
        int count = 0;
        for (String line : unprocessed) {
            if (line == null || line.isEmpty()) {
                continue;
            }
            if (!line.contains("|")) {
                continue;
            }
            count++;
            if (count == 50) {
                break;
            }

            StringTokenizer st = new StringTokenizer(line, "|", false);
            TescoProductInfoDto dto = new TescoProductInfoDto();
            if (st.hasMoreElements()) {
                dto.setNazov((String) st.nextElement());
            }
            if (st.hasMoreElements()) {
                dto.setUrl((String) st.nextElement());
            }
            String tmp = dto.getUrl().substring(dto.getUrl().lastIndexOf("/"), dto.getUrl().length());

            dto.setImagePath("C:\\price-comparator\\tesco\\pictures\\" + tmp + ".jpeg");
            result.add(dto);
        }
        return result;
    }
}
