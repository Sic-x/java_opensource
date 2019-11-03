package cn.itsource._05_xmlaop;


import cn.itsource._05_xmlaop.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringTest {

    @Autowired
    private IUserService service;

    @Test
    public void test() throws Exception {
        service.save();
    }

}
