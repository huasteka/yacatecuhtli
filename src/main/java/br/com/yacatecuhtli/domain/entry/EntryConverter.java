package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.domain.account.AccountRepository;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.payment.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryConverter extends JsonConverter<EntryJson, Entry> {

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected PaymentTypeRepository paymentTypeRepository;

    @Autowired
    protected BudgetCategoryRepository budgetCategoryRepository;

    @Override
    public Entry convert(EntryJson source) {
        Entry entry = new Entry();
        update(source, entry);
        return entry;
    }

    @Override
    public void update(EntryJson source, Entry target) {
        target.setType(source.getType());
        target.setGrossValue(source.getGrossValue());
        target.setAddition(source.getAddition());
        target.setDiscount(source.getDiscount());
        target.setNetValue(source.getNetValue());
        target.setDescription(source.getDescription());
        target.setIssuedAt(source.getIssuedAt());
        target.setExecutedAt(source.getExecutedAt());
        target.setReversedAt(source.getReversedAt());
        updateRelationship(accountRepository, source.getAccount(), target::setAccount);
        updateRelationship(paymentTypeRepository, source.getPaymentType(), target::setPaymentType);
        updateRelationship(budgetCategoryRepository, source.getCategory(), target::setCategory);
    }

}
