package cn.itsource.domian;

import cn.itsource.utils.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentTest {
    @Test
    public void test() throws Exception{
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("day04");
        factory.createEntityManager();
    }

    @Test
    public void test1() throws Exception{
        // 查询所有员工【查询实体类型】
        EntityManager manager = JPAUtil.getManager();
        // 1. 准备 JPQL
        String jpql = "select e from Employee e ";
        // 2. 创建 Query对象
        Query query = manager.createQuery(jpql);
        // 3. 执行查询
        List<Employee> list = query.getResultList();
        // 4. 遍历结果
        list.forEach( e ->{
            System.out.println(e);
        });
        manager.close();
    }

    @Test
    public void test2() throws Exception{
        // 查询所有员工的姓名和所属部门名称【查询特定属性】
        EntityManager manager = JPAUtil.getManager();
        // 1. 准备 JPQL
        String jpql = "select e.name, e.department.name from Employee e ";
        // 2. 创建 Query对象
        Query query = manager.createQuery(jpql);
        // 3. 执行查询 -- 结果是一个Object 数组
        List<Object[]> list = query.getResultList();
        // 4. 遍历结果
        list.forEach( e ->{
            System.out.println(Arrays.toString(e));
        });
        manager.close();
    }

    @Test
    public void test2_1() throws Exception{
        // 查询所有员工的姓名和所属部门名称【查询特定属性】
        EntityManager manager = JPAUtil.getManager();
        // 1. 准备 JPQL
        String jpql = "select new Employee(e.name, e.department.name) from Employee e ";
        // 2. 创建 Query对象
        Query query = manager.createQuery(jpql);
        // 3. 执行查询 --
        List<Employee> list = query.getResultList();
        // 4. 遍历结果
        list.forEach( e ->{
            System.out.println(e.getName()+":"+e.getDepartment().getName());
        });
        manager.close();
    }

    @Test
    public void test2_2() throws Exception{
        // 查询所有员工的姓名和所属部门名称【查询特定属性,成都和广州】
        EntityManager manager = JPAUtil.getManager();
        // 1. 准备 JPQL
        String jpql = "select e from Employee e  where e.department.city = ?1 or e.department.city = ?2 ";
        // 2. 创建 Query对象
        Query query = manager.createQuery(jpql);
        query.setParameter(1,"成都");
        query.setParameter(2,"广州");
        // 3. 执行查询 --
        List<Employee> list = query.getResultList();
        // 4. 遍历结果
        list.forEach( e ->{
            System.out.println(e.getName()+":"+e.getDepartment().getCity());
        });
        manager.close();
    }

    @Test
    public void test2_3() throws Exception{
        // 查询所有员工的姓名和所属部门名称【查询特定属性,月薪排序】
        EntityManager manager = JPAUtil.getManager();
        // 1. 准备 JPQL
        String jpql = "select e from Employee e order by e.salary desc";
        // 2. 创建 Query对象
        Query query = manager.createQuery(jpql);
        // 3. 执行查询 --
        List<Employee> list = query.getResultList();
        // 4. 遍历结果
        list.forEach( e ->{
            System.out.println(e);
        });
        manager.close();
    }
    @Test
    public void test2_4() throws Exception{
        // 查询所有员工的姓名和所属部门名称【按照部门编号排序】
        EntityManager manager = JPAUtil.getManager();
        // 1. 准备 JPQL
        String jpql = "select e from Employee e order by e.department.id desc";
        // 2. 创建 Query对象
        Query query = manager.createQuery(jpql);
        // 3. 执行查询 --
        List<Employee> list = query.getResultList();
        // 4. 遍历结果
        list.forEach( e ->{
            System.out.println(e);
        });
        manager.close();
    }



}