/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import pojos.Product;

/**
 *
 * @author Peti
 */
@ManagedBean
@RequestScoped
public class ProductList {
    
    private List<Product> productList;
    /**
     * Creates a new instance of ProductList
     */
    public ProductList() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        productList = session.createQuery("FROM Product").list();
        session.close();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
