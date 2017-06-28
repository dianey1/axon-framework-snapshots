/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softech.flexbet.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

/**
 *
 * @author Abongwa
 */
@Aggregate(repository="saleRepository")
//@Aggregate
public class Sale {

    @AggregateIdentifier
    String sale_id;

    public Sale() {
    }

    @CommandHandler
    public Sale(MakeSaleCommand command) {
        apply(new SaleMadeEvent(command.getId(), command.getAgent_id(), command.getProduct_id(),
                command.getProduct_price(), command.getSale_date_time()));
    }
    
    @CommandHandler
    public void update(UpdateSaleCommand updateSaleCommand) {
        apply (new UpdateSaleEvent(sale_id, updateSaleCommand.getProduct_price()));
    }

    /*
     * Will be invoked by apply method or during replay
     */
    @EventSourcingHandler
    public void on(SaleMadeEvent event) {
        this.sale_id = event.getId();
    }

}
