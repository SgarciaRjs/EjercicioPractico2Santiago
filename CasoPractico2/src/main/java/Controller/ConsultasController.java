package Controller;


import Service.RolService;
import Service.UsuarioService;
import domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;
    
    @GetMapping
    public String mostrarConsultas(Model model) {
        model.addAttribute("roles", rolService.obtenerTodosLosRoles());
        model.addAttribute("usuariosRecientes", usuarioService.obtenerUsuariosOrdenadosPorFecha());
        model.addAttribute("usuariosActivos", usuarioService.contarUsuariosActivos());
        model.addAttribute("usuariosInactivos", usuarioService.contarUsuariosInactivos());
        return "consultas/avanzadas";
    }
    
    @PostMapping("/porRol")
    public String buscarPorRol(@RequestParam Long idRol, Model model) {
        var rol = rolService.obtenerRolPorId(idRol);
        model.addAttribute("resultados", usuarioService.buscarUsuariosPorRol((Rol) rol));
        model.addAttribute("tipoBusqueda", "Usuarios con rol: " + rol.getNombreRol());
        model.addAttribute("roles", rolService.obtenerTodosLosRoles());
        return "consultas/avanzadas";
    }
    
    @PostMapping("/porTexto")
    public String buscarPorTexto(@RequestParam String textoBusqueda, Model model) {
        model.addAttribute("resultados", usuarioService.buscarPorTexto(textoBusqueda));
        model.addAttribute("tipoBusqueda", "Búsqueda por: " + textoBusqueda);
        model.addAttribute("roles", rolService.obtenerTodosLosRoles());
        return "consultas/avanzadas";
    }
    
    @PostMapping("/porFechas")
    public String buscarPorFechas(@RequestParam String fechaInicio, 
                                   @RequestParam String fechaFin, Model model) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio + "T00:00:00");
        LocalDateTime fin = LocalDateTime.parse(fechaFin + "T23:59:59");
        model.addAttribute("resultados", usuarioService.buscarUsuariosPorRangoFechas(inicio, fin));
        model.addAttribute("tipoBusqueda", "Usuarios creados entre " + fechaInicio + " y " + fechaFin);
        model.addAttribute("roles", rolService.obtenerTodosLosRoles());
        return "consultas/avanzadas";
    }
}
