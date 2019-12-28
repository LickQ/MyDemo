package com.itheima.springmvc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.springmvc.dao.CategoryMapper;
import com.itheima.springmvc.pojo.Category;
import com.itheima.springmvc.pojo.Expert;
import com.itheima.springmvc.pojo.History;
import com.itheima.springmvc.pojo.Text;
import com.itheima.springmvc.service.CategoryService;
import com.itheima.springmvc.service.ExpertService;
import com.itheima.springmvc.service.HistoryService;
import com.itheima.springmvc.service.TextService;
@Controller
public class ExpertController {
	@Autowired
	private ExpertService expertService;
	@Autowired
	private TextService textService;
	@Autowired
	private HistoryService historyservice;
	@Autowired
	private CategoryMapper categorymapper;
	// 前往专家注册界面
		@RequestMapping(value = "/Expregister.action", method = RequestMethod.GET)
		public String Expertregister() {
			return "Expregister";
		}

		@RequestMapping(value = "/Expregister.action", method = RequestMethod.POST)

		public String ExpRES(Expert expert) {
			// 添加用户
			expertService.register(expert);

			return "redirect:/index.action";

		}

		// 去专家登陆的页面
		@RequestMapping(value = "/Explogin.action", method = RequestMethod.GET)
		public String Expertlogin() {
			return "Explogin";
		}

		@RequestMapping(value = "/Explogin.action", method = RequestMethod.POST)
		public String Expertlogin(String expertname,String password,HttpServletRequest request,HttpServletResponse response) {
			HttpSession session = request.getSession();
			Expert expert=new Expert();
			expert.setExpertname(expertname);
			expert.setPassword(password);
			session.setAttribute("expert", expert);
			session.setAttribute("expertname", expertname);
			if (expertService.login(expertname, password) == null) {
				return "error";
			}
			return "redirect:/ExpertList.action";

		}
		//注销
		@RequestMapping(value = "/elogout.action")
		public String elogout(HttpServletRequest request,HttpServletResponse response) {
			HttpSession session = request.getSession();
			session.removeAttribute("expert");
			return "redirect:/index.action";

		}
		
		//专家个人界面
			@RequestMapping(value = "/ExpertList.action",produces="text/html;charset=UTF-8")
			public ModelAndView ExpertList(HttpSession httpSession) {
				ModelAndView mav = new ModelAndView();
				String expertname=(String) httpSession.getAttribute("expertname");
				// 数据
				String major=expertService.findByExpertName(expertname).getMajor();
				int cid=categorymapper.findCidByCName(major);
				//根据专家的专业和未审核的条件显示可审核的信息
				String checkmark="未审核";
				List<Text> textList = textService.findTextByCKandCid(checkmark,cid);
				mav.addObject("textList", textList);
				mav.setViewName("ExpertList");
				return mav;
			}
		//审核界面

			@RequestMapping(value = "/Experttext.action", produces="text/html;charset=UTF-8")
			public ModelAndView Experttext(Integer id, HttpServletRequest request, HttpServletResponse response,
					HttpSession session, Model model) throws IOException {
				// 防止不同的专家对同一个文章做并发操作
				Text texts = textService.selectTextById(id);
				ModelAndView mav = new ModelAndView();
				if(texts.getStatus()!=null&&texts.getStatus()==1) {
					mav.setViewName("error");
					return mav;
				}
				else {
					texts.setStatus(1);
					textService.updateTextById(texts);
				}
				
				// 数据
				mav.addObject("text", texts);
				mav.setViewName("Approval");
				return mav;
			}
			
			// 审核
			@RequestMapping(value = "/updatecheckMark.action",produces="text/html;charset=GBK")
		  	public String updatecheckMark(Integer tid,String checkMark,HttpServletResponse response)throws Exception{
				// 修改
				Text texts = textService.selectTextById(tid);
				if(!texts.getCheckMark().equals("未审核")) {
					return "error";
				}
				Text text=new Text();
				text.setCheckMark(checkMark);
				text.setTid(tid);
				text.setStatus(0);
				textService.updatecheckMark(text);
				History history=new History();
				history.setTname(texts.getTitle());
				history.setLatestTime(new Date());
				history.setState("已审核");
				historyservice.record(history);

		  		 return "redirect:/ExpertList.action";

			}
			//显示专家个人信息
			@RequestMapping(value = "/expperson.action")
			public String autperson(String name,HttpServletRequest request,HttpServletResponse response) {
				HttpSession session = request.getSession();
				String expertname=(String) session.getAttribute("expertname");
				Expert expperson=expertService.findByExpertName(expertname);
				if(expperson==null) {
					expperson=expertService.findByExpertName(name);
					String mark="1";
					session.setAttribute("mark", mark);
					
				}
				session.setAttribute("expperson", expperson);
				 return "expperson";
			}
			
			//修改专家个人信息
			@RequestMapping(value = "/updateexp.action")
			public String updateaut(Expert expert,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
				request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession();
				expertService.updateAuthorById(expert);
				String mark=(String)session.getAttribute("mark");
				 if(mark=="1") {
					 return "redirect:/expertlist.action";
				 };
				return "redirect:/ExpertList.action";
			}
			
}
