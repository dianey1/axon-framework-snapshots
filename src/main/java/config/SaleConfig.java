/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.common.caching.Cache;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.CachingEventSourcingRepository;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.softech.flexbet.aggregates.Sale;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.common.lock.PessimisticLockFactory;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter;
import org.springframework.cache.annotation.EnableCaching;

@Configuration
@EnableCaching
public class SaleConfig {

    @Autowired
    public void configure(AmqpAdmin admin) {
        admin.declareExchange(exchange());
        admin.declareQueue(queue());
        admin.declareBinding(binding());
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("SaleEvents").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("SaleEvents").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

    @Bean
    public AggregateFactory<Sale> saleAggregateFactory() {
        SpringPrototypeAggregateFactory<Sale> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("sale");

        return aggregateFactory;
    }

    @Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }

    @Bean
    public SpringAggregateSnapshotter snapshotter(ParameterResolverFactory parameterResolverFactory, EventStore eventStore, TransactionManager transactionManager) {
        Executor executor = Executors.newSingleThreadExecutor(); //Or any other executor of course
        return new SpringAggregateSnapshotter(eventStore, parameterResolverFactory, executor, transactionManager);
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) throws Exception {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 3);
    }

    @Bean
    public Repository<Sale> saleRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTriggerDefinition, Cache cache, ParameterResolverFactory parameterResolverFactory) {
        return new CachingEventSourcingRepository<>(new GenericAggregateFactory<>(Sale.class), eventStore, new PessimisticLockFactory(), cache, parameterResolverFactory, snapshotTriggerDefinition);
    }

}
