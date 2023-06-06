package golovin.store.gusli.controller.rest;

import golovin.store.gusli.dto.AuthRequestDto;
import golovin.store.gusli.dto.TokenDetails;
import golovin.store.gusli.dto.UserDto;
import golovin.store.gusli.security.CustomPrincipal;
import golovin.store.gusli.security.SecurityService;
import golovin.store.gusli.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDetails> login(@RequestBody @Valid AuthRequestDto dto) {
        return ResponseEntity.ok(securityService.authenticate(dto.getUsername(), dto.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto));
    }

    @GetMapping("/info")
    public UserDto getUserInfo(Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        return userService.getUser(principal.getId());
    }
}
