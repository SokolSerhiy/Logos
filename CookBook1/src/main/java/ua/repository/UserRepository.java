package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String username);

}
