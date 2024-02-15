package dev.rajat.UserService.SpringSecurity;

import dev.rajat.UserService.Models.User;
import dev.rajat.UserService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("Username is not found!");
        }
        User user = optionalUser.get();

        return new CustomUserDetails(user);
    }
}
