package kollus.stmp.stmp.controller;

import kollus.stmp.stmp.component.ScheduleComponent;
import kollus.stmp.stmp.component.SendMailComponent;
import kollus.stmp.stmp.dao.DbCustomerEntity;
import kollus.stmp.stmp.dao.DbCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class SmtpController {

    @Autowired
    private SendMailComponent SendMailComponent;
    @Autowired
    private ScheduleComponent ScheduleComponent;
    @Autowired
    private DbCustomerRepository DbCustomerRepository;

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public ModelAndView index() throws Exception{
        List<DbCustomerEntity> items = DbCustomerRepository.selectCustomerInformation();
        System.out.println(items);
        for(DbCustomerEntity a : items){
            System.out.println(a.getCustomer_email());
        }

        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/tb"}, method = RequestMethod.GET)
    public ModelAndView hello(Model model, @RequestParam(value="name", required=false, defaultValue="") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("tables");
    }

    @RequestMapping(value = {"/smtp"}, method = RequestMethod.GET)
    public ModelAndView test() throws Exception{
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get smtp=======================");

       // SendMailComponent.getHTMLMailForm();
       // SendMailComponent.sendMailingSystem("");
       // List<DbtestEntity> items = dbtestRepository.findByRange();
      //  System.out.println(items.toString());
      //  SendMailComponent.test123();

        /*Enumeration params = request.getParameterNames();
        System.out.println("----------------------------");
        while (params.hasMoreElements()){
            String name = (String)params.nextElement();
            System.out.println(name + " : " +request.getParameter(name));
        }
        System.out.println("----------------------------");*/

        return new ModelAndView("test");
    }

    @ResponseBody
    @RequestMapping(value = {"/sendMail"}, method = RequestMethod.POST)
    public HashMap<String, Object> sendMail(HttpServletRequest request) throws Exception{
        System.out.println("===============inSite Spring by Jae Yoon Lee - sendMail=======================");

        request.setCharacterEncoding("utf-8");

        HashMap<String, Object> result = new HashMap<String, Object>();
        String textBody = request.getParameter("mailForm");
        String sendType = request.getParameter("type");
        System.out.println(sendType);
        if(!textBody.isEmpty()){
            HashMap<String, String> costomerList = SendMailComponent.getCustomerCode(sendType, "");
          //  String sendMailHTML =  SendMailComponent.getHTMLMailForm(textBody);
         //   result= SendMailComponent.sendMailingSystem(sendMailHTML, costomerList);
        }else{
            result.put("error", 1);
            result.put("message", "-null에러 메일 양식이 없습니다.");
        }

        return result;
    }

}
