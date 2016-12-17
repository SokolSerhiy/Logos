package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
