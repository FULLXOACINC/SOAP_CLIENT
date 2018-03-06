package by.zhuk.soapclient.soap;

import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SOAPClient {
    private static final String END_POINT = "http://localhost:8080/axis2/services/ArticleService?wsdl";
    private ArticleServiceStub stub;

    public SOAPClient() {
        try {
            stub = new ArticleServiceStub(END_POINT);
        } catch (AxisFault axisFault) {
           // axisFault.printStackTrace();
        }
    }

    public void saveArticle(ArticleServiceStub.Article article) {
        try {
            stub = new ArticleServiceStub(END_POINT);
            ArticleServiceStub.SaveArticle saveArticle = new ArticleServiceStub.SaveArticle();
            saveArticle.setArticle(article);
            try {
                stub.saveArticle(saveArticle);
            } catch (ArticleServiceFileNotFoundExceptionException e) {
                e.printStackTrace();
            }
        } catch (RemoteException ignored) {
        }
    }

    public List<String> getArticleName() {
        try {
            stub = new ArticleServiceStub(END_POINT);
            ArticleServiceStub.GetArticlesName getArticlesName = new ArticleServiceStub.GetArticlesName();
            ArticleServiceStub.GetArticlesNameResponse response = stub.getArticlesName(getArticlesName);
            String[] strings = response.get_return();
            System.out.println(Arrays.toString(strings));
            if (strings != null) {
                return Arrays.asList(strings);
            } else {
                return new ArrayList<>();
            }
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        System.out.println(new SOAPClient().getArticle("gg").getBody());
        new SOAPClient().getArticleName();
    }


    public void updateArticle(ArticleServiceStub.Article article) {
        try {
            stub = new ArticleServiceStub(END_POINT);
            ArticleServiceStub.UpdateArticle updateArticle = new ArticleServiceStub.UpdateArticle();
            updateArticle.setArticle(article);
            try {
                stub.updateArticle(updateArticle);
            } catch (ArticleServiceIOExceptionException e) {
                e.printStackTrace();
            }
        } catch (RemoteException ignored) {
        }
    }

    public ArticleServiceStub.Article getArticle(String name) {
        ArticleServiceStub.Article article = null;
        try {
            stub = new ArticleServiceStub(END_POINT);
            ArticleServiceStub.GetArticle getArticle = new ArticleServiceStub.GetArticle();
            getArticle.setName(name);
            ArticleServiceStub.GetArticleResponse response = stub.getArticle(getArticle);
            article = response.get_return();
            //    System.out.println(strings[0]);

        } catch (RemoteException e) {
            return null;
        } catch (ArticleServiceIOExceptionException e) {
            e.printStackTrace();
        }
        return article;
    }

    public void deleteArticle(String name) {
        try {
            stub = new ArticleServiceStub(END_POINT);
            ArticleServiceStub.DeleteArticle deleteArticle = new ArticleServiceStub.DeleteArticle();
            deleteArticle.setName(name);
            try {
                stub.deleteArticle(deleteArticle);
            } catch (ArticleServiceIOExceptionException e) {
                e.printStackTrace();
            }
        } catch (RemoteException ignored) {
        }

    }
}