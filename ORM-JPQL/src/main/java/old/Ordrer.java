package old;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Ordrer.deleteAllRows", query = "DELETE from Ordrer")
public class Ordrer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordrerID;

    @OneToMany(mappedBy = "ordrer", cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines = new ArrayList();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    public Ordrer() {
    }

    public Integer getOrdrerID() {
        return ordrerID;
    }

    public void setOrderID(Integer orderID) {
        this.ordrerID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void addOrderLine(OrderLine ol) {
        this.orderLines.add(ol);
        if (ol.getOrdrer() != this) {
            ol.setOrdrer(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ordrer other = (Ordrer) obj;
        if (!Objects.equals(this.ordrerID, other.ordrerID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ordrer{" + "ordrerID=" + ordrerID + ", orderLines=" + orderLines + ", customer=" + customer + '}';
    }

}
