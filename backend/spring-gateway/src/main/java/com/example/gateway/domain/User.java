package com.example.gateway.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column("user_id")
    private Long userId;

    @Column("user_name")
    private String userName;

    @Column("email")
    private String email;


}
