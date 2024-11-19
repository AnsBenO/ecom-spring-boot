package com.ansbeno.start_beca.web.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ansbeno.start_beca.domain.user.UserService;
import com.ansbeno.start_beca.dtos.UserDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
class AuthController {

      private final UserService userService;

      @GetMapping("/register")
      public String getRegisterForm(Model model) {
            if (isUserAuthenticated()) {
                  return "redirect:/";
            }
            UserDto user = new UserDto();
            model.addAttribute("user", user);
            return "views/auth/register";
      }

      @PostMapping("/register")
      public String registerUser(
                  @Valid @ModelAttribute("user") UserDto user,
                  BindingResult result) {
            if (isUserAuthenticated()) {
                  return "redirect:/";
            }

            if (result.hasErrors()) {
                  return "views/auth/register";
            }
            boolean existsByEmail = userService.existsByEmail(user.getEmail());

            if (existsByEmail) {
                  return "redirect:/register?fail";
            }
            userService.save(user);
            return "redirect:/";
      }

      @GetMapping("/login")
      public String showLoginForm(Model model,
                  @RequestParam(value = "error", required = false) String error,
                  @RequestParam(value = "logout", required = false) String logout,
                  RedirectAttributes redirectAttributes) {
            if (error != null) {
                  model.addAttribute("error", "Invalid credentials");
            }
            if (logout != null) {
                  model.addAttribute("logout", "You have been logged out");
            }
            if (isUserAuthenticated()) {
                  redirectAttributes.addFlashAttribute("error", "You Are Already Authenticated");
                  return "redirect:/";
            }
            return "views/auth/login";
      }

      @GetMapping("/logout")
      public String logout() {
            return "auth/login";
      }

      private boolean isUserAuthenticated() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("Authentication {}", authentication.getName());
            boolean isAuthenticated = (authentication != null
                        && !(authentication instanceof AnonymousAuthenticationToken));
            log.info("Is user authenticated {}, name :{}", isAuthenticated, authentication.getName());
            return isAuthenticated;
      }

}
