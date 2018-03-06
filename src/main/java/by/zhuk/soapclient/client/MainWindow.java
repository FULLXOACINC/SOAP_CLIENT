package by.zhuk.soapclient.client;


import by.zhuk.soapclient.soap.ArticleServiceStub;
import by.zhuk.soapclient.soap.SOAPClient;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow {

    private Logger logger = LoggerFactory.getLogger(MainWindow.class);

    private final String host = "127.0.0.1";

    private final int PORT = 8080;

    private SOAPClient articleClient;

    private ArticleComponent articleComponent;
    private final static String IMG_PATCH = "img/";

    private MainWindow() {
        articleClient = new SOAPClient();
        JFrame frame = new JFrame("AXIS2 Java Client");

        frame.setLayout(new BorderLayout());
        frame.add(createToolBar(), BorderLayout.NORTH);
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new ExitAdapter(this));
        articleComponent = new ArticleComponent();
        articleComponent.setClient(articleClient);
        frame.add(articleComponent, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void updateTable() {
        articleComponent.updatePanel();
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(makeButton(new JButton(), "ADD.png", actionEvent -> addArticle()));
        toolBar.add(makeButton(new JButton(), "DELETE.png", actionEvent -> deleteArticle()));
        toolBar.add(makeButton(new JButton(), "DELETE.png", actionEvent -> updateArticle()));
        return toolBar;
    }

    private void addArticle() {
        logger.info("add Article");
        AddDialog dialog = new AddDialog(articleClient, "Add Article");
        dialog.show();
        articleComponent.updatePanel();
    }

    private void deleteArticle() {
        ArticleServiceStub.Article article = articleComponent.getSelectedArticle();
        if (article != null) {
            int confirm = JOptionPane.showOptionDialog(
                    null, "Are You Sure to delete article " + article.getName() + "?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
                articleClient.deleteArticle(article.getName());
                articleComponent.resetSelectedArticle();
                updateTable();
            }
            logger.info("delete Article");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Select article !",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateArticle() {
        ArticleServiceStub.Article articleThrift = articleComponent.getSelectedArticle();
        if (articleThrift != null) {
            UpdateDialog dialog = new UpdateDialog(articleClient, "Update Article " + articleThrift.getName(), articleThrift);
            dialog.show();
            articleComponent.updatePanel();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Select article !",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new MainWindow().updateTable();

    }
    
    private JButton makeButton(JButton button, String imgString, ActionListener action) {
        button.addActionListener(action);
        String patch = IMG_PATCH + imgString;
        ImageIcon img = new ImageIcon(patch);
        button.setIcon(img);
        return button;
    }


}
