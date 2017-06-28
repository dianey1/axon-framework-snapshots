package org.softech.flexbet.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleAggregrateRepository extends JpaRepository<SaleEntry, String> {
    
//    @Bean
//    public Repository<SaleEntry> myAggregateRepository(EventStore eventStore, Snapshotter snapshotter) {
//        return new EventSourcingRepository<>(MyAggregate.class, eventStore, new EventCountSnapshotTriggerDefinition)snapshotter, 100));
//    }

//    @Bean
//    public SpringAggregateSnapshotterFactoryBean snapshotter() {
//        return new SpringAggregateSnapshotterFactoryBean();
//    }

}
