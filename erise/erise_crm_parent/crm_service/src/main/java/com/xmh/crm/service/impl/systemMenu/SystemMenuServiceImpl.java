package com.xmh.crm.service.impl.systemMenu;

import com.xmh.basic.service.impl.BaseServiceImpl;
import com.xmh.crm.domain.employee.Employee;
import com.xmh.crm.domain.systemMenu.SystemMenu;
import com.xmh.crm.mapper.systemMenu.SystemMenuMapper;
import com.xmh.crm.service.employee.IEmployeeService;
import com.xmh.crm.service.systemMenu.ISystemMenuService;
import com.xmh.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SystemMenuServiceImpl extends BaseServiceImpl<SystemMenu> implements ISystemMenuService {

    @Autowired
    SystemMenuMapper systemMenuMapper;
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public StringBuilder findAllByPermission(String type) {
        List<SystemMenu> list;
        if("t".equals(type)) {
            list = systemMenuMapper.findAll();
        }
        else {
            Employee user = (Employee)UserContext.getUser();
            Employee employee= employeeService.findOne(user.getId());
            list = systemMenuMapper.tenantFindAll(employee.getDept().getId());
        }

        HashSet<SystemMenu> children = new HashSet<>();
        HashSet<SystemMenu> parent = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        list.stream().filter(p -> p.getParent() != null).forEach(p->
                children.add(p)
        );

        list.stream().filter(p -> p.getParent() == null).forEach(p->
                parent.add(p)
        );
        builder.append("[");
        for (SystemMenu s:parent) {
            builder.append("{path: '").append(s.getSn()).append("',component:`./views/").append(s.getUrl()).append(".vue`,name: '").append(s.getName()).append("',iconCls: '").append(s.getIcon()).append("',children: [");
            for (SystemMenu c:children) {
                if(c.getParent().getName().equals(s.getName()))
                    builder.append("{ path: '").append(c.getSn()).append("', component:`./views/nav1/").append(c.getUrl()).append(".vue`, name: '").append(c.getName()).append("',  },");
            }
            builder.append("]},");
        }
        builder.append("]");
        return builder;
    }
}
