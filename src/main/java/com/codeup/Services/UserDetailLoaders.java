//package com.codeup.Services;
//
//import com.codeup.entity.User;
//import com.codeup.entity.UserWithRoles;
//import com.codeup.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class UserDetailLoaders implements UserDetailsService {
//
//    private final UserRepository users;
//
//
//
//    public UserDetailLoaders(UserRepository users) {
//        this.users = users;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = users.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("No user found for " + username);
//        }
//
//        return (UserDetails) new UserWithRoles(user);
//    }
//}
