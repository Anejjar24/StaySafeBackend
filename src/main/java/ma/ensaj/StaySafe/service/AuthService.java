package ma.ensaj.StaySafe.service;

import ma.ensaj.StaySafe.entity.User;
import ma.ensaj.StaySafe.dto.LoginRequest;
import ma.ensaj.StaySafe.repository.UserRepository;
import ma.ensaj.StaySafe.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;


import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash du mot de passe
        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return jwtTokenProvider.generateToken(user);
    }



//    public User login(LoginRequest loginRequest) {
//        User user = userRepository.findByEmail(loginRequest.getEmail());
//        if (user == null) {
//            throw new IllegalArgumentException("User not found");
//        }
//
//        // Hash the input password before comparing
//        String hashedInputPassword = passwordEncoder.encode(loginRequest.getPassword());
//
//        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("Invalid credentials");
//        }
//
//        return user;
//    }
}
