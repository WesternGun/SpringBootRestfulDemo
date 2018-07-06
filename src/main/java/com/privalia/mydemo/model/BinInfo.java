package com.privalia.mydemo.model;

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

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@JsonComponent
@NoArgsConstructor
@Table(name="bin_info")
public class BinInfo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("bin_id")
    private Long id;
    
    @NotBlank
    @NotNull
    @Size(min=6, max=8)
    @Column(name="bin")
    @JsonProperty("bin")
    private String bin;
    
    @NotNull
    @NotEmpty
    @Column(name="json_full")
    @JsonProperty("json_full")
    private String json_full;
    
    @NotNull
    @Column(name="insert_date")
    // Formats output date when this DTO is passed through JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    // Allows dd/MM/yyyy date to be passed into GET request in JSON
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("createAt")
    private Date createAt;
    
    public BinInfo(String bin, String json_full, Date createAt) {
        this.bin = bin;
        this.json_full = json_full;
        this.createAt = createAt;
    }
}
