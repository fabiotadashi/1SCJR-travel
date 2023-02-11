package br.com.fiap.travel.service;

import br.com.fiap.travel.dto.AuthDTO;
import br.com.fiap.travel.dto.CreateUserDTO;
import br.com.fiap.travel.dto.JwtDTO;
import br.com.fiap.travel.dto.UserDTO;
import br.com.fiap.travel.entity.UserEntity;
import br.com.fiap.travel.repository.UserRepository;
import br.com.fiap.travel.security.JwtTokenUtil;
import org.h2.engine.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImpl implements UserService {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenUtil jwtTokenUtil,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtDTO login(AuthDTO authDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authDTO.username(),
                    authDTO.password()
            ));
        } catch (DisabledException disabledException) {
            throw new Exception("user.disabled", disabledException);
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("bad.credentials", badCredentialsException);
        }

        String token = jwtTokenUtil.generateToken(authDTO.username());

        return new JwtDTO(token);
    }

    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserDTO.username());
        userEntity.setPassword(passwordEncoder.encode(createUserDTO.password()));

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return new UserDTO(
                savedUserEntity.getId(),
                savedUserEntity.getUsername()
        );
    }

}
