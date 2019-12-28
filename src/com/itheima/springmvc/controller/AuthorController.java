package com.itheima.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.springmvc.pojo.Author;
import com.itheima.springmvc.pojo.Expert;
import com.itheima.springmvc.pojo.Text;
import com.itheima.springmvc.service.AuthorService;
import com.itheima.springmvc.service.TextService;
@Controller
public class AuthorController {
	@Autowired
	private TextService textService;
	@Autowired
	private AuthorService authorService;
	// 前往作家注册界面
		@RequestMapping(value = "/Autregister.action", method = RequestMethod.GET)
		public String Autregister() {
			return "Autregister";
		}

		@RequestMapping(value = "/Autregister.action", method = RequestMethod.POST)

		public String AutRES(Author author) {
			// 添加用户
			authorService.register(author);

			return "redirect:/index.action";

		}

		// 去作家登陆的页面
		@RequestMapping(value = "/Autlogin.action", method = RequestMethod.GET)
		public String Autlogin() {
			return "Autlogin";
		}

		@RequestMapping(value = "/Autlogin.action", method = RequestMethod.POST)
		public String Autlogin(String authorname, String password,HttpServletRequest request,HttpServletResponse response) {
			HttpSession session = request.getSession();
			Author author=new Author();
			author.setAuthorname(authorname);
			author.setPassword(password);
			session.setAttribute("author", author);
			session.setAttribute("authorname", authorname);
			if (authorService.login(authorname, password) == null) {
				return"error";
			}
			 return "redirect:/AuthorList.action";
		}
		//注销
		@RequestMapping(value = "/alogout.action")
		public String alogout(HttpServletRequest request,HttpServletResponse response) {
			HttpSession session = request.getSession();
			session.removeAttribute("author");
			return "redirect:/index.action";

		}
	//作者个人界面
		@RequestMapping(value = "/AuthorList.action")
		public ModelAndView AuthorList(HttpSession httpSession,HttpServletRequest request,HttpServletResponse response) {
			ModelAndView mav = new ModelAndView();
			// 数据
			HttpSession session = request.getSession();
			String authorname=(String) session.getAttribute("authorname");
			List<Text> textList = textService.findTextByAuthorName(authorname);
			mav.addObject("authorname", authorname);
			mav.addObject("textList", textList);
			mav.setViewName("AuthorList");
			return mav;
		}
		//显示作家个人信息
		@RequestMapping(value = "/autperson.action")
		public String autperson(String name,HttpServletRequest request,HttpServletResponse response) {
			HttpSession session = request.getSession();
			String authorname=(String) session.getAttribute("authorname");
			Author autperson=authorService.findByAuthorName(authorname);
			if(autperson==null) {
				autperson=authorService.findByAuthorName(name);
				String mark2="2";
				session.setAttribute("mark2", mark2);

			}
			session.setAttribute("autperson", autperson);
			 return "autperson";
		}
		//修改作家个人信息
				@RequestMapping(value = "/updateaut.action")
				public String updateaut(Author author,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
					request.setCharacterEncoding("UTF-8");
					HttpSession session = request.getSession();
					
					System.out.println(author.getRname());
					authorService.updateAuthorById(author);
					String mark=(String)session.getAttribute("mark2");
					 if(mark=="2") {
						 return "redirect:/authorlist.action";
					 };
					return "success";
				}
}
