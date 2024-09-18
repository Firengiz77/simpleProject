package com.bookstore.simpleblog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;


    @Column(name = "file", length = 1000)
    private String file;

//    @OneToOne(mappedBy = "image")
//    private About about;
//
//    @OneToOne(mappedBy = "image")
//    private Project project;
//
//    @OneToOne(mappedBy = "image")
//    private Video video;
}
