package com.example.datawarehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {
    @GetMapping(value={"home","/","index"})
    public String home(Model model){
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd,MM,yyyy HH:mm:ss");
        String time = now.format(formatter);
        model.addAttribute("currentDate",time);
        return "index";
    }
}
