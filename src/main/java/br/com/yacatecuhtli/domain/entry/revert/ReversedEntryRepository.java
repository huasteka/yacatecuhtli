package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReversedEntryRepository extends EntityRepository<ReversedEntry> {

    ReversedEntry findByReverseId(Integer reverseId);

}
