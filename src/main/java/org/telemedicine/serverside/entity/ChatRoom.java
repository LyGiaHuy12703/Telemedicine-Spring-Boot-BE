package org.telemedicine.serverside.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String message;
    LocalDateTime createdAt;
    Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonBackReference
    User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonBackReference
    User receiver;
}
