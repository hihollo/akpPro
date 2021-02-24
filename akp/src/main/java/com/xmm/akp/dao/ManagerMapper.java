package com.xmm.akp.dao;

import com.xmm.akp.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ManagerMapper {
    List<Manager> getAllManager();
}
