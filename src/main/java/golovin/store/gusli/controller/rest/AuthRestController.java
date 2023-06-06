package golovin.store.gusli.controller.rest;

import golovin.store.gusli.dto.AuthRequestDto;
import golovin.store.gusli.dto.TokenDetails;
import golovin.store.gusli.dto.UserDto;
import golovin.store.gusli.entity.type.LoginType;
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

    @PostMapping("/login/username")
    public ResponseEntity<TokenDetails> loginByUsername(@RequestBody @Valid AuthRequestDto dto) {
        return ResponseEntity.ok(securityService.authenticate(dto.getUsername(), dto.getPassword(), LoginType.USERNAME));
    }

    @PostMapping("/login/email")
    public ResponseEntity<TokenDetails> loginByEmail(@RequestBody @Valid AuthRequestDto dto) {
        return ResponseEntity.ok(securityService.authenticate(dto.getEmail(), dto.getPassword(), LoginType.EMAIL));
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
