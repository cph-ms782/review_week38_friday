package old;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQuery(name = "ItemType.deleteAllRows", query = "DELETE from ItemType")
public class ItemType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemTypeID;
    private String name;
    private String description;
    private double price;
    
    @OneToMany(mappedBy = "itemType",cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines = new ArrayList();
    
    public ItemType() {
    }

    public ItemType(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Integer getItemTypeID() {
        return itemTypeID;
    }

    public void setItemTypeID(Integer itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ItemType other = (ItemType) obj;
        if (!Objects.equals(this.itemTypeID, other.itemTypeID)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "ItemType{" + "itemTypeID=" + itemTypeID + ", name=" + name + ", description=" + description + ", price=" + price + ", orderLines=" + orderLines + '}';
    }
   
}
