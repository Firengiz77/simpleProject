package com.bookstore.simpleblog.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    //    @OneToOne()
//    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @OneToOne
    @JoinColumn(name = "image_id",referencedColumnName = "id")
    private Image image;
}
