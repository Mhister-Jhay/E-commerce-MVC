package org.jhay.serviceImpl;
import org.jhay.dto.FeedbackDTO;
import org.jhay.model.Feedback;
import org.jhay.repository.FeedbackRepository;
import org.jhay.service.impl.FeedbackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class FeedbackServiceImplTest {
    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveFeedback() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setName("Joshua");
        feedbackDTO.setSubject("Failed Order");
        feedbackDTO.setEmail("omosighojosh@gmail.com");
        feedbackDTO.setMessage("Order failed");

        Feedback feedback = Feedback.builder()
                .name(feedbackDTO.getName())
                .subject(feedbackDTO.getSubject())
                .email(feedbackDTO.getEmail())
                .message(feedbackDTO.getMessage())
                .build();
        when(feedbackRepository.save(feedback)).thenReturn(feedback);
        assertNotNull(feedback.getName());
        assertTrue(feedbackService.saveFeedback(feedbackDTO));
    }
}