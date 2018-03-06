package by.zhuk.soapclient.client;


import by.zhuk.soapclient.soap.ArticleServiceStub;
import by.zhuk.soapclient.soap.SOAPClient;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AddDialog {

    private String dialogName;

    private final static String NAME = "NAME";
    private final static String INTRO = "INTRO";
    private final static String BODY = "BODY";
    private final static String CODE_EXAMPLE = "CODE EXAMPLE";

    private String[] labelString = {NAME, INTRO, BODY, CODE_EXAMPLE};

    private JDialog dialog;

    private Map<String, JTextArea> fieldID = new HashMap<>();

    private SOAPClient client;


    public AddDialog(SOAPClient client, String dialogName) {
        this.client = client;
        this.dialogName = dialogName;

        for (int field = 0; field < labelString.length; field++) {
            JTextArea textArea = new JTextArea();
            fieldID.put(labelString[field], textArea);
        }

    }

    public void show() {
        dialog = new JDialog(new JFrame(), dialogName, true);
        createFrame();
        dialog.setSize(500, 200);
        dialog.setResizable(true);
        dialog.setVisible(true);
    }

    public void closeDialog() {
        dialog.dispose();
        dialog = null;
    }

    private void createFrame() {
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        JLabel labelText = new JLabel(this.dialogName);
        labelText.setHorizontalAlignment(JLabel.CENTER);
        jPanelID.setLayout(new GridLayout(8, 1, 0, 0));

        jPanelID.add(new JLabel(NAME));
        jPanelID.add(fieldID.get(labelString[0]));
        jPanelID.add(new JLabel(INTRO));
        jPanelID.add(fieldID.get(labelString[1]));
        jPanelID.add(new JLabel(BODY));
        jPanelID.add(fieldID.get(labelString[2]));
        jPanelID.add(new JLabel(CODE_EXAMPLE));
        jPanelID.add(fieldID.get(labelString[3]));

        dialog.add(jPanelID, BorderLayout.NORTH);
        JButton okButton = new JButton(dialogName);
        okButton.addActionListener(actionEvent -> checkAndSave());
        dialog.add(okButton, BorderLayout.SOUTH);
    }

    private void checkAndSave() {
        if (haveEmptyField()) {
            JOptionPane.showMessageDialog(dialog,
                    "Some field are empty!",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            saveArticle();
        }
    }

    private boolean haveEmptyField() {
        return getTextID(NAME).isEmpty() ||
                getTextID(INTRO).isEmpty() ||
                getTextID(BODY).isEmpty() ||
                getTextID(CODE_EXAMPLE).isEmpty();
    }

    private void saveArticle() {

        ArticleServiceStub.Article article = new ArticleServiceStub.Article();
        article.setName(getTextID(NAME));
        article.setInto(getTextID(INTRO));
        article.setBody(getTextID(BODY));
        article.setCodeExample(getTextID(CODE_EXAMPLE));
        client.saveArticle(article);

        closeDialog();
    }

    private String getTextID(String key) {
        return fieldID.get(key).getText();
    }

}