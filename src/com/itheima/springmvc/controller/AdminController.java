package com.itheima.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itheima.springmvc.pojo.Admin;
import com.itheima.springmvc.pojo.Author;
import com.itheima.springmvc.pojo.Category;
import com.itheima.springmvc.pojo.Expert;
import com.itheima.springmvc.pojo.History;
import com.itheima.springmvc.pojo.Text;
import com.itheima.springmvc.service.AdminService;
import com.itheima.springmvc.service.AuthorService;
import com.itheima.springmvc.service.CategoryService;
import com.itheima.springmvc.service.ExpertService;
import com.itheima.springmvc.service.HistoryService;

@Controller
public class AdminController {
	@Autowired
	private  CategoryService categoryService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private ExpertService expertService;
	@Autowired
	private HistoryService historyService;
	// 管理员登陆的页面
			@RequestMapping(value = "/Admlogin.action", method = RequestMethod.GET)
			public String Autlogin() {
				return "Admlogin";
			}

			@RequestMapping(value = "/Admlogin.action", method = RequestMethod.POST)
			public String Autlogin(String adminname, String password,HttpServletRequest request,HttpServletResponse response) {
				Admin admin=new Admin();
				admin.setAdminname(adminname);
				admin.setPassword(password);
				HttpSession session=request.getSession();
				session.setAttribute("admin", admin);
				
				if (adminService.Adminlogin(adminname, password) == null) {
					return"error";
				}
				 return "redirect:/User.jsp";
			}
			@RequestMapping(value = "/categorylist.action")
			public String categorylist(HttpServletRequest request,HttpServletResponse response) {
				HttpSession session = request.getSession();
				List<Category> categorylist=categoryService.findAllCategory();
				session.setAttribute("categorylist", categorylist);
				 return "CatManage";

			}
			
			@RequestMapping(value = "/adlogout.action")
			public String alogout(HttpServletRequest request,HttpServletResponse response) {
				HttpSession session = request.getSession();
				session.removeAttribute("admin");
				return "redirect:/index.action";

			}
			// 删除
			@RequestMapping(value = "/deleteCategory.action")
			public String deleteCategory(Integer id) {
//		  	public String updateitem(Items items)throws Exception{
				// 删除
				categoryService.deleteCategoryById(id);
				

				return "redirect:/categorylist.action";
//		  		return "success";

			}
			//修改
			
			
			
			//添加种类
			@RequestMapping(value = "/AddCategory.action", method = RequestMethod.GET)
			public String AddText() {
				return "AddCategory";
			}

			@RequestMapping(value = "/AddCategory.action", method = RequestMethod.POST)

			public  String AddText(String cname,HttpServletRequest requset,HttpServletResponse response) {

				categoryService.addCategory(cname);
				return "redirect:/categorylist.action";

			}
			//查看所有作家信息
			@RequestMapping(value = "/authorlist.action")
			public String authorlist(HttpServletRequest request,HttpServletResponse response) {
				HttpSession session = request.getSession();
				List<Author> alist=authorService.findAuthor();
				session.setAttribute("alist", alist);
				 return "Alist";

			}
			//查看所有专家信息
			@RequestMapping(value = "/expertlist.action")
			public String expertlist(HttpServletRequest request,HttpServletResponse response) {
				HttpSession session = request.getSession();
				List<Expert> elist=expertService.findExpert();
				session.setAttribute("elist", elist);
				 return "Elist";

			}
			//查看历史信息
			@RequestMapping(value = "/historylist.action")
			public String historylist(HttpServletRequest request,HttpServletResponse response) {
				HttpSession session = request.getSession();
				List<History> hlist = historyService.findAllHistory();
				
				session.setAttribute("hlist", hlist);
				 return "Hlist";

			}
			//修改专家个人信息
//			@RequestMapping(value = "/updateE.action")
//			public String updateaut(String expertname,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
//				request.setCharacterEncoding("UTF-8");
//				expertService.findByExpertName(expertname);
//				return "redirect:/ExpertList.action";
//			}
			
			

}
