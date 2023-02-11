package br.com.fiap.travel.security;

import br.com.fiap.travel.entity.UserEntity;
import br.com.fiap.travel.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findFirstByUsername(username);
        if(userEntity == null){
            throw new UsernameNotFoundException("username.not.found");
        }

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                new ArrayList<>()); //TODO Implementar Roles
    }

}
