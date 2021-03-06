package pojos;
// Generated 08-Jul-2015 06:12:41 by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Order generated by hbm2java
 */
@Entity
@Table(name="order"
    ,catalog="webshop"
)
public class Order  implements java.io.Serializable {


     private Integer id;
     private Client client;
     private boolean teljesitve;
     private Date datum;
     private Set<Orderitem> orderitems = new HashSet<Orderitem>(0);

    public Order() {
    }

	
    public Order(Client client, boolean teljesitve) {
        this.client = client;
        this.teljesitve = teljesitve;
    }
    public Order(Client client, boolean teljesitve, Date datum, Set<Orderitem> orderitems) {
       this.client = client;
       this.teljesitve = teljesitve;
       this.datum = datum;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="client_id", nullable=false)
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }

    
    @Column(name="teljesitve", nullable=false)
    public boolean isTeljesitve() {
        return this.teljesitve;
    }
    
    public void setTeljesitve(boolean teljesitve) {
        this.teljesitve = teljesitve;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datum", length=10)
    public Date getDatum() {
        return this.datum;
    }
    
    public void setDatum(Date datum) {
        this.datum = datum;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="order")
    public Set<Orderitem> getOrderitems() {
        return this.orderitems;
    }
    
    public void setOrderitems(Set<Orderitem> orderitems) {
        this.orderitems = orderitems;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", client=" + client + ", teljesitve=" + teljesitve + ", datum=" + datum + ", orderitems=" + orderitems + '}';
    }
}


