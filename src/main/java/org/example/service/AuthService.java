package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.SignInRequest;
import org.example.dto.request.SignUpRequest;
import org.example.dto.response.BooleanOperationResponse;
import org.example.dto.response.TokenResponse;
import org.example.exception.SvetlovodException;
import org.example.model.UserEntity;
import org.example.repository.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public BooleanOperationResponse signUp(SignUpRequest request) {
        if (request.getEmail().isBlank()) {
            throw new SvetlovodException("EMPTY_EMAIL", "ВВЕДЕН ПУСТОЙ EMAIL", HttpStatus.BAD_REQUEST);
        }
        if (request.getPassword().isBlank()) {
            throw new SvetlovodException("EMPTY_PASSWORD", "ВВЕДЕН ПУСТОЙ ПАРОЛЬ", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userEntityRepository.save(user);
        return new BooleanOperationResponse(true);
    }

    public TokenResponse signIn(SignInRequest request) {
        Optional<UserEntity> optionalUser = userEntityRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new SvetlovodException("WRONG_EMAIL", "ВВЕДЕН НЕВЕРНЫЙ EMAIL", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = optionalUser.get();
        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            String token = UUID.randomUUID().toString();
            userEntity.setToken(token);
            userEntityRepository.save(userEntity);
            return new TokenResponse(token);
        } else {
            throw new SvetlovodException("WRONG_PASSWORD", "ВВЕДЕН НЕВЕРНЫЙ ПАРОЛЬ", HttpStatus.BAD_REQUEST);
        }
    }

    public BooleanOperationResponse signOut() {
        UserEntity user = getCurrentUser();
        user.setToken(null);
        userEntityRepository.save(user);
        return new BooleanOperationResponse(true);
    }

    public UserEntity getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails e) {
            return userEntityRepository.findByEmail(e.getUsername()).orElseThrow();
        }
        throw new SvetlovodException("NOT_LOGGED_IN", "Не вошел в аккаунт", HttpStatus.UNAUTHORIZED);
    }
}
