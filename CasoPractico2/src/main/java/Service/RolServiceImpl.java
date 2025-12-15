package Service;

import com.plataforma.academica.repository.RolRepository;
import domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol obtenerRolPorId(Long idRol) {
        return rolRepository.findById(idRol).orElse(null);
    }

    @Override
    @Transactional
    public void guardarRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void eliminarRol(Long idRol) {
        rolRepository.deleteById(idRol);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol buscarPorNombre(String nombreRol) {
        return rolRepository.findByNombre(nombreRol);
    }
}

