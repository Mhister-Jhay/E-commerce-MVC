package org.jhay.flutterwave;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private String message;
    private String status;
    private Data data;
}
