package ru.guteam.cookstarter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.guteam.cookstarter.api.enums.RequestStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponse {
    private RequestStatus status;
    private String error;
    private String id;
}
