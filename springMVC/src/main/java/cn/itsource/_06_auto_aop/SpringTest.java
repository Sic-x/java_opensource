package cn.itsource._06_auto_aop;



import cn.itsource._06_auto_aop.service.IUserService;
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
