package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.controller.AbstractRestController;
import br.com.yacatecuhtli.core.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReversedEntryController extends AbstractRestController {

    @Autowired
    private ReversedEntryService revertService;

    @DeleteMapping("/api/entries/reverse/{entryId}")
    public ResponseEntity<JsonResponse> reverse(@PathVariable Integer entryId) {
        revertService.reverse(EntryReversalJson.builder().entryId(entryId).build());
        return withJson(HttpStatus.NO_CONTENT);
    }

}
