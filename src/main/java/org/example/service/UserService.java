package org.example.service;

import org.example.dto.RegisterRequest;
import org.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(RegisterRequest request);

    Page<User> findAll(Pageable pageable);
}
