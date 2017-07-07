package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.controller.AbstractRestController;
import br.com.yacatecuhtli.core.controller.RequestPagination;
import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.domain.entry.revert.EntryReversalJson;
import br.com.yacatecuhtli.domain.entry.revert.ReversedEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entries")
public class EntryController extends AbstractRestController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ReversedEntryService revertService;

    @PostMapping("/deposit")
    public ResponseEntity<JsonResponse> deposit(@RequestBody EntryJson entry) {
        return withJson(entryService.deposit(entry), HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<JsonResponse> withdraw(@RequestBody EntryJson entry) {
        return withJson(entryService.withdraw(entry), HttpStatus.CREATED);
    }

    @DeleteMapping("/reverse/{entryId}")
    public ResponseEntity<JsonResponse> reverse(@PathVariable Integer entryId) {
        revertService.reverse(EntryReversalJson.builder().entryId(entryId).build());
        return withJson(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<JsonResponse> findByAccount(@PathVariable Integer accountId, RequestPagination pagination) {
        return withJson(entryService.findByAccount(accountId, new PageRequest(pagination.getCurrentPage(), pagination.getPageSize())));
    }

}
