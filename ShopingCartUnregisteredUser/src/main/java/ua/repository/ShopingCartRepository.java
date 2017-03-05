package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.ShopingCart;

public interface ShopingCartRepository extends JpaRepository<ShopingCart, Integer>{

}
