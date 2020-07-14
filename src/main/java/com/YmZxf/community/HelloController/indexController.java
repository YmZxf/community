package com.YmZxf.community.HelloController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class indexController {
    @GetMapping("/")
    public String hello(@RequestParam(name="name",required = true,defaultValue = "Zheng") String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }
}
