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

    @ManyToOne()
    @JoinColumn(name = "user_id")
     private User user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private ProductReview productReview;



}