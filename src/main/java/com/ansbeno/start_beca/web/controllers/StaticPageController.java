package com.ansbeno.start_beca.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
class StaticPageController {

      @GetMapping
      String homePage(Model model) {
            model.addAttribute("success", "Hello From Controller");
            return "views/home";
      }

      @GetMapping("/about")
      String aboutPage() {
            return "views/about";
      }

      @GetMapping("/contact")
      String contactPage() {
            return "views/contact";
      }
}
