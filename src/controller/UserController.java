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
	//�˳�
	@RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
    	//List<Items> itemList = itemsService.findItemsAll();
		//String aa=request.getParameter("txtName");
		ModelAndView modelAndView = new ModelAndView();
		//�����ݷ���model�п��Է��ظ�ҳ��
		//ָ��ҳ���λ��
		UserDao user = new UserDao();
		List list = user.getUserList();
		//modelAndView.addObject("list", list);
		modelAndView.setViewName("logon");
		return modelAndView;
    }
}
