/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softech.flexbet.aggregates;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 *
 * @author Admin
 */
public class UpdateSaleCommand {
    
    @TargetAggregateIdentifier
    private final String id;
    
    private final double product_price;

    public double getProduct_price() {
        return product_price;
    }

    public String getId() {
        return id;
    }

    public UpdateSaleCommand(String id, double product_price) {
        this.id = id;
        this.product_price = product_price;
    }
    
}
