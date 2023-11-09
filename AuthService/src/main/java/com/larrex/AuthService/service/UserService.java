package com.larrex.AuthService.service;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

   User createUser(UserModel userModel, HttpServletRequest httpServletRequest);
   User updateUser(UserModel userModel,String userId);
   User getUserById(String userId);
   void deleteUser(String userId);

}
