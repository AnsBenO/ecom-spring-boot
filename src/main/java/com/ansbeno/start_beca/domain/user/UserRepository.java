package com.ansbeno.start_beca.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

      public Optional<UserEntity> findByEmail(String email);

      public boolean existsUserEntityByEmail(String email);

}
