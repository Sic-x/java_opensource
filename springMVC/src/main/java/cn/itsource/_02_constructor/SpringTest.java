package cn.itsource._02_constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringTest {

    @Autowired
    private MyBean myBean;


    @Test
    public void test() throws Exception {
        System.out.println(myBean);
    }

}
