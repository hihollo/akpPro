package com.xmm.akp.controller;

import com.xmm.akp.entity.Manager;
import com.xmm.akp.service.Manager.ManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "manager")
public class ManagerController {
    private static Logger log = Logger.getLogger(ManagerController.class);
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/loginTo",params = {"managerName","managerPassword"},method = RequestMethod.GET)
    @ResponseBody
    public String login(String managerName,String managerPassword){
        Manager manager = new Manager();
        manager.setName(managerName);
        manager.setPassword(managerPassword);
        log.info("传来的管理员信息"+ manager.toString());
        boolean flag = managerService.managerLogin(manager);
        log.info("flag : " +flag);
        if (flag == true) {
            return "success";
        }else {
            return "false";
        }
    }
}
