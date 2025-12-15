package Controller;



import Service.RolService;
import domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolController {
    
    @Autowired
    private RolService rolService;
    
    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        model.addAttribute("roles", rolService.obtenerTodosLosRoles());
        return "rol/listado";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "rol/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarRol(@ModelAttribute Rol rol) {
        rolService.guardarRol(rol);
        return "redirect:/roles/listado";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Rol rol = rolService.obtenerRolPorId(id);
        model.addAttribute("rol", rol);
        return "rol/formulario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return "redirect:/roles/listado";
    }
}
