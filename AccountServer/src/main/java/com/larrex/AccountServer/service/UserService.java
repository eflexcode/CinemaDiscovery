package com.larrex.AccountServer.service;

import com.larrex.AccountServer.entity.Address;
import com.larrex.AccountServer.model.UserModel;
import com.larrex.AccountServer.entity.User;

public interface UserService {

   User createUser(UserModel userModel);

   User updateLocation(String userId, Address address);

   //did noy use this because it was inaccurate it better for use to send location from frontend
//   User createUser(UserModel userModel, HttpServletRequest httpServletRequest);
   User updateUser(UserModel userModel,String userId);
   User getUserById(String userId);
   User getUserByEmail(String email);
   void deleteUser(String userId);

}
