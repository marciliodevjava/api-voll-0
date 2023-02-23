package br.com.med.voll.api.domain.usuario;

import br.com.med.voll.api.dto.LoginDto;
import br.com.med.voll.api.dto.UsuarioCadastroDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity(name = "usuario")
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    public Usuario(LoginDto dto){
        this.login = dto.login();
        this.senha = dto.senha();
    }

    public Usuario(UsuarioCadastroDto cadastroDto){
        this.login = cadastroDto.login();
        this.senha = BCrypt.hashpw(cadastroDto.senha(),BCrypt.gensalt());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
