package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.transfer.TransferredEntry;
import br.com.yacatecuhtli.domain.entry.transfer.TransferredEntryJson;

import java.util.concurrent.TimeUnit;

public class TransferredEntryTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_TRANSFERRED_ENTRY_TEMPLATE = "valid";

    @Override
    public void load() {
        Rule baseRule = new Rule();
        baseRule.add("transferredAt", baseRule.sequence(() -> FAKER.date().past(30, TimeUnit.DAYS)));

        Rule entityRule = new Rule();
        entityRule.add("source", entityRule.one(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        entityRule.add("target", entityRule.one(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        Fixture.of(TransferredEntry.class).addTemplate(VALID_TRANSFERRED_ENTRY_TEMPLATE, new Rule(baseRule, entityRule));

        Rule jsonRule = new Rule();
        jsonRule.add("source", jsonRule.one(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        jsonRule.add("target", jsonRule.one(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        Fixture.of(TransferredEntryJson.class).addTemplate(VALID_TRANSFERRED_ENTRY_TEMPLATE, new Rule(baseRule, jsonRule));
    }

}
