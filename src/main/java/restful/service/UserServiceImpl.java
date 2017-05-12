package restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restful.model.User;
import restful.repository.UserRepository;

import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User getById(Serializable id) {
        return userRepository.findOne((Long) id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        userRepository.delete((Long) id);
    }
}
