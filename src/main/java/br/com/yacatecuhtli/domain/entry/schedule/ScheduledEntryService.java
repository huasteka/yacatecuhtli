package br.com.yacatecuhtli.domain.entry.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.entry.EntryType;

@Service
public class ScheduledEntryService extends AbstractService {

    private ScheduledEntryValidator scheduledEntryValidator;

    private ScheduledEntryRepository scheduledEntryRepository;

    private ScheduledEntryConverter scheduledEntryConverter;

    @Autowired
    public ScheduledEntryService(
    		ScheduledEntryValidator scheduledEntryValidator,
			ScheduledEntryRepository scheduledEntryRepository, 
			ScheduledEntryConverter scheduledEntryConverter
	) {
		this.scheduledEntryValidator = scheduledEntryValidator;
		this.scheduledEntryRepository = scheduledEntryRepository;
		this.scheduledEntryConverter = scheduledEntryConverter;
	}

	@Transactional
    public ScheduledEntryJson scheduleEntry(ScheduledEntryJson scheduledEntry, EntryType type) {
        scheduledEntryValidator.validate(scheduledEntry);
        scheduledEntry.getEntry().setType(type);
        return scheduledEntryRepository.save(scheduledEntryConverter.convert(scheduledEntry)).toJson();
    }

}
