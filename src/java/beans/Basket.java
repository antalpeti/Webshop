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
public class Basket {

    private List<Orderitem> orderList;
    private Order order;
    private Client client;

    /**
     * Creates a new instance of Basket
     */
    public Basket() {
        orderList = new ArrayList<Orderitem>();
        client = new Client();
        order = new Order(client, false, new Date(), new HashSet<Orderitem>());
    }

    public String put(Product product) {
        Orderitem oi = new Orderitem(order, product, 1, product.getPrice());
        orderList.add(oi);
        
        return "index2cart";
    }
    
    public void remove(Orderitem oi){
        orderList.remove(oi);
    }
    
    public void incAmount(Orderitem oi){
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
    
    public double getSumTotal(){
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

    
}
