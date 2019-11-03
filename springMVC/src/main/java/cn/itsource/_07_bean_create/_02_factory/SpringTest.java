package cn.itsource._07_bean_create._02_factory;

import cn.itsource._07_bean_create._01_normal.MyBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringTest {

    @Autowired
    private EntityManagerFactory factory;


    @Test
    public void test() throws Exception {
        System.out.println(factory);
    }

}
