/**
 * ArticleServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package by.zhuk.soapclient.soap;


/**
 *  ArticleServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class ArticleServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public ArticleServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public ArticleServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for getArticlesName method
     * override this method for handling normal response from getArticlesName operation
     */
    public void receiveResultgetArticlesName(
        by.zhuk.soapclient.soap.ArticleServiceStub.GetArticlesNameResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getArticlesName operation
     */
    public void receiveErrorgetArticlesName(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getArticle method
     * override this method for handling normal response from getArticle operation
     */
    public void receiveResultgetArticle(
        by.zhuk.soapclient.soap.ArticleServiceStub.GetArticleResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getArticle operation
     */
    public void receiveErrorgetArticle(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateArticle method
     * override this method for handling normal response from updateArticle operation
     */
    public void receiveResultupdateArticle() {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateArticle operation
     */
    public void receiveErrorupdateArticle(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for deleteArticle method
     * override this method for handling normal response from deleteArticle operation
     */
    public void receiveResultdeleteArticle() {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from deleteArticle operation
     */
    public void receiveErrordeleteArticle(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for saveArticle method
     * override this method for handling normal response from saveArticle operation
     */
    public void receiveResultsaveArticle() {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from saveArticle operation
     */
    public void receiveErrorsaveArticle(java.lang.Exception e) {
    }
}
