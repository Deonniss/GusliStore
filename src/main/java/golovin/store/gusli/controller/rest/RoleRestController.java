package golovin.store.gusli.controller.rest;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.RoleDto;
import golovin.store.gusli.service.RoleService;
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
@RequestMapping("/api/v1/roles")
public class RoleRestController {

    private final RoleService roleService;

    @GetMapping("/{userId}")
    public ResponseEntity<PageableResponse<RoleDto>> getRolesByUserId(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                                      @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(roleService.getRolesByUserId(userId, pageable));
    }

    @GetMapping
    public ResponseEntity<PageableResponse<RoleDto>> getRoles(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(roleService.getRoles(pageable));
    }
}
