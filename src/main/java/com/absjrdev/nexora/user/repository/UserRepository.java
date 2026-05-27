package com.absjrdev.nexora.user.repository;

import com.absjrdev.nexora.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface UserRepository extends JpaRepository<User, Long> {

}
