/**
 * Copyright (C), 2015-2019, 重庆了赢科技有限公司
 * FileName: UserController
 * Author:   萧毅
 * Date:     2019/3/7 11:03
 * Description:
 */
package com.snow.postgis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {



    @Autowired
    private UserRepository userRepository;



    @GetMapping("x")
    public ResponseEntity x(){

      return ResponseEntity.ok(userRepository.findAll());

    }

    @GetMapping("index")
    public void index(@RequestParam String name){



        userRepository.save(User.builder().name(name).password("xssxssdjfidfisdfhoisdhfosdjiofsdofdosjfodsfodjsfjdsfjidjifjidjfidjifojidsfasfjiasjifjiodasifjidsfjiojisdfojidasfojidsfidifijdsfdfojidjifojidasfojiadjisfoidapfojaisdaofpdajfidpoafpdapofjpapjfdpafojdajfaiodsifjaidfjidasfpapfjdjasfojia").build());




    }








}