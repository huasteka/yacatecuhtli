package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.controller.AbstractRestController;
import br.com.yacatecuhtli.core.controller.RequestPagination;
import br.com.yacatecuhtli.core.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entries")
public class EntryController extends AbstractRestController {

    private EntryService entryService;
    
    @Autowired
    public EntryController(EntryService entryService) {
		this.entryService = entryService;
	}

	@PostMapping("/deposit")
    public ResponseEntity<JsonResponse> deposit(@RequestBody EntryJson entry) {
        return withJson(entryService.deposit(entry), HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<JsonResponse> withdraw(@RequestBody EntryJson entry) {
        return withJson(entryService.withdraw(entry), HttpStatus.CREATED);
    }

    @GetMapping("/{entryId}")
    public ResponseEntity<JsonResponse> findById(@PathVariable Integer entryId) {
        return withJson(entryService.findById(entryId));
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<JsonResponse> findByAccount(@PathVariable Integer accountId, RequestPagination pagination) {
        return withJson(entryService.findByAccount(accountId, new PageRequest(pagination.getCurrentPage(), pagination.getPageSize())));
    }

    @GetMapping("/search-code/{entryCode}")
    public ResponseEntity<JsonResponse> findByCode(@PathVariable String entryCode) {
        return withJson(entryService.findByCode(entryCode));
    }

}
