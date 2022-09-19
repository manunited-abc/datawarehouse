package com.example.datawarehouse.model;


import lombok.*;

@Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class Lottery {
        String company;
        String province;
        String issueDate ;
        String prize0;
        String prize1;
        String prize2;
        String prize3;
        String prize4;
        String prize5;
        String prize6;
        String prize7;
        String prize8;
        String infoPrize;
}
