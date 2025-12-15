package Service;



import Repository.UsuarioRepository;
import domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("=== Buscando usuario: " + email + " ===");
        
        Usuario usuario = usuarioRepository.findByEmail(email);
        
        if (usuario == null) {
            System.out.println("ERROR: Usuario NO encontrado");
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }
        
        System.out.println("Usuario encontrado: " + usuario.getNombre());
        System.out.println("Password: " + usuario.getPassword());
        System.out.println("Rol: " + usuario.getRol().getNombre());
        
        List<GrantedAuthority> autoridades = new ArrayList<>();
        autoridades.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()));
        
        return new User(
            usuario.getEmail(), 
            usuario.getPassword(), 
            usuario.getActivo(), 
            true, 
            true, 
            true, 
            autoridades
        );
    }
}


