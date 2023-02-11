package br.com.fiap.travel.controller;

import br.com.fiap.travel.dto.AuthDTO;
import br.com.fiap.travel.dto.CreateUserDTO;
import br.com.fiap.travel.dto.JwtDTO;
import br.com.fiap.travel.dto.UserDTO;
import br.com.fiap.travel.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("login")
    public JwtDTO login(@RequestBody AuthDTO authDTO) throws Exception {
        return userService.login(authDTO);
    }

    @PostMapping
    public UserDTO create(@RequestBody CreateUserDTO createUserDTO){
        return userService.createUser(createUserDTO);
    }

}
