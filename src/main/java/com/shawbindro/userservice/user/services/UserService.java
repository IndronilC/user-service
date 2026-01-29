package com.shawbindro.userservice.user.services;



import com.shawbindro.userservice.user.dtos.AddressDTO;
import com.shawbindro.userservice.user.dtos.UserRequest;
import com.shawbindro.userservice.user.dtos.UserResponse;
import com.shawbindro.userservice.user.exceptions.UserNotFoundException;
import com.shawbindro.userservice.user.models.Address;
import com.shawbindro.userservice.user.models.User;
import com.shawbindro.userservice.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
  //  private final KeyCloakAdminService keyCloakAdminService;
    private List<User> userList = new ArrayList<>();
   private Long nextId = 1L;

   public List<User> fetchAllUsers() {
       List<User> users = userRepository.findAll();

       users.stream()
               .findAny()
               .orElseThrow(() -> new UserNotFoundException("No users found"));

       return users;
   }

    /*public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }*/

    public void addUser(User user){
        userRepository.save(user);
    }

    /*public void addUser(UserRequest userRequest){
      *//*   user.setId(nextId++);
        String token = keyCloakAdminService.getAdminAccessToken();
        String keycloakUserId =
                keyCloakAdminService.createUser(token, userRequest);

        User user = new User();
        updateUserFromRequest(user, userRequest);
        user.setKeycloakId(keycloakUserId);

        keyCloakAdminService.assignRealmRoleToUser(userRequest.getUsername(),
                "USER", keycloakUserId);
        userRepository.save(user);*//*
    }*/

 /*   public Optional<UserResponse> fetchUser(String id) {
        return userRepository.findById(Long.valueOf(id))
                .map(this::mapToUserResponse);
    }*/

    public Optional<User> fetchUser(String id) {
        return userRepository.findById(Long.valueOf(id));

    }

/*    public boolean updateUser(Long id, UserRequest updatedUserRequest) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateUserFromRequest(existingUser, updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }*/

    public boolean updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setEmail(user.getEmail());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    /*private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
    //    user.setPhone(userRequest.getPhone());
        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setState(userRequest.getAddress().getState());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setCity(userRequest.getAddress().getCity());
            address.setCountry(userRequest.getAddress().getCountry());
   //         user.setAddress(address);
        }
    }*/

   /* private UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
  //      response.setKeyCloakId(user.getKeycloakId());
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
  *//* //     response.setPhone(user.getPhone());
  //      response.setRole(user.getRole());

     //   if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
    //        addressDTO.setStreet(user.getAddress().getStreet());
   //         addressDTO.setCity(user.getAddress().getCity());
    //        addressDTO.setState(user.getAddress().getState());
    //        addressDTO.setCountry(user.getAddress().getCountry());
    //        addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);*//*
        }
        return response;
    }*/
}
