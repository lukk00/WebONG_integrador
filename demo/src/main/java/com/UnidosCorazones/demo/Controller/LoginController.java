package com.UnidosCorazones.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Retorna el nombre del archivo 'login.html'
    }

    @GetMapping("/")
    public String redirectToHome() {
        // Redirige la raíz a la página de inicio pública
        return "redirect:/inicio.html";
    }
}
