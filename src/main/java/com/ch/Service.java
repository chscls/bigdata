package com.ch;

import com.ch.dao.hbase.UserMapper;
import com.ch.entity.UserMo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/test")
@RestController
public class Service {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/getUser")
    public ResponseEntity<UserMo> getUser(Long id){
        UserMo o = userMapper.selectUserById(id);

        return new ResponseEntity(o, HttpStatus.OK);

    }


}
