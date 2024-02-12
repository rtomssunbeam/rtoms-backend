package com.app.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.app.entities.ApplicationType;
import com.app.entities.PostalAddress;
import com.app.entities.User;
import com.app.enums.BloodGroup;
import com.app.enums.Gender;
import com.app.enums.Qualification;
import com.app.enums.RtoOffice;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LearningLicenseApplicationDTO {

	@JsonProperty(access = Access.READ_ONLY) // used during serialization
	private Integer id;

	@NotBlank(message = "First name is required")
	@Size(max = 20, message = "First name must be at most 20 characters")
	private String firstName;

	@NotBlank(message = "Middle name is required")
	@Size(max = 20, message = "Middle name must be at most 20 characters")
	private String middleName;

	@NotBlank(message = "Last name is required")
	@Size(max = 20, message = "Last name must be at most 20 characters")
	private String lastName;

	@NotBlank(message = "Mobile number is required")
	private String mobileNumber;

	// used during de-serialization
	private Integer userId;

	private PostalAddressDTO postalAddressDTO;

	@NotBlank(message = "Gender is required")
	private Gender gender;

	@NotBlank(message = "Blood Group is required")
	private BloodGroup bloodGroup;

	@Past(message = "the date must be from past")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Rto Office is required")
	private RtoOffice rtoOffice;

	@NotBlank(message = "Qualification is required")
	private Qualification qualification;

	
	private MultipartFile profilePhoto;

	@JsonProperty(access = Access.WRITE_ONLY) // used during de-serialization
	private MultipartFile educationalDocument;

	@JsonProperty(access = Access.WRITE_ONLY) // used during de-serialization
	private MultipartFile addressProof;


	private Set<String> applicationTypes;
	
	
	


}
