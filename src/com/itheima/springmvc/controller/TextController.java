package com.itheima.springmvc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.itheima.springmvc.pojo.Backup;
import com.itheima.springmvc.pojo.Category;
import com.itheima.springmvc.pojo.History;
import com.itheima.springmvc.pojo.PageBean;
import com.itheima.springmvc.pojo.Text;
import com.itheima.springmvc.service.BackupService;
import com.itheima.springmvc.service.CategoryService;
import com.itheima.springmvc.service.HistoryService;
import com.itheima.springmvc.service.TextService;
@Controller
public class TextController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TextService textService;
	@Autowired
	private HistoryService historyservice;
	@Autowired
	private BackupService backupservice;

	@RequestMapping(value = "/index.action")
	public ModelAndView index(HttpServletRequest requset,HttpServletResponse response) throws IOException {
		// 从Mysql中查询
		List<Text> list = textService.findTextByCheckMark("优秀");
//		List<Text> list=textService.findTextByAuthorName(authorname);
		ModelAndView mav = new ModelAndView();
		// 数据
		List<Category> categorylist=categoryService.findAllCategory();
		response.setContentType("text/html;charset=UTF-8");
		Gson gson=new Gson();
		String json=gson.toJson(categorylist);
		response.getWriter().write(json);
		
		System.out.println(json);
//		mav.addObject("categortlist",categorylist);
		mav.addObject("textLists", list);
		mav.setViewName("index");
		return mav;
	}
	//json数据交互
	@ResponseBody
	@RequestMapping(value = "/category.action")
	public List<Category> category(HttpServletRequest requset,HttpServletResponse response)throws IOException {
		List<Category> categorylist=categoryService.findAllCategory();
		return categorylist;
	}
	
	
//	// 显示文章所有内容
//
//		@RequestMapping(value = "/textList.action")
//		public ModelAndView textList() {
//			// 从Mysql中查询
//			List<Text> list = textService.findTextByCheckMark("优秀");
////			List<Text> list=textService.findTextByAuthorName(authorname);
//			ModelAndView mav = new ModelAndView();
//			// 数据
//			mav.addObject("textList", list);
//			mav.setViewName("textList");
//			return mav;
//		}

		// 去修改页面 入参 id
		@RequestMapping(value = "/textEdit.action")
//	  	public ModelAndView toEdit(@RequestParam(value = "id",required = false,defaultValue = "1") Integer idaaq,
		public ModelAndView toEdit(Integer tid, HttpServletRequest request, HttpServletResponse response,
				HttpSession session, Model model) {

			Text texts = textService.selectTextById(tid);
			ModelAndView mav = new ModelAndView();
			// 数据
			mav.addObject("text", texts);
			mav.setViewName("EditText");
			return mav;
		}

		// 提交修改页面 入参 为 Text对象
		@RequestMapping(value = "/updatetext.action")
//		public ModelAndView updateitem(Text texts) {
	  	public String updateitem(Text texts)throws Exception{
			// 修改
			texts.setStatus(0);
			textService.updateTextById(texts);

//			ModelAndView mav = new ModelAndView();
//			mav.setViewName("AuthorList");
//			return mav;
	  		 return "redirect:/AuthorList.action";

		}
		//文章详细界面
		@RequestMapping(value = "/findit.action")
//		public ModelAndView toEdit(@RequestParam(value = "id",required = false,defaultValue = "1") Integer idaaq,
	public ModelAndView findit(Integer id) {

		Text texts = textService.selectTextById(id);
		ModelAndView mav = new ModelAndView();
		// 数据
		mav.addObject("text", texts);
		mav.setViewName("findit");
		return mav;
	}
		

		// 前往投稿
		@RequestMapping(value = "/AddText.action", method = RequestMethod.GET)
		public String AddText() {
			return "AddText";
		}

		@RequestMapping(value = "/AddText.action", method = RequestMethod.POST)

		public  String AddText(Text text,HttpServletRequest requset,HttpServletResponse response) {
			HttpSession session=requset.getSession();
			String authorname =(String) session.getAttribute("authorname");
			text.setAuthorname(authorname);
			textService.addText(text);

			return "redirect:/AuthorList.action";

		}

		// 删除
		@RequestMapping(value = "/deleteText.action")
		public String deleteText(Integer tid) {
//	  	public String updateitem(Items items)throws Exception{
			// 删除
			//记录历史
			Text text=new Text();
			text=textService.selectTextById(tid);
			History history=new History();
			history.setTname(text.getTitle());
			history.setLatestTime(new Date());
			history.setState("删除");
			historyservice.record(history);
			//删除
			textService.deleteById(tid);
			//备份
			Backup backup =new Backup();
			backup.setAname(text.getAuthorname());
			backup.setCheckMark(text.getCheckMark());
			backup.setDetail(text.getDetail());
			backup.setEid(text.getEid());
			backup.setSummary(text.getSummary());
			backup.setTname(text.getTitle());
			
			backupservice.AddBackup(backup);

			return "redirect:/AuthorList.action";
//	  		return "success";

		}

		// 模糊查询
		@RequestMapping(value = "/findTextByName.action", method = RequestMethod.GET)
		public String findTextByTextname() {
			return "findlist";
		}

		@RequestMapping(value = "/findTextByName.action", method = RequestMethod.POST)
		public ModelAndView findTextByTextname(String title, HttpSession httpSession) {

			ModelAndView mav = new ModelAndView();

			List<Text> texts = textService.findTextByTextname(title);

			// 数据
			mav.addObject("textslist", texts);
			mav.setViewName("findtextbyname");
			return mav;

		}
		//分类查询
		@RequestMapping(value = "/findTextByCid.action")
	  	public String findTextByCid(Integer cid ,HttpServletRequest request,HttpServletResponse response)throws Exception{
			// 修改
			//获得cid

			String currentPageStr = request.getParameter("currentPage");
			if(currentPageStr==null) currentPageStr="1";
			int currentPage = Integer.parseInt(currentPageStr);
			int currentCount = 4;

			PageBean pageBean = textService.findProductListByCid(cid, currentPage, currentCount);

			request.setAttribute("pageBean", pageBean);
			request.setAttribute("cid", cid);
			
			// 数据
			return "findlist";

		}
		
		
		
		
}
