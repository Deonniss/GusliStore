package golovin.store.gusli.controller.rest;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.UserDto;
import golovin.store.gusli.service.UserService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable @Positive(message = "userId must be positive") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getUser(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.getUsers(pageable));
    }
}
