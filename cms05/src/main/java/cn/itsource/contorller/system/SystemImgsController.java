package cn.itsource.contorller.system;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itsource.domain.City;
import cn.itsource.domain.Imgs;
import cn.itsource.service.ICityService;
import cn.itsource.service.IImgsService;
import cn.itsource.service.IJobsService;
import cn.itsource.util.PageBeanUtil;
import cn.itsource.util.UploadUtil;

@Controller
@RequestMapping("system/imgs")
public class SystemImgsController {
	
	@Autowired
	private IImgsService service;
	
	
	@RequestMapping("/list")
	public String list(Model model,Integer localPage,Integer fromAdd,Integer totalPage){

		if(fromAdd!=null)
			localPage = totalPage;

		PageBeanUtil<Imgs> pageBean = service.list(localPage);
		model.addAttribute("pageBean",pageBean);
		return "WEB-INF/system/main";
	}
	
	@RequestMapping("/delete")
	public String delete(Imgs imgs,HttpServletRequest req){
		service.delete(imgs.getImgid());
		
		String path = req.getServletContext().getRealPath("/upload");
		
		File file = new File(path, imgs.getStorename());
		if(file.exists())
			file.delete();
		return "forward:list";
	}
	
	@RequestMapping("/addPage")
	public String addPage(Model model,Integer totalPage){
		
		model.addAttribute("totalPage",totalPage);
		return "WEB-INF/system/main_add";
	}
	
	@RequestMapping("/add")
	public String add(Model model,Imgs imgs,HttpServletRequest req){
		String realPath = req.getServletContext().getRealPath("/uploadFile");
		File f1 = new File(realPath);
		if(!f1.exists())
			f1.mkdir();
		
		String fileName = UploadUtil.fileUpload(imgs.getFileImg(), req, realPath);
		
		imgs.setStorepath(realPath);//将图片上传的目录位置设置到实体中
		imgs.setStorename(fileName);//图片名称
		
		service.add(imgs);
		return "forward:list";
	}
	
	@RequestMapping("/updatePage")
	public String updatePage(Model model,Imgs imgs,Integer localPage){
		Imgs newImgs = service.findById(imgs);
		model.addAttribute("newJobs",newImgs);
		model.addAttribute("localPage",localPage);
		return "WEB-INF/system/main_edit";
	}
	
	@RequestMapping("/update")
	public String update(Model model,Imgs imgs,HttpServletRequest req){
		
		service.update(imgs);
		return "forward:list";
	}
	
}
