package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.controller.AbstractRestController;
import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.domain.entry.EntryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedules")
public class ScheduledEntryController extends AbstractRestController {

    private ScheduledEntryService scheduleService;

    @Autowired
    public ScheduledEntryController(ScheduledEntryService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@PostMapping("/deposit")
    public ResponseEntity<JsonResponse> scheduleDeposit(@RequestBody ScheduledEntryJson entry) {
        return withJson(scheduleService.scheduleEntry(entry, EntryType.DEPOSIT), HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<JsonResponse> scheduleWithdraw(@RequestBody ScheduledEntryJson entry) {
        return withJson(scheduleService.scheduleEntry(entry, EntryType.WITHDRAW), HttpStatus.CREATED);
    }

}
