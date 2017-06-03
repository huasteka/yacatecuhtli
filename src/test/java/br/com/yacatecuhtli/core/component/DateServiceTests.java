package br.com.yacatecuhtli.core.component;

import br.com.yacatecuhtli.core.AbstractCoreTests;
import br.com.yacatecuhtli.core.service.DateService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;

@RunWith(MockitoJUnitRunner.class)
public class DateServiceTests extends AbstractCoreTests {

    @Spy
    private DateService dateService;

    @Test
    public void shouldCreateDate() {
        Date now = new Date(System.currentTimeMillis());
        Assert.assertThat(truncateMilliseconds(dateService.getNow()), Matchers.equalTo(truncateMilliseconds(now)));
    }

    @Test
    public void shouldAddSecondsToDate() {
        Date now = new Date(System.currentTimeMillis());
        Date oneHourAhead = applyToDate(now, (calendar) -> calendar.add(Calendar.SECOND, 3600));
        Assert.assertThat(dateService.addSecondsToDate(now, 3600), Matchers.equalTo(oneHourAhead));
    }

    private Date applyToDate(Date object, Consumer<Calendar> apply) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(object);
        apply.accept(calendar);
        return calendar.getTime();
    }

    private Date truncateMilliseconds(Date object) {
        return applyToDate(object, (calendar) -> calendar.set(Calendar.MILLISECOND, 0));
    }

}
