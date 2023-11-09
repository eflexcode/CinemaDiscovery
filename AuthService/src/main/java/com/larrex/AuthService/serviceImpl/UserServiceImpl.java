package com.larrex.AuthService.serviceImpl;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import com.larrex.AuthService.repository.UserRepository;
import com.larrex.AuthService.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel, HttpServletRequest httpServletRequest) {

        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        Date date = new Date();
        user.setCreatedAt(date);
        user.setUpdateAt(date);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserModel userModel, String userId) {

        User user = getUserById(userId);

        user.setDisplayName((userModel.getDisplayName() != null) ? (userModel.getDisplayName()) : user.getDisplayName());
        user.setBio(userModel.getBio() != null ? userModel.getBio() : user.getBio());
        user.setEmail(userModel.getEmail() != null ? userModel.getEmail() : user.getEmail());
        user.setImageUrl(userModel.getImageUrl() != null ? userModel.getImageUrl() : user.getImageUrl());
        user.setUpdateAt(new Date());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not user found"));
    }

    @Override
    public void deleteUser(String userId) {

        userRepository.deleteById(userId);

    }

}
