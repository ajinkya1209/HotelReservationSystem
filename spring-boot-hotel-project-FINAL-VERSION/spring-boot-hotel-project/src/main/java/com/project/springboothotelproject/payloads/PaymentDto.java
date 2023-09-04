package com.project.springboothotelproject.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.springboothotelproject.enitites.PaymentMode;
import com.project.springboothotelproject.exceptionhandling.ValidPaymentMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Guest id is required")
    private Long guestId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Room id is required")
    private Long roomId;

    @ValidPaymentMode  //Custom validation
    private PaymentMode paymentMode;

    @NotNull(message = "Payment amount is required")
    @Positive(message = "Payment amount can't be a negative value")
    private Integer paymentAmount;
}
