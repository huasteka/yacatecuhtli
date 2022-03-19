package br.com.yacatecuhtli.domain.entry.revert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.yacatecuhtli.core.controller.AbstractRestController;
import br.com.yacatecuhtli.core.json.JsonResponse;

@RestController
public class ReversedEntryController extends AbstractRestController {

    private ReversedEntryService revertService;

    @Autowired
    public ReversedEntryController(ReversedEntryService revertService) {
		this.revertService = revertService;
	}

	@DeleteMapping("/api/entries/{entryId}/reverse")
    public ResponseEntity<JsonResponse> reverse(@PathVariable Integer entryId) {
        revertService.reverse(EntryReversalJson.builder().entryId(entryId).build());
        return withJson(HttpStatus.NO_CONTENT);
    }

}
