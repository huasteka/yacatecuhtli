package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.revert.ReversedEntry;
import br.com.yacatecuhtli.domain.entry.revert.ReversedEntryJson;

public class ReversedEntryTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_REVERSED_ENTRY_TEMPLATE = "valid";

    @Override
    public void load() {
        Rule entityRule = new Rule();
        entityRule.add("reverse", entityRule.one(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        entityRule.add("reversed", entityRule.one(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        Fixture.of(ReversedEntry.class).addTemplate(VALID_REVERSED_ENTRY_TEMPLATE, entityRule);

        Rule jsonRule = new Rule();
        jsonRule.add("reverse", jsonRule.one(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        jsonRule.add("reversed", jsonRule.one(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        Fixture.of(ReversedEntryJson.class).addTemplate(VALID_REVERSED_ENTRY_TEMPLATE, jsonRule);
    }

}
