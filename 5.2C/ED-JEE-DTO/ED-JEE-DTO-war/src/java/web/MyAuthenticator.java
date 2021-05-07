package web;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cyrus
 */
public class MyAuthenticator extends Authenticator {

    PasswordAuthentication mypa;

    public MyAuthenticator(String username, String password) {
        mypa = new PasswordAuthentication(username, password);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return mypa;
    }

}
