package org.jhay.service.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.dto.FeedbackDTO;
import org.jhay.model.Feedback;
import org.jhay.repository.FeedbackRepository;
import org.jhay.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    @Override
    public boolean saveFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = Feedback.builder()
                .name(feedbackDTO.getName())
                .email(feedbackDTO.getEmail())
                .subject(feedbackDTO.getSubject())
                .message(feedbackDTO.getMessage())
                .build();
        feedbackRepository.save(feedback);
        return true;
    }
}
