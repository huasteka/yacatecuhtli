package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.controller.AbstractRestController;
import br.com.yacatecuhtli.core.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController extends AbstractRestController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<JsonResponse> save(@RequestBody AccountJson accountJson) {
        return withJson(accountService.save(accountJson), HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<JsonResponse> update(@PathVariable Integer accountId, @RequestBody AccountJson accountJson) {
        accountService.update(accountId, accountJson);
        return withJson(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<JsonResponse> destroy(@PathVariable Integer accountId) {
        accountService.destroy(accountId);
        return withJson(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<JsonResponse> findOne(@PathVariable Integer accountId) {
        return withJson(accountService.findOne(accountId));
    }

    @GetMapping
    public ResponseEntity<JsonResponse> findAll() {
        return withJson(accountService.findAll());
    }

}
