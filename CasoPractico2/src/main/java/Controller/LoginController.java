package Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
    
    @GetMapping("/")
    public String inicio() {
        return "redirect:/dashboard";
    }
}
