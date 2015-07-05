/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.Client;
import pojos.Order;
import pojos.Orderitem;
import pojos.Product;

/**
 *
 * @author Peti
 */
@ManagedBean
@SessionScoped
public class Cart {

    private List<Orderitem> orderList;
    private Order order;
    private Client client;
    private Client loginData;
    private Constants constants;
    private String logInOut = LOGIN;
    public static final String LOGIN = "Login";
    public static final String LOGOUT = "Logout";

    /**
     * Creates a new instance of Basket
     */
    public Cart() {
        orderList = new ArrayList<Orderitem>();
        loginData = new Client();
        constants = new Constants();
        order = new Order(client, false, new Date(), new HashSet<Orderitem>());
    }

    public void findClient() {
        if (loginData.getUsername() != null || loginData.getPassword() != null) {
            Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
            List<Client> clients;
            Query query = session.createQuery("FROM Client WHERE username =:username AND password =:password");
            query.setParameter("username", loginData.getUsername());
            query.setParameter("password", loginData.getPassword());
            clients = query.list();
            if (clients.size() > 0) {
                client = clients.get(0);
            }
            session.close();
        }
    }
    
    public String logInOut(String startPage, String endPage){
        if (client == null && loginData.getUsername() != null && loginData.getPassword() != null) {
            findClient();
        }
        if (client != null) {
            if (logInOut.equals(LOGOUT)) {
                client = null;
                logInOut = LOGIN;
                return "";
            } else {
                logInOut = LOGOUT;
                return navigatePage(startPage, endPage);
            }
        } else {
            return navigatePage(startPage, constants.getLogin());
        }
    }
    
    public String register(){
        if(loginData.getNev() != null && loginData.getEmail() != null && loginData.getUsername() != null && loginData.getPassword() != null){
            Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(loginData);
            session.getTransaction().commit();
            session.close();
            return logInOut(constants.getRegister(),constants.getIndex());
        }
        return "";
    }

    public String navigatePage(String startPage, String endPage) {
        return startPage + "2" + endPage;
    }

    public void registerClient(Client client) {

    }

    public String put(Product product) {
        Orderitem oi = new Orderitem(order, product, 1, product.getPrice());
        orderList.add(oi);

        return constants.getIndex() + "2" + constants.getCart();
    }

    public void remove(Orderitem oi) {
        orderList.remove(oi);
    }

    public void incAmount(Orderitem oi) {
        oi.setQty(oi.getQty() + 1);
        oi.setPrice(oi.getQty() * oi.getProduct().getPrice());
    }

    public void decAmount(Orderitem oi) {
        if (oi.getQty() > 1) {
            oi.setQty(oi.getQty() - 1);
            oi.setPrice(oi.getQty() * oi.getProduct().getPrice());
        } else {
            remove(oi);
        }
    }

    public String purchase() {
        if (client == null) {
            return constants.getCart() + "2" + constants.getLogin();
        }
        return "";
    }

    public double getSumTotal() {
        double sumTotal = 0;
        for (Orderitem oi : orderList) {
            sumTotal += oi.getPrice();
        }
        return sumTotal;
    }

    public List<Orderitem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Orderitem> orderList) {
        this.orderList = orderList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getLogInOut() {
        return logInOut;
    }

    public void setLogInOut(String logInOut) {
        this.logInOut = logInOut;
    }

    public Client getLoginData() {
        return loginData;
    }

    public void setLoginData(Client loginData) {
        this.loginData = loginData;
    }
}
