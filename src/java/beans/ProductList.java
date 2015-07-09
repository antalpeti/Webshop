/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private Set<String> countrySet;
    private String actCountry;

    /**
     * Creates a new instance of ProductList
     */
    public ProductList() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        productList = session.createQuery("FROM Product").list();
        session.close();
        countrySet = new HashSet<String>();
        countrySet.add("");
        for (Product product : productList) {
            countrySet.add(product.getCountry());
        }
    }

    public void filterCountry() {
        List<Product> filteredList = new ArrayList<Product>();
        if (!actCountry.isEmpty()) {
            for (Product product : productList) {
                if (actCountry.equals(product.getCountry())) {
                    filteredList.add(product);
                }
            }
            productList = filteredList;
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Set<String> getCountrySet() {
        return countrySet;
    }

    public void setCountrySet(Set<String> countrySet) {
        this.countrySet = countrySet;
    }

    public String getActCountry() {
        return actCountry;
    }

    public void setActCountry(String actCountry) {
        this.actCountry = actCountry;
    }
}
