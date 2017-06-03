package br.com.yacatecuhtli.core.component;

import br.com.yacatecuhtli.core.AbstractCoreTests;
import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.message.JsonErrorMessageHolder;
import br.com.yacatecuhtli.core.message.TestMessageCode;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class BusinessRuleExceptionTests extends AbstractCoreTests {

    @Test(expected = BusinessRuleException.class)
    public void shouldThrowExceptionIfListIsNotEmpty() {
        BusinessRuleException exception = new BusinessRuleException();
        exception.addMessage(TestMessageCode.TEST_MESSAGE_CODE);
        exception.addMessage(TestMessageCode.TEST_MESSAGE_CODE_2);
        Assert.assertThat(exception.getErrors().size(), Matchers.equalTo(2));
        exception.throwException();
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldThrowExceptionIfConditionIsTrue() {
        BusinessRuleException exception = new BusinessRuleException();
        exception.addMessage(() -> true, TestMessageCode.TEST_MESSAGE_CODE);
        Assert.assertThat(exception.getErrors().size(), Matchers.equalTo(1));
        exception.throwException();
    }

    @Test
    public void shouldThrowExceptionIfConditionIsFalse() {
        BusinessRuleException exception = new BusinessRuleException();
        exception.addMessage(() -> false, TestMessageCode.TEST_MESSAGE_CODE);
        Assert.assertThat(exception.getErrors(), Matchers.emptyCollectionOf(JsonErrorMessageHolder.class));
        exception.throwException();
    }

    @Test
    public void shouldNotThrowExceptionWhenListIsEmpty() {
        BusinessRuleException exception = new BusinessRuleException();
        Assert.assertThat(exception.getErrors(), Matchers.emptyCollectionOf(JsonErrorMessageHolder.class));
        exception.throwException();
    }

}
