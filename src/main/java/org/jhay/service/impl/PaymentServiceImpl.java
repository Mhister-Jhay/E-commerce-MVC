package org.jhay.service.impl;


import lombok.RequiredArgsConstructor;
import org.jhay.flutterwave.Payment;
import org.jhay.flutterwave.PaymentResponse;
import org.jhay.flutterwave.Transaction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl {
    private final RestTemplate restTemplate;



    public String makePayment(Payment payment) {
        String url = "https://api.flutterwave.com/v3/payments";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("FLWSECK_TEST-7db74521eb0a68c0ef50afbf000cab42-X");
        HttpEntity<Payment> entity = new HttpEntity<>(payment,headers);
        ResponseEntity<PaymentResponse> response =
                restTemplate.exchange(url, HttpMethod.POST,entity, PaymentResponse.class);
        PaymentResponse paymentResponse = response.getBody();
        assert paymentResponse != null;
        return paymentResponse.getData().getLink();
    }

    public String transactionStatus(String status, String txRef, String transaction_id) {
        Transaction transaction = Transaction.builder()
                .status(status)
                .tx_ref(txRef)
                .transaction_id(transaction_id)
                .build();
        return "Your Payment was "+ transaction.getStatus();
    }
}
