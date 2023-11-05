package com.example.foodbackend.service;

import com.example.foodbackend.entity.Role;
import com.example.foodbackend.entity.UserEntity;
import com.example.foodbackend.repository.RoleRepository;
import com.example.foodbackend.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        UserEntity adminUser = new UserEntity();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);

//        UserEntity user = new UserEntity();
//        user.setUserName("user123");
//        user.setUserPassword(getEncodedPassword("user@pass"));
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userRepository.save(user);
    }

    public UserEntity registerNewUser(UserEntity userEntity){
        Role role = roleRepository.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        userEntity.setRole(roles);
        userEntity.setUserPassword(getEncodedPassword(userEntity.getUserPassword()));
        return userRepository.save(userEntity);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
