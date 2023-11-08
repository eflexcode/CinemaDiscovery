package com.larrex.AuthService.serviceImpl;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import com.larrex.AuthService.repository.UserRepository;
import com.larrex.AuthService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {

        User user = new User();
        BeanUtils.copyProperties(userModel,user);
        user.setCreatedAt(new Date());
        user.setUpdateAt(new Date());

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserModel userModel) {
        return null;
    }

    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

}
