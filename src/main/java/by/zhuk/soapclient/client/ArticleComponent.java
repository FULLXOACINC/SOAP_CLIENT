package by.zhuk.soapclient.client;


import by.zhuk.soapclient.soap.ArticleServiceStub;
import by.zhuk.soapclient.soap.SOAPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ArticleComponent extends JComponent {
    private Logger logger = LoggerFactory.getLogger(ArticleComponent.class);

    private JPanel articles;
    private JTextArea content;
    private ArticleServiceStub.Article selectedArticle;
    private SOAPClient client;


    public ArticleComponent() {
        articles = new JPanel();
        articles.setLayout(new GridLayout(15, 1, 0, 0));
        content = new JTextArea();
        content.setBackground(Color.decode("#2C001E"));
        content.setForeground(Color.WHITE);
        content.setEnabled(false);
        setLayout(new GridLayout(1, 2, 0, 0));
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(articles);
        add(content);
    }


    public ArticleServiceStub.Article getSelectedArticle() {
        return selectedArticle;
    }

    public void updatePanel() {
        articles.removeAll();
        createPanel();
        revalidate();
        repaint();

    }

    private void createPanel() {
        List<String> articlesList = client.getArticleName();
        if (selectedArticle == null) {
            selectedArticle = client.getArticle(articlesList.get(0));
        } else {
            selectedArticle = client.getArticle(selectedArticle.getName());
        }
        updateContent();

        if (articlesList == null) {
            return;
        }
        for (String article : articlesList) {
            JButton button = new JButton(article);
            button.addActionListener(actionEvent -> {
                selectedArticle = client.getArticle(article);
                updateContent();
            });
            articles.add(button);
        }
    }

    public void updateContent() {
        content.setText("");
        String into = selectedArticle.getInto();
        content.append("Intro\n\n");
        content.append(into);
        content.append("\nMain\n\n");
        String body = selectedArticle.getBody();
        content.append(body);
        content.append("\nExamples\n\n");

        String codeExample = selectedArticle.getCodeExample();
        content.append(codeExample);
    }


    public void setClient(SOAPClient client) {
        this.client = client;
    }

    public void resetSelectedArticle() {
        selectedArticle = null;
    }
}