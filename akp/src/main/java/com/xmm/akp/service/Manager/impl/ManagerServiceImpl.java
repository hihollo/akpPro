package com.xmm.akp.service.Manager.impl;

import com.xmm.akp.dao.ManagerMapper;
import com.xmm.akp.entity.Manager;
import com.xmm.akp.service.Manager.ManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private static Logger log = Logger.getLogger(ManagerServiceImpl.class);
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public boolean managerLogin(Manager manager) {
        List<Manager> managers = managerMapper.getAllManager();
        log.debug("所有的管理员");
        log.debug(managers);
        boolean flag= false;
        for(Manager m : managers){
//            log.debug("进入循环");
//            log.debug("m.getName()==manager.getName(): "+m.getName()==manager.getName());
//            log.debug("m.getPassword()==manager.getPassword(): "+m.getPassword()==manager.getPassword());
            if(m.getName().equals(manager.getName())&&m.getPassword().equals(manager.getPassword())){
                flag = true;
//                System.out.println("flag在service中改为true");
                break;
            }else {
                flag = false;
            }
        }
        return flag;
    }
}
