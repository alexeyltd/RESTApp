package restful.service;


import restful.model.User;

public interface UserService extends CRUDService<User> {

    User findByUserName(String username);

}
