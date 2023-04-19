package com.etiya.ecommerceDemo.entities.concrete;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review_replies")
public class ReviewReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "reply_date")
    private Date replyDate;

    @Column(name = "reply_text")
    private String replyText;

    /// doldurulacak private review ;

    /// doldurulacak private user attribute;

}