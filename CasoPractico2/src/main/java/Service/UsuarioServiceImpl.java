package Service;


import Repository.UsuarioRepository;
import domain.Rol;
import domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setFechaCreacion(LocalDateTime.now());
        } else {
            Usuario usuarioExistente = usuarioRepository.findById(usuario.getId()).orElse(null);
            if (usuarioExistente != null) {
                usuario.setFechaCreacion(usuarioExistente.getFechaCreacion());
            }
        }
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuariosPorRol(Rol rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuariosPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return usuarioRepository.findByFechaCreacionBetween(fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorTexto(String busqueda) {
        return usuarioRepository.buscarPorTexto(busqueda);
    }

    @Override
    @Transactional(readOnly = true)
    public long contarUsuariosActivos() {
        return usuarioRepository.countByActivo(true);
    }

    @Override
    @Transactional(readOnly = true)
    public long contarUsuariosInactivos() {
        return usuarioRepository.countByActivo(false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuariosOrdenadosPorFecha() {
        return usuarioRepository.findAllByOrderByFechaCreacionDesc();
    }
}




