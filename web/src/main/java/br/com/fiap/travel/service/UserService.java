package br.com.fiap.travel.service;

import br.com.fiap.travel.dto.AuthDTO;
import br.com.fiap.travel.dto.CreateUserDTO;
import br.com.fiap.travel.dto.JwtDTO;
import br.com.fiap.travel.dto.UserDTO;

public interface UserService {

    JwtDTO login(AuthDTO authDTO) throws Exception;
    UserDTO createUser(CreateUserDTO createUserDTO);

}
