package com.oauth.accesstoken.redis.utility;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.oauth.accesstoken.redis.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class ConvertUtility {

	public static Collection<? extends GrantedAuthority> convertToGrantedAuthorities(
			Collection<RoleEntity> roleEntitys) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>(roleEntitys.size());
		for (RoleEntity roleEntity : roleEntitys) {
			GrantedAuthority grantedAuthority = convertToGrantedAuthority(roleEntity);
			grantedAuthorities.add(grantedAuthority);
		}
		return grantedAuthorities;
	}

	private static GrantedAuthority convertToGrantedAuthority(RoleEntity roleEntity) {
		String roleId = roleEntity.getRoleId();
		GrantedAuthority grantedAuthoritie = new SimpleGrantedAuthority(roleId);
		return grantedAuthoritie;
	}

}
