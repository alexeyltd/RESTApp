package restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restful.model.UserRole;
import restful.repository.UserRolesRepository;

import java.io.Serializable;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    UserRolesRepository userRolesRepository;

    @Override
    public UserRole save(UserRole entity) {
        return userRolesRepository.save(entity);
    }

    @Override
    public UserRole getById(Serializable id) {
        return userRolesRepository.findOne((Long) id);
    }

    @Override
    public List<UserRole> getAll() {
        return (List<UserRole>) userRolesRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        userRolesRepository.delete((Long) id);
    }
}
