package golovin.store.gusli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    private String email;
    private String firstName;
    private String lastName;

    @JsonProperty(access = WRITE_ONLY)
    private String password;

    @JsonProperty(access = READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = READ_ONLY)
    private Timestamp updatedAt;
}
