package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledEntryRepository extends EntityRepository<ScheduledEntry> {

}
