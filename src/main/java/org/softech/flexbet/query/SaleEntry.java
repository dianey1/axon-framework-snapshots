package org.softech.flexbet.query;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SaleEntry {

    @Id
    private String id;
    private String agent_id;
    private String product_id;
    double product_price;
    Timestamp sale_date_time;

//    @Override
//    public String toString() {
//        return "A reservation{"
//                + "id:" + id
//                + ", reservationName='" + reservationName + '\''
//                + '}';
//    }

    public SaleEntry(String id, String agent_id, String product_id, double product_price, Timestamp sale_date_time) {
        this.id = id;
        this.agent_id = agent_id;
        this.product_id = product_id;
        this.product_price = product_price;
        this.sale_date_time = sale_date_time;
    }

    public SaleEntry() {
    }

    public String getId() {
        return id;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public double getProduct_price() {
        return product_price;
    }

    public Timestamp getSale_date_time() {
        return sale_date_time;
    }

    
}
