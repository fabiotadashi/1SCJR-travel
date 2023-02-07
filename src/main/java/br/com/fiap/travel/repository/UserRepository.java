package br.com.fiap.travel.repository;

import br.com.fiap.travel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findFirstByUsername(String username);

}
