package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.entry.EntryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduledEntryService extends AbstractService {

    @Autowired
    private ScheduledEntryValidator scheduledEntryValidator;

    @Autowired
    private ScheduledEntryRepository scheduledEntryRepository;

    @Autowired
    private ScheduledEntryConverter scheduledEntryConverter;

    @Transactional
    public ScheduledEntryJson scheduleEntry(ScheduledEntryJson scheduledEntry, EntryType type) {
        scheduledEntryValidator.validate(scheduledEntry);
        scheduledEntry.getEntry().setType(type);
        return scheduledEntryRepository.save(scheduledEntryConverter.convert(scheduledEntry)).toJson();
    }

}
