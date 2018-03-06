/**
 * ArticleServiceIOExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package by.zhuk.soapclient.soap;

public class ArticleServiceIOExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1520196723570L;
    private by.zhuk.soapclient.soap.ArticleServiceStub.ArticleServiceIOException faultMessage;

    public ArticleServiceIOExceptionException() {
        super("ArticleServiceIOExceptionException");
    }

    public ArticleServiceIOExceptionException(java.lang.String s) {
        super(s);
    }

    public ArticleServiceIOExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public ArticleServiceIOExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        by.zhuk.soapclient.soap.ArticleServiceStub.ArticleServiceIOException msg) {
        faultMessage = msg;
    }

    public by.zhuk.soapclient.soap.ArticleServiceStub.ArticleServiceIOException getFaultMessage() {
        return faultMessage;
    }
}
