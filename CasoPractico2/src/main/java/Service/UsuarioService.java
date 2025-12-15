package Service;




import domain.Rol;
import domain.Usuario;
import java.time.LocalDateTime;
import java.util.List;

public interface UsuarioService {
    
    List<Usuario> obtenerTodosLosUsuarios();
    
    Usuario obtenerUsuarioPorId(Long id);
    
    void guardarUsuario(Usuario usuario);
    
    void eliminarUsuario(Long id);
    
    Usuario buscarPorEmail(String email);
    
    // Consultas avanzadas
    List<Usuario> buscarUsuariosPorRol(Rol rol);
    
    List<Usuario> buscarUsuariosPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    List<Usuario> buscarPorTexto(String busqueda);
    
    long contarUsuariosActivos();
    
    long contarUsuariosInactivos();
    
    List<Usuario> obtenerUsuariosOrdenadosPorFecha();
}
