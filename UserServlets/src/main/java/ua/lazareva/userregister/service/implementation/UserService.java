package ua.lazareva.userregister.service.implementation;

import ua.lazareva.userregister.dao.IGenericDao;
import ua.lazareva.userregister.entity.User;
import ua.lazareva.userregister.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private IGenericDao<User, Integer> userDao;

    public UserService(IGenericDao<User, Integer> userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(Integer userId) {
        return userDao.getById(userId);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    public void delete(Integer userId) {
        userDao.delete(userId);
    }
}
