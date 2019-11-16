package com.xmh.aisell.repository;

import com.github.wenhao.jpa.Specifications;
import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.query.EmployeeQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void testFindAll() throws Exception {
        List<Employee> list = employeeRepository.findAll();
        list.forEach(e -> System.out.println(e));
    }

    @Test
    public void testFindOne() throws Exception {
        Employee employee = employeeRepository.findOne(12L);
        System.out.println(employee);
    }

    //employeeRepository.save提供了添加和修改的功能
    @Test
    public void testSave() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("勇勇");
        employee.setAge(21);
        employee.setPassword("123456");
        employeeRepository.save(employee);

    }

    @Test
    public void testUpdate() throws Exception {
        /*Employee employee = new Employee();
        employee.setUsername("明明");
        employee.setAge(23);
        employee.setId(274L);
        employeeRepository.save(employee);*/
        Employee employee = employeeRepository.findOne(274L);
        employee.setUsername("勇勇");
        employeeRepository.save(employee);
    }


    @Test
    public void testDelete() throws Exception {
        /*Employee employee = employeeRepository.findOne(274L);
        employeeRepository.delete(employee);*/
        employeeRepository.delete(274L);
    }

    //List<T> findAll(Sort var1); 可以通过传入Sort对象进行排序查询
    //Sort(Sort.Direction direction, String... properties)
    //         升序降序方向               查询条件属性
    //         ASC升 DESC降
    @Test
    public void testSort() throws Exception {
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        List<Employee> list = employeeRepository.findAll(sort);
        list.forEach(e -> System.out.println(e));
    }

    //Page<T> findAll(Pageable var1);  可以通过传入Pageable对象进行分页查询
    //Pageable是接口，创建它的实现类
    //public PageRequest(int page, int size)
    //public PageRequest(int page, int size, Direction direction, String... properties)
    @Test
    public void testPage() throws Exception {
        //PageRequest引入了Sort
        Pageable pageable = new PageRequest(0,10,Sort.Direction.ASC,"id");
        //PageRequest
        Page<Employee> page = employeeRepository.findAll(pageable);
        //page extends Slice<T> page泛化Slice ，Slice extends Iterable<T> Slice泛化了可迭代
        //使得page对象可以直接foreach
        page.forEach(e -> System.out.println(e));
    }

    /**
     * 名称规则查询
     * @throws Exception
     */
    @Test
    public void testMethodName01() throws Exception{
        System.out.println(employeeRepository.findByUsername("admin"));
    }
    @Test
    public void testMethodName02() throws Exception{
        employeeRepository.findByUsernameLike("%1%").forEach(e-> System.out.println(e));
    }
    @Test
    public void testMethodName03() throws Exception{
        employeeRepository.findByUsernameLikeAndEmailLike("%1%","%2%")
                .forEach(e-> System.out.println(e));
    }

    /**
     *  使用JpaSpecificationExecutor完成查询，不写sql，通过面向对象的方式完成
     *  1.需要EmployeeRepository接口去继承JpaSpecificationExecutor
     *  2.List<T> findAll(Specification<T> var1);
     *  3.根据自己的需求去写实现类实现接口方法
     *  4.由于每次需求都不同所以用匿名内部类实现
     *  5.Root<Employee> root
     *    查询哪个表（定位到表和字段-> 用于拿到表中的字段）
     *    可以查询和操作的实体的根
     *    Root接口：代表Criteria查询的根对象，Criteria查询的查询根定义了实体类型，能为将来导航获得想要的结果，它与SQL查询中的FROM子句类似
     *    Root<Employee> 相当于 from Employee
     *    Root<Product> 相当于  from Product
     *  6.CriteriaQuery<?> criteriaQuery
     *    代表一个specific的顶层查询对象
     *    包含查询的各个部分,比如select,from,where,group by ,order by 等
     *    简单理解 就是它提供了查询ROOT的方法(where,select,having)
     *    查询哪些字段，排序是什么(主要是把多个查询的条件连系起来)
     *  7.CriteriaBuilder criteriaBuilder
     *    用来构建CriteriaQuery的构建器对象(相当于条件或者说条件组合)
     *    构造好后以Predicate的形式返回
     *    字段之间是什么关系，如何生成一个查询条件，每一个查询条件都是什么方式
     *    主要判断关系（和这个字段是相等，大于，小于like等）
     *  8.返回的表达式 Predicate（Expression）
     *    单独每一条查询条件的详细描述 整个 where xxx=xx and yyy=yy ...
     * */

     @Test
     public void testJpaSpecificationExecutor() throws Exception {
         //如果需要添加分页或排序就像之前一样在findAll添加Pageable、Sort对象的条件
         List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
             @Override
             public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                 //1.拿到Employee中的username字段
                 Path usernamePath = root.get("username");
                 //2.加上字段的判断关系
                 // 参数1:字段(表达式)  2.值
                 Predicate p1 = cb.like(usernamePath, "%1%");
                 //返回表达式
                 return p1;
             }
         });
         list.forEach(e-> System.out.println(e));
     }

     //使用插件使JpaSpecificationExecutor查询更加简单
     @Test
     public void testJpaSpec01() throws Exception {
         Specification<Employee> spec = Specifications.<Employee>and()  //and条件/or条件
                 //返回参数都相同，查询条件可以直接在后面调方法 .like  .gt   .in
                 .like("username", "%1%")
                 //方法调用结束不要忘了 .build() 才能正确的创建 Specification 对象
                 .build();
         List<Employee> list = employeeRepository.findAll(spec);
         list.forEach(e-> System.out.println(e));
     }

    //jpa-spec
    //完成咱们的简单查询 username like ? + 分页 + 排序
    @Test
    public void testJpaSpec03() throws Exception{
        //创建分页对象
        //PageRequest(int page, int size, Direction direction, String... properties)
        Pageable pageable = new PageRequest(0,10,Sort.Direction.DESC,"age");
        //规则对象(查询条件)
        Specification<Employee> spec = Specifications.<Employee>and()   //and条件/or条件
                //返回参数都相同，查询条件可以直接在后面调方法 .like  .gt   .in
                .like("username", "%1%")
                //方法调用结束不要忘了 .build() 才能正确的创建 Specification 对象
                .build();
        //功能执行  给findAll方法添加查询条件与分页排序属性
        Page<Employee> page = employeeRepository.findAll(spec, pageable);

        page.forEach(e-> System.out.println(e));
    }

    /**
     *
     * 查询条件不应该在测试方法里写
     * 应该写在实体查询里面条件与属性也不能写死,需要按需求向前台获取
     * 用Query抽离查询条件，数据从前台传过来直接获取
     * 就使得测试语句变得简单
     * */
    @Test
    public void testJpaSpec04() throws Exception {
        //此处模拟前台数据
        EmployeeQuery query = new EmployeeQuery();
        //findAll
        query.setPageSize(10);
        query.setCurrentPage(2);
        query.setOrderName("age");
        query.setOrderType(true);
        //findOne
        /*
        query.setUsername("勇勇");
        query.setAge(21);
        */
        //使用query createSort获得排序对象
        Sort sort = query.createSort();
        //PageRequest(int page, int size, Sort sort) 获取分页对象
        PageRequest request = new PageRequest(query.getPageSize(), query.getCurrentPage(), sort);
        //使用query createSpec方法创建查询规则
        Specification<Employee> spec = query.createSpec();
        //Page<T> findAll(Specification<T> var1, Pageable var2);
        Page<Employee> page = employeeRepository.findAll(spec, request);
        page.forEach(e-> System.out.println(e));

    }
}
