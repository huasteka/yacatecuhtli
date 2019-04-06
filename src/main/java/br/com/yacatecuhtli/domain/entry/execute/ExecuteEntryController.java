package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.core.controller.AbstractRestController;
import br.com.yacatecuhtli.core.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecuteEntryController extends AbstractRestController {

	private ExecuteEntryService executionService;

    @Autowired
    public ExecuteEntryController(ExecuteEntryService executionService) {
		this.executionService = executionService;
	}

	@PostMapping("/api/entries/{entryId}/execute")
    public ResponseEntity<JsonResponse> execute(@PathVariable Integer entryId, @RequestBody EntryExecutionJson entryExecution) {
        entryExecution.setEntryId(entryId);
        return withJson(executionService.executeEntry(entryExecution));
    }

}
