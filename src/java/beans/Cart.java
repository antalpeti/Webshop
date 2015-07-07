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
import java.util.Set;
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

    /**
     * The whole order.
     */
    private Order order;
    /**
     * The actually logged in user
     */
    private Client client;
    /**
     * Store the login data of the user.
     */
    private Client loginData;
    private final Constants constants;
    /**
     * Indicate logged in or out state.
     */
    private String logInOut = LOGIN;
    /**
     * Link text in case of logged out state.
     */
    public static final String LOGIN = "Login";
    /**
     * Link text in case of logged in state.
     */
    public static final String LOGOUT = "Logout";

    /**
     * Creates a new instance of Cart.
     */
    public Cart() {
        orderList = new ArrayList<Orderitem>();
        loginData = new Client();
        constants = new Constants();
        order = new Order(client, false, new Date(), new HashSet<Orderitem>());
    }

    /**
     * Find the user from the database according to the given username and
     * password and set into the client field.
     */
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

    /**
     * Change login/logout text of the link and handle the login/logout state of
     * the user.
     */
    public String logInOut(String startPage, String endPage) {
        //try to find the user
        if (client == null && loginData.getUsername() != null && loginData.getPassword() != null) {
            findClient();
        }
        if (client != null) {
            //if the user exist and logout the text
            //that means it has to logout the actual user
            if (logInOut.equals(LOGOUT)) {
                client = null;
                logInOut = LOGIN;
                return "";
            } else {
                //the user logged in
                logInOut = LOGOUT;
                return navigatePage(startPage, endPage);
            }
        } else {
            //wrong access data try it again
            return navigatePage(startPage, constants.getLogin());
        }
    }

    /**
     * Register a new user according to the given data and navigate to the index
     * page if it is successful.
     */
    public String register() {
        if (loginData.getNev() != null && loginData.getEmail() != null && loginData.getUsername() != null && loginData.getPassword() != null) {
            Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(loginData);
            session.getTransaction().commit();
            session.close();
            return logInOut(constants.getRegister(), constants.getIndex());
        }
        return "";
    }

    /**
     * Navigate between start and end page.
     *
     * @param startPage the start page
     * @param endPage the end page
     * @return the navigation text
     */
    public String navigatePage(String startPage, String endPage) {
        return startPage + "2" + endPage;
    }

    /**
     * Add new product to the order item list.
     *
     * @param product the new product
     * @return the navigation text
     */
    public String put(Product product) {
        boolean oiExist = false;
        for (Orderitem storedOi : orderList) {
            if (storedOi.getProduct().getId().equals(product.getId())) {
                incAmount(storedOi);
                oiExist = true;
            }
        }
        if (!oiExist) {
            Orderitem newOi = new Orderitem(order, product, 1, product.getPrice());
            orderList.add(newOi);
        }

        return constants.getIndex() + "2" + constants.getCart();
    }

    /**
     * Remove an order item.
     *
     * @param oi the removable order item.
     */
    public void remove(Orderitem oi) {
        orderList.remove(oi);
    }

    /**
     * Increase an order item by one.
     *
     * @param oi the increasable order item
     */
    public void incAmount(Orderitem oi) {
        oi.setQty(oi.getQty() + 1);
        oi.setPrice(oi.getQty() * oi.getProduct().getPrice());
    }

    /**
     * Decrease an order item by one.
     *
     * @param oi the reducible order item
     */
    public void decAmount(Orderitem oi) {
        if (oi.getQty() > 1) {
            oi.setQty(oi.getQty() - 1);
            oi.setPrice(oi.getQty() * oi.getProduct().getPrice());
        } else {
            remove(oi);
        }
    }

    /**
     * Store the order and order items into the database.
     *
     * @return the navigation text
     */
    public String purchase() {
        if (client == null) {
            return constants.getCart() + "2" + constants.getLogin();
        } else {
//            Set<Orderitem> orderSet = new HashSet<Orderitem>();
//            for (Orderitem oi : orderList) {
//                orderSet.add(oi);
//            }
            
            Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(order);
            for (Orderitem oi : orderList) {
                oi.setOrder(order);
                order.getOrderitems().add(oi);
                session.save(oi);
            }
            session.getTransaction().commit();
            session.close();
            return "";
        }
    }

    /**
     * Calculate the total price of the order items according to the order.
     */
    public double getSumTotal() {
        double sumTotal = 0;
        for (Orderitem oi : orderList) {
            sumTotal += oi.getPrice();
        }
        return sumTotal;
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

    public List<Orderitem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Orderitem> orderList) {
        this.orderList = orderList;
    }

}
