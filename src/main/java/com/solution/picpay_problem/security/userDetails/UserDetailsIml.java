package com.solution.picpay_problem.security.userDetails;

import com.solution.picpay_problem.domain.user.UserEntity;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/* aqui eu configuro quais sãos acredendenciais alocadas no banco de dados
    exemplo onde esta a coluna senha ou "Username" que seria a senha nesse caso
*/
public class UserDetailsIml implements UserDetails {

    private final UserEntity user;
    public UserDetailsIml(UserEntity user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // configura o cargo do usuario em granAuthority
        return List.of(
            new SimpleGrantedAuthority("ROLE_" + user.getCargo().name())
        );
    }


    @Override
    public @Nullable String getPassword() {
        /* Diz ao Authentication Manager qual seria a senha para identificar o usuario
        * obs: o usuario é encontrado de acordo com a função no UserDetails Service*/

        return user.getSenha();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
