package golovin.store.gusli.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TokenDetails {

    private Long userId;
    private String token;
    private Date issuedAt;
    private Date expiresAt;
}

