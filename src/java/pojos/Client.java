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
 * Client generated by hbm2java
 */
@Entity
@Table(name="client"
    ,catalog="webshop"
)
public class Client  implements java.io.Serializable {


     private Integer id;
     private String nev;
     private String email;
     private String username;
     private String password;
     private Set<Order> orders = new HashSet<Order>(0);

    public Client() {
    }

	
    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Client(String nev, String email, String username, String password, Set<Order> orders) {
       this.nev = nev;
       this.email = email;
       this.username = username;
       this.password = password;
       this.orders = orders;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="nev", length=45)
    public String getNev() {
        return this.nev;
    }
    
    public void setNev(String nev) {
        this.nev = nev;
    }

    
    @Column(name="email", length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="username", nullable=false, length=45)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="password", nullable=false, length=45)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="client")
    public Set<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nev=" + nev + ", email=" + email + ", username=" + username + ", password=" + password + ", orders=" + orders + '}';
    }
}


