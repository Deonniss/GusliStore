package golovin.store.gusli.service;

import golovin.store.gusli.entity.Status;
import golovin.store.gusli.entity.type.StatusType;
import golovin.store.gusli.repository.StatusRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class StatusServiceTest {

    @Mock
    private StatusRepository statusRepository;

    @Test
    public void testGetByType() {
        StatusService statusService = new StatusService(statusRepository);
        Status expectedStatus = Status.builder()
                .id(1L)
                .name("create")
                .type(StatusType.CREATED)
                .description("create")
                .build();
        when(statusRepository.findByType(StatusType.CREATED)).thenReturn(expectedStatus);
        Status actualStatus = statusService.getByType(StatusType.CREATED);
        assertEquals(expectedStatus, actualStatus);
    }
}
