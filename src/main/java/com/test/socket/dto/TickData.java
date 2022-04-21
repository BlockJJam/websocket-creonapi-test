package com.test.socket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TickData {
    String code;
    String timess;
    String cPrice;

    public TickData(String code, Object timess, Object cPrice) {
        this.code = code;
        this.timess = timess.toString();
        this.cPrice = cPrice.toString();
    }
}
