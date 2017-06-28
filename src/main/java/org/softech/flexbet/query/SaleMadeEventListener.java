/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softech.flexbet.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.softech.flexbet.aggregates.SaleMadeEvent;

/**
 *
 * @author Abongwa
 */

@Component
class SaleMadeEventListener {

    private final SaleAggregrateRepository repository;
    
    @Autowired
    public SaleMadeEventListener(SaleAggregrateRepository repository){
        this.repository = repository;
    }
    
    @EventHandler
    public void SaleMadeEventHandler(SaleMadeEvent event){
        repository.save(new SaleEntry(event.getId(), event.getAgent_id(), event.getProduct_id(), 
                event.getProduct_price(), event.getSale_date_time()));
    }
}
