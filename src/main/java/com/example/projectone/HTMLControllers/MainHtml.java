package com.example.projectone.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainHtml {

    @GetMapping("/")
    public String mainController(){
        return "index";
    }
    @GetMapping("/MainClient")
    public String getMainClient(){
       return "MainClient" ;
    }
    @GetMapping("/MainEmployee")
    public String getMainEmployee(){
        return "MainEmployee";
    }
    @GetMapping("/MainSupplier")
    public String getMainSupplier(){
        return "MainSupplier";
    }
}
