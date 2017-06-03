package br.com.yacatecuhtli;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync(proxyTargetClass = true)
public class YacatecuhtliApplicationTests {

    @Test
    public void contextLoads() {
        // It should pass
    }

}
