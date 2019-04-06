package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.base.Sequence;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.core.SystemTime;
import br.com.yacatecuhtli.domain.entry.execute.EntryExecutionJson;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExecuteEntryTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_EXECUTED_ENTRY_TEMPLATE = "valid";

    @Override
    public void load() {
        Sequence<BigDecimal> bigDecimalSequence = () -> new BigDecimal(FAKER.number().randomDouble(2, 0, 5000)).setScale(2, RoundingMode.HALF_EVEN);

        Rule jsonRule = new Rule();
        jsonRule.add("addition", jsonRule.sequence(bigDecimalSequence));
        jsonRule.add("discount", jsonRule.sequence(bigDecimalSequence));
        jsonRule.add("executeAt", SystemTime.INSTANCE.getNow());
        Fixture.of(EntryExecutionJson.class).addTemplate(VALID_EXECUTED_ENTRY_TEMPLATE, jsonRule);
    }

}
