package com.shawbindro.userservice.user.services;

import com.shawbindro.userservice.user.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockUserService {
    private List<User> userList = new ArrayList<>();
    private Long userId=1L;

    public List<User> getAllUsers(){
       return userList;
    }

    public void addUser(User user){
        user.setId(userId);
        userId++;
        userList.add(user);
    }
    public Optional<User> fetchUser(String userId){
      return  userList.stream().filter(user ->
                user.getId().equals(userId)).findFirst();

    }
}
