package com.poly.lab4.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;


@AllArgsConstructor
@Builder
@Data
public class Staff {
private String id;
private String fullname;
@Default
private String photo = "photo.jpg";
@Default
private Boolean gender;
@Default
@DateTimeFormat(
pattern="MM/dd/yyyy")
private Date birthday = new Date();
@Default
private Double salary = 12345.6789;
@Default
private Integer level = 0;
}