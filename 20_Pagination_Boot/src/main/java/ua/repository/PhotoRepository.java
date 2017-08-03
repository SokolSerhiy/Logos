package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{

}
