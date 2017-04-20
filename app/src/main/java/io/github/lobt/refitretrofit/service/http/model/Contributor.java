package io.github.lobt.refitretrofit.service.http.model;

/**
 * @author Lobt
 * @version 0.1
 */

public class Contributor {

    public String login;
    public int contributions;

    public Contributor(){}

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }
}
