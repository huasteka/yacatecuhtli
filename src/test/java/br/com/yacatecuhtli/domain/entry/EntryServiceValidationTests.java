package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeJson;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntryServiceValidationTests extends AbstractEntryServiceTests {

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveIfGrossValueIsBlank() {
        EntryJson payload = createValidEntry();
        payload.setGrossValue(null);
        entryService.deposit(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveIfGrossValueIsLesserThanZero() {
        EntryJson payload = createValidEntry();
        payload.setGrossValue(payload.getGrossValue().negate());
        entryService.deposit(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveIfPaymentTypeIsBlank() {
        EntryJson payload = createValidEntry();
        payload.setPaymentType(null);
        entryService.deposit(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveIfPaymentTypeIsUnavailable() {
        PaymentTypeJson unavailable = createObject(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        EntryJson payload = createValidEntry();
        unavailable.setId(payload.getPaymentType().getId() + 10);
        payload.setPaymentType(unavailable);
        entryService.deposit(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveIfAccountIsBlank() {
        EntryJson payload = createValidEntry();
        payload.setAccount(null);
        entryService.deposit(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveIfAccountIsUnavailable() {
        AccountJson unavailable = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        EntryJson payload = createValidEntry();
        unavailable.setId(payload.getAccount().getId() + 10);
        payload.setAccount(unavailable);
        entryService.deposit(payload);
    }

    private EntryJson createValidEntry() {
        AccountJson operationAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE).toJson();
        PaymentTypeJson operationPaymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE).toJson();

        EntryJson payload = createObject(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setAccount(operationAccount);
        payload.setPaymentType(operationPaymentType);
        return payload;
    }

}
