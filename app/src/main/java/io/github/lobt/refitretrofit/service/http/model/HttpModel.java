package io.github.lobt.refitretrofit.service.http.model;

/**
 * @author Lobt
 * @version 0.1
 */

public class HttpModel<T> {

    // 天狗API返回的数据格式
//    public boolean status;
//    public String total;
//    public T tngou;


    // 近义词反义词API数据格式

    public int status;
    public String msg;
    public T result;

}
