package org.example.service;


import lombok.AllArgsConstructor;
import org.example.dto.RegisterRequest;
import org.example.entity.User;
import org.example.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(RegisterRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(request.getPassword());

        userRepository.findByEmail(request.getEmail()).ifPresentOrElse(user ->
                        userRepository.save(User.builder()
                                .id(user.getId())
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(password)
                                .build()),
                () ->
                        userRepository.save(User.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(password)
                                .build())
        );
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
