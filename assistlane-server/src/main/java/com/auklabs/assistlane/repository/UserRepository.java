package com.auklabs.assistlane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.auklabs.assistlane.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
