package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.People;

public interface PeopleRepository extends JpaRepository<People, Long>{

}