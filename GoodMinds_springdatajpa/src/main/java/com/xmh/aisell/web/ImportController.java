package com.xmh.aisell.web;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.xmh.aisell.common.EmployeeExcelVerifyHandler;
import com.xmh.aisell.domain.Department;
import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.service.IDepartmentService;
import com.xmh.aisell.service.IEmployeeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController{

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private EmployeeExcelVerifyHandler employeeExcelVerifyHandler;

    @RequestMapping("/index")
    public String index(){
        return "import";
    }

    /**
     * @param empFile 必须与前台传过来的文件名一致
     * @return
     */
    @RequestMapping("/employeeXlsx")
    public String employeeXlsx(MultipartFile empFile, HttpServletResponse response) throws Exception {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setNeedVerfiy(true);
        params.setVerifyHandler(employeeExcelVerifyHandler);


        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(empFile.getInputStream(), Employee.class, params);
        List<Employee> list = result.getList();
        list.forEach(e->{
            e.setPassword("123");
            Department dept = departmentService.findByName(e.getDepartment().getName());
            e.setDepartment(dept);
            employeeService.save(e);
        });

        if(result.isVerfiyFail()){
            Workbook failWorkbook = result.getFailWorkbook();

            //把这个文件导出
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=error.xlsx"); //告诉浏览下载的是一个附件，名字叫做error.xlsx
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream outputStream = response.getOutputStream();
            failWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }


        /*List<Employee> list = ExcelImportUtil.importExcel(empFile.getInputStream(), Employee.class, params);
        list.forEach(e->{
            e.setPassword("123");

            service.save(e);
        });*/
        return "import";
    }
    @RequestMapping("/template")
    public String template(HttpServletResponse response) throws IOException {
        FileInputStream in = new FileInputStream("D:\\itsource\\作业\\IEDAWorkSpace\\_2019_10_10_aisell_springdatajpa\\src\\main\\webapp\\template.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
        response.setHeader("Content-disposition", "attachment;filename=template.xlsx"); //告诉浏览下载的是一个附件，名字叫做error.xlsx
        response.setHeader("Pragma", "No-cache");//设置不要缓存
        int i = in.available();
        byte[] data = new byte[i];
        in.read(data);
        OutputStream out = response.getOutputStream();
        out.write(data);
        out.flush();
        out.close();
        in.close();
        return "import";
    }
}
