package com.example.demo.dto.security;


import com.example.demo.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

        private String userId;

        private String password;

        private String email;

        private String nickname;

        public static AccountDto of(String userId,String password,String email,String nickname){
            return new AccountDto(userId,password,email,nickname);
        }

        public static AccountDto from(Account account){
                return of(
                        account.getUserId(),
                        account.getPassword(),
                        account.getEmail(),
                        account.getNickname()
                );
        }

        public Account toEntity(){
            return Account.of(
                    userId,
                    password,
                    email,
                    nickname
            );
        }

}
