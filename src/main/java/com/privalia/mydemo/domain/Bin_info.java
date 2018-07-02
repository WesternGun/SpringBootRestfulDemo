package com.privalia.mydemo.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="bin_info")
public class Bin_info implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @NotNull
    @Size(min=6, max=8)
    @Column(name="bin")
    private String bin;
    
    @NotNull
    @NotEmpty
    @Column(name="json_full")
    private String json_full;
    
    @NotNull
    @Column(name="insert_date")
    private Date createAt;
    
    public Bin_info(String bin, String json_full, Date createAt) {
        this.bin = bin;
        this.json_full = json_full;
        this.createAt = createAt;
    }
}
