package com.sparta.hanghae_magazine.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Success {
    private boolean success;
    private String msg;
}
