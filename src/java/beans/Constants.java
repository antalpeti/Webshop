/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Peti
 */
@ManagedBean
@SessionScoped
public class Constants {
    
    private String login = "login";
    private String index = "index";
    private String logreg = "logreg";
    private String cart = "cart";
    /**
     * Creates a new instance of Constants
     */
    public Constants() {
    }

    public String getLogin() {
        return login;
    }

    public String getIndex() {
        return index;
    }

    public String getLogreg() {
        return logreg;
    }

    public String getCart() {
        return cart;
    }
    
}
