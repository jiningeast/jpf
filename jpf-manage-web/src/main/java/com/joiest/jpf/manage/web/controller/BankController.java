package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.facade.BankServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankServiceFacade bankServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "bank/bankList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list( long page, long rows ){
        Map<String, Object> map = new HashMap<>();
        map.put("total", bankServiceFacade.getBankCount());
        map.put("rows", bankServiceFacade.getBank(page, rows));

        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ModelAndView add(){
        return new ModelAndView("bank/bankAdd");
    }

    @RequestMapping("/save")
    @ResponseBody
    public JpfResponseDto save(String paybankname, String tpid, String bankcode) {
        return bankServiceFacade.addBank(paybankname, tpid, bankcode);
    };
}
