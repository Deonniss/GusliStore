package golovin.store.gusli.common;

import lombok.*;

import java.util.List;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse<T> {

    private List<T> items;
    private long total;
}
