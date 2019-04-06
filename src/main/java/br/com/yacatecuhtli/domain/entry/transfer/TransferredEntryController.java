package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.controller.AbstractRestController;
import br.com.yacatecuhtli.core.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferredEntryController extends AbstractRestController {

    private TransferredEntryService transferService;

    @Autowired
    public TransferredEntryController(TransferredEntryService transferService) {
		this.transferService = transferService;
	}

	@PostMapping
    public ResponseEntity<JsonResponse> transfer(@RequestBody AccountTransferJson accountTransfer) {
        return withJson(transferService.transfer(accountTransfer), HttpStatus.CREATED);
    }

}
