package com.sparta.hanghae_magazine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RegisterRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]{3,20}", message = "아이디는 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하기")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Min(4)
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String realName;

    @Builder
    public RegisterRequestDto(String username, String password, String realName) {
        this.username = username;
        this.password = password;
        this.realName = realName;
    }
}
