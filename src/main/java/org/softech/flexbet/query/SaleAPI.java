package org.softech.flexbet.query;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.softech.flexbet.aggregates.MakeSaleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.softech.flexbet.aggregates.UpdateSaleCommand;

/**
 *
 * @author Abongwa
 */
@RestController
public class SaleAPI {
    
    private final SaleAggregrateRepository repository;
    private final CommandGateway commandGateway;
    
//    @Autowired
    public SaleAPI(SaleAggregrateRepository repository, CommandGateway commandGateway){
        this.repository = repository;
        this.commandGateway = commandGateway;
    }
    
    @PostMapping
    public void makeSale(@RequestBody Map<String,String> request){
        String id = UUID.randomUUID().toString();
        commandGateway.send(new MakeSaleCommand(id, request.get("agent_id"), request.get("product_id"),
                Double.parseDouble(request.get("product_price")), Timestamp.valueOf(request.get("sale_date_time"))));
    }
    
//    @PostMapping
//    public void updateSale(@RequestBody Map<String,String> request){
//        commandGateway.send(new UpdateSaleCommand(request.get("id"),
//                Double.parseDouble(request.get("product_price"))));
////        EventCountSnapshotTriggerDefinition
//    }
    
    /*@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(@RequestParam(value = "id") String id,
                    @RequestParam(value = "product_price", required = true) double product_price,
                    HttpServletResponse response) {

        try {
            UpdateSaleCommand command = new UpdateSaleCommand(id, product_price);
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_CREATED);// Set up the 201 CREATED response
            return;
        } catch (AssertionError ae) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (CommandExecutionException cex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (null != cex.getCause()) {
                if (cex.getCause() instanceof ConcurrencyException) {
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        }
    }*/
    
    @GetMapping
    public List<SaleEntry> findAll(){
        return repository.findAll();
    }
    
     @GetMapping("/{id}")
    public SaleEntry findOne(@PathVariable(value = "id",required = true)String id){
        return repository.findOne(id);
    }
    
//    @RequestMapping(value = "/{id}", method = PUT)
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
//        return null;
//    }
//    
//    @RequestMapping(value = "/{id}", method = POST)
//    public Res
//    ponseEntity<?> post(@PathVariable String id, @RequestBody Object input) {
//        return null;
//    }
//    
//    @RequestMapping(value = "/{id}", method = DELETE)
//    public ResponseEntity<Object> delete(@PathVariable String id) {
//        return null;
//    }
    
}
