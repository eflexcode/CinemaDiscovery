package com.larrex.AccountServer.serviceImpl;

import com.larrex.AccountServer.entity.Address;
import com.larrex.AccountServer.repository.UserRepository;
import com.larrex.AccountServer.entity.User;
import com.larrex.AccountServer.model.UserModel;
import com.larrex.AccountServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {

        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        Date date = new Date();
        user.setCreatedAt(date);
        user.setUpdateAt(date);

       return userRepository.save(user);
    }

    @Override
    public User updateLocation(String userId, Address address) {

        User user = getUserById(userId);
        user.setUpdateAt(new Date());
        user.setAddress(address);

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
    public User verifyUser(String email) {
        System.out.println("LLLLLLLLLLLLLLLLLLLL"+email);
        User user= getUserByEmail(email);
        user.setIsVerified(true);

        return userRepository.save(user);
    }


    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not user found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not user found"));
    }

    @Override
    public void deleteUser(String userId) {

        userRepository.deleteById(userId);

    }

}
