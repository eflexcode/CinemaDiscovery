package com.larrex.AuthService.service;

import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;

public interface UserService {

   User createUser(UserModel userModel);
   User updateUser(UserModel userModel);
   User getUser(String userId);
   void deleteUser(String userId);

}
