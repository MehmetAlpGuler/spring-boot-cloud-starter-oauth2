package com.oauth.accesstoken.redis.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String roleId;
	private String rolename;

	@ManyToMany(mappedBy = "roles")
	private Collection<UserEntity> users;

	public RoleEntity() {
		super();
	}

	public RoleEntity(String roleId, String rolename, Collection<UserEntity> users) {
		super();
		this.roleId = roleId;
		this.rolename = rolename;
		this.users = users;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getRolename() {
		return rolename;
	}

	public Collection<UserEntity> getUsers() {
		return users;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setUsers(Collection<UserEntity> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleEntity other = (RoleEntity) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

}
