package com.ansbeno.start_beca.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
class StaticPageController {

      @GetMapping
      String homePage(HttpSession session, Model model) {
            String successMessage = (String) session.getAttribute("loginSuccessMessage");
            session.removeAttribute("loginSuccessMessage");
            model.addAttribute("successMessage", successMessage);
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
