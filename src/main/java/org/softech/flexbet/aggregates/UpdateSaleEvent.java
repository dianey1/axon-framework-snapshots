/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softech.flexbet.aggregates;

/**
 *
 * @author Admin
 */
public class UpdateSaleEvent {
    
    private String id;
    double product_price;

    public UpdateSaleEvent(String id, double product_price) {
        this.id = id;
        this.product_price = product_price;
    }
    
}
