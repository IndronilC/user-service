package com.shawbindro.userservice.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Indexed;


import java.time.LocalDateTime;

@Data
/*@Document(collection = "users")*/
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // private String keycloakId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;

  //  @Indexed(unique = true)

    private String phone;
    private UserRole role = UserRole.CUSTOMER;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id",
            unique = true
    )
    private Address address;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
