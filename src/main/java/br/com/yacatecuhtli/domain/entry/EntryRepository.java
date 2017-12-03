package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends EntityRepository<Entry> {

    Page<Entry> findAllByAccountIdOrderByIssuedAtDesc(Integer accountId, Pageable pageable);

    List<Entry> findAllByCodeOrderByIssuedAtDesc(String entryCode);
}
