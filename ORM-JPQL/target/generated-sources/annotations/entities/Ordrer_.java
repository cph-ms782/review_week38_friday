package entities;

import entities.Customer;
import entities.OrderLine;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-20T17:46:03")
@StaticMetamodel(Ordrer.class)
public class Ordrer_ { 

    public static volatile ListAttribute<Ordrer, OrderLine> orderLines;
    public static volatile SingularAttribute<Ordrer, Integer> ordrerID;
    public static volatile SingularAttribute<Ordrer, Customer> customer;

}