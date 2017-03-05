package ua.service;

import ua.entity.User;

public interface UserService {

	void save(User user);

	int createNewUser();

	void addToShoppingCart(int userId, int itemId);
}
