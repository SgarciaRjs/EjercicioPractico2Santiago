package Controller;


import Service.RolService;
import Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReportesController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;
    
    @GetMapping
    public String mostrarReportes(Model model) {
        model.addAttribute("totalUsuarios", usuarioService.obtenerTodosLosUsuarios().size());
        model.addAttribute("usuariosActivos", usuarioService.contarUsuariosActivos());
        model.addAttribute("usuariosInactivos", usuarioService.contarUsuariosInactivos());
        model.addAttribute("roles", rolService.obtenerTodosLosRoles());
        model.addAttribute("usuarios", usuarioService.obtenerTodosLosUsuarios());
        return "reportes/reportes";
    }
}
