package restful.service;

import restful.model.UserRole;

import java.util.List;


public interface RoleService extends CRUDService<UserRole> {

    UserRole findRoleByUserName(String username);

}
