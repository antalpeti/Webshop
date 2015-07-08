package pojos;
// Generated 08-Jul-2015 06:12:41 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name="product"
    ,catalog="webshop"
)
public class Product  implements java.io.Serializable {


     private Integer id;
     private String title;
     private String artist;
     private Integer year;
     private String country;
     private String company;
     private Double price;
     private Set<Orderitem> orderitems = new HashSet<Orderitem>(0);

    public Product() {
    }

    public Product(String title, String artist, Integer year, String country, String company, Double price, Set<Orderitem> orderitems) {
       this.title = title;
       this.artist = artist;
       this.year = year;
       this.country = country;
       this.company = company;
       this.price = price;
       this.orderitems = orderitems;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="title", length=45)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="artist", length=45)
    public String getArtist() {
        return this.artist;
    }
    
    public void setArtist(String artist) {
        this.artist = artist;
    }

    
    @Column(name="year")
    public Integer getYear() {
        return this.year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }

    
    @Column(name="country", length=45)
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    
    @Column(name="company", length=45)
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }

    
    @Column(name="price", precision=22, scale=0)
    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    public Set<Orderitem> getOrderitems() {
        return this.orderitems;
    }
    
    public void setOrderitems(Set<Orderitem> orderitems) {
        this.orderitems = orderitems;
    }




}


