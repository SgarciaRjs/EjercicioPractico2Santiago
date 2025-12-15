package Service;


import domain.Rol;
import java.util.List;

public interface RolService {
    
    List<Rol> obtenerTodosLosRoles();
    
    Rol obtenerRolPorId(Long id);
    
    void guardarRol(Rol rol);
    
    void eliminarRol(Long id);
    
    Rol buscarPorNombre(String nombre);
}

