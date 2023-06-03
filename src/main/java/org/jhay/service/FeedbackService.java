package org.jhay.service;


import org.jhay.dto.FeedbackDTO;

public interface FeedbackService {
    boolean saveFeedback(FeedbackDTO feedbackDTO);
}
