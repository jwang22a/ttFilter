package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.UserDao;

@Controller
@RequestMapping("/user")
public class UserController {
	//退出
	@RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
    	//List<Items> itemList = itemsService.findItemsAll();
		//String aa=request.getParameter("txtName");
		ModelAndView modelAndView = new ModelAndView();
		//将数据放入model中可以返回给页面
		//指定页面的位置
		UserDao user = new UserDao();
		List list = user.getUserList();
		//modelAndView.addObject("list", list);
		modelAndView.setViewName("logon");
		return modelAndView;
    }
}
