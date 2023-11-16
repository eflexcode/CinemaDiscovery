package com.larrex.AuthService.service;

import com.larrex.AuthService.entity.Address;
import com.larrex.AuthService.entity.User;
import com.larrex.AuthService.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

   User createUser(UserModel userModel);

   User updateLocation(String userId,Address address);

   //did noy use this because it was inaccurate it better for use to send location from frontend
//   User createUser(UserModel userModel, HttpServletRequest httpServletRequest);
   User updateUser(UserModel userModel,String userId);
   User getUserById(String userId);
   void deleteUser(String userId);

}
