/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Store some constants to help the page navigation.
 * @author Peti
 */
@ManagedBean
@SessionScoped
public class Constants {
    
    private final String login;
    private final String index;
    private final String cart;
    private final String register;

    public String getRegister() {
        return register;
    }
    /**
     * Creates a new instance of Constants
     */
    public Constants() {
        this.login = "login";
        this.cart = "cart";
        this.index = "index";
        this.register = "register";
    }

    public String getLogin() {
        return login;
    }

    public String getIndex() {
        return index;
    }

    public String getCart() {
        return cart;
    }
    
}
