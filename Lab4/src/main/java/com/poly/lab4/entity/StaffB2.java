package com.poly.lab4.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class StaffB2 {
		@NotBlank(message = "Chưa nhập email")
		@Email(message = "Email không đúng định dạng")
		private String id;
		@NotBlank(message = "Chưa nhập họ và tên")
		private String fullname;
		@Default
		private String photo = "photo.jpg";
		@NotNull(message = "Chưa chọn giới tính")
		private Boolean gender;
		@NotNull(message = "Chưa nhập ngày sinh")
		@Past(message = "Ngày sinh không hợp lệ")
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date birthday;
		@Min(value = 1000, message = "Lương tối thiểu phải là 1000")
		@NotNull(message = "Chưa nhập lương")
		private Double salary;
		private Integer level;
}
