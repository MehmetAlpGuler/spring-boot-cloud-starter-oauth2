package com.oauth.accesstoken.redis.service;

import java.util.Optional;

import com.oauth.accesstoken.redis.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oauth.accesstoken.redis.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository repository;

	public void save(UserEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public UserEntity findOne(String username) {
		Optional<UserEntity> userEntityOption = repository.findById(username);
		if (userEntityOption.isPresent() == false) {
			return null;
		}
		UserEntity userEntity = userEntityOption.get();
		userEntity.getRoles().size();
		return userEntity;
	}

}
