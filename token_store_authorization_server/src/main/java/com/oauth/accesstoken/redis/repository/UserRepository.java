package com.oauth.accesstoken.redis.repository;

import com.oauth.accesstoken.redis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
