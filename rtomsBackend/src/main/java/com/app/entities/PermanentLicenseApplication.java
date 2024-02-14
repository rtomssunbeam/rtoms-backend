package com.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.enums.BloodGroup;
import com.app.enums.Gender;
import com.app.enums.Qualification;
import com.app.enums.RtoOffice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "permanent_license_applications")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString(callSuper = true/*, exclude = { "profilePhoto", "educationalDocument","addressProof" }*/)
public class PermanentLicenseApplication extends BaseEntity {

//	@OneToOne
//	@JoinColumn(name = "user_id", nullable = false, unique=true)
//	private User user;
	
}
