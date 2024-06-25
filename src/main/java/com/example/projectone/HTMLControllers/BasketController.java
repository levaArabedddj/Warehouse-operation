package com.example.projectone.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketController {

    @GetMapping("/Basket")
    public String getSiteBasket(){
        return "Basket";
    }
}





