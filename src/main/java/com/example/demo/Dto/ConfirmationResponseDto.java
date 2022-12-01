package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmationResponseDto {
    private Boolean confirmed;
    private Long confirmationRequestId;
}
