package com.whut.truck.controller;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Service.SystemAdminService;
import com.whut.truck.entity.SystemAdmin;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthController {

    private final SystemAdminService systemAdminService;

    public AuthController(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }
    
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("user_name") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) throws IOException {
        SystemAdminDto result = systemAdminService.login(username, password);
        
        switch (result.getMsg()) {
            case 0: // Username not found
                model.addAttribute("usernameError", "用户名不存在");
                model.addAttribute("alertMessage", "用户名不存在，请重新输入");
                return "Login";
            case 1: // Password error
                model.addAttribute("passwordError", "密码错误");
                model.addAttribute("alertMessage", "密码错误，请重新输入");
                return "Login";
            case 2: // Success
                session.setAttribute("systemAdmin", username);
                session.setAttribute("componentName", "default");
                return "redirect:/hall";
            default:
                return "Login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "Register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("confirm_pass") String confirmPass,
                           Model model) throws IOException {
        
        SystemAdminDto checkResult = systemAdminService.check(username);
        
        if (checkResult.getMsg() == 3) { // Username taken
            model.addAttribute("usernameError", "用户名已被注册，请重新注册");
            model.addAttribute("alertMessage", "用户名已被注册，请重新注册");
            return "Register";
        }

        if (!password.equals(confirmPass)) {
            model.addAttribute("alertMessage", "两次密码输入不一致，请重新输入");
            return "Register";
        }

        // Save
        systemAdminService.save(new SystemAdmin(username, password, email));
        model.addAttribute("alertMessage", "注册成功");
        return "Login"; // Should probably be redirect with flash attribute, but consistent with old behavior
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
