package Controller;


import Service.UsuarioService;
import domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public String mostrarPerfil(Authentication authentication, Model model) {
        String email = authentication.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
        model.addAttribute("usuario", usuario);
        return "perfil/perfil";
    }
}

