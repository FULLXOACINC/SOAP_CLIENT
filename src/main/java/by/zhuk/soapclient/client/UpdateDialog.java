package by.zhuk.soapclient.client;

import by.zhuk.soapclient.soap.ArticleServiceStub;
import by.zhuk.soapclient.soap.SOAPClient;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateDialog {

    private String name;
    private String dialogName;

    private final static String INTRO = "INTRO";
    private final static String BODY = "BODY";
    private final static String CODE_EXAMPLE = "CODE EXAMPLE";

    private String[] labelString = {INTRO, BODY, CODE_EXAMPLE};

    private JDialog dialog;

    private Map<String, JTextArea> fieldID = new HashMap<>();

    private SOAPClient client;


    public UpdateDialog(SOAPClient client, String dialogName, ArticleServiceStub.Article article) {
        this.name = article.getName();
        this.client = client;
        this.dialogName = dialogName;

        for (String aLabelString : labelString) {
            JTextArea textArea = new JTextArea();
            fieldID.put(aLabelString, textArea);
        }
        fieldID.get(INTRO).setText(article.getInto());
        fieldID.get(BODY).setText(article.getBody());
        fieldID.get(CODE_EXAMPLE).setText(article.getCodeExample());

    }

    public void closeDialog() {
        dialog.dispose();
        dialog = null;
    }

    public void show() {
        dialog = new JDialog(new JFrame(), dialogName, true);
        createFrame();
        dialog.setSize(1200, 500);
        dialog.setResizable(true);
        dialog.setVisible(true);
    }

    private void createFrame() {
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        JLabel labelText = new JLabel(this.dialogName);
        labelText.setHorizontalAlignment(JLabel.CENTER);
        jPanelID.setLayout(new GridLayout(labelString.length, 1, 0, 0));

        jPanelID.add(new JLabel(INTRO));
        jPanelID.add(fieldID.get(labelString[0]));
        jPanelID.add(new JLabel(BODY));
        jPanelID.add(fieldID.get(labelString[1]));
        jPanelID.add(new JLabel(CODE_EXAMPLE));
        jPanelID.add(fieldID.get(labelString[2]));

        dialog.add(jPanelID, BorderLayout.NORTH);
        JButton okButton = new JButton(dialogName);
        okButton.addActionListener(actionEvent -> checkAndUpdate());
        dialog.add(okButton, BorderLayout.SOUTH);
    }

    private void checkAndUpdate() {
        if (haveEmptyField()) {
            JOptionPane.showMessageDialog(dialog,
                    "Some field are empty!",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            updateArticle();
        }
    }

    private boolean haveEmptyField() {
        return
                getTextID(INTRO).isEmpty() ||
                        getTextID(BODY).isEmpty() ||
                        getTextID(CODE_EXAMPLE).isEmpty();
    }

    private void updateArticle() {
        ArticleServiceStub.Article article = new ArticleServiceStub.Article();
        article.setName(name);
        article.setInto(getTextID(INTRO));
        article.setBody(getTextID(BODY));
        article.setCodeExample(getTextID(CODE_EXAMPLE));
        client.updateArticle(article);
        closeDialog();
    }

    private String getTextID(String key) {
        return fieldID.get(key).getText();
    }

}