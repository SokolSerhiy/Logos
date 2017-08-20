package ua.mapper;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import ua.domain.request.RegistrationRequest;
import ua.entity.Role;
import ua.entity.UserEntity;

public interface UserMapper {

	public static User toUser(UserEntity entity){
		return new User(entity.getEmail(), entity.getPassword(), AuthorityUtils.createAuthorityList(String.valueOf(entity.getRole())));
	}
	
	public static UserEntity toUserEntity(RegistrationRequest request){
		UserEntity entity = new UserEntity();
		entity.setEmail(request.getEmail());
		entity.setFullName(request.getFullName());
		entity.setPassword(request.getPassword());
		entity.setPhone(request.getPhone());
		entity.setRole(request.getIsOwner() ? Role.ROLE_OWNER : Role.ROLE_USER);
		return entity;
	}
}
