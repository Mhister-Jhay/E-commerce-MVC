package org.jhay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackDTO {
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
}
