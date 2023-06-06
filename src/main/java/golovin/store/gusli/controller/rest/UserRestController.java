package golovin.store.gusli.controller.rest;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.UserDto;
import golovin.store.gusli.service.UserService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PageableResponse<UserDto>> getUsers(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.getUsers(pageable));
    }

    @PostMapping("/lock/{userId}")
    public ResponseEntity<UserDto> lockUser(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                              @RequestParam @NotNull Boolean locked) {
        return ResponseEntity.ok(userService.lockUser(userId, locked));
    }
}
