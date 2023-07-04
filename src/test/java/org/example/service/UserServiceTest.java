package org.example.service;

import org.example.dto.RegisterRequest;
import org.example.entity.User;
import org.example.jpa.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private RegisterRequest registerRequest;
    private User user;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registerRequest = new RegisterRequest();

    }

    @Test
    public void testSaveUser() {
        registerRequest.setPassword("test");
        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.ofNullable(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        userService.saveUser(registerRequest);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(1, 3, Sort.by("createdAt"));
        Page userPage = Mockito.mock(Page.class);
        Mockito.when(userRepository.findAll(pageable)).thenReturn(userPage);
    }
}