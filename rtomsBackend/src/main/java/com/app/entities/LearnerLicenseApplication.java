package com.app.entities;
import javax.persistence.*;

import com.app.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "learner_license_applications")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString(callSuper = true/*, exclude = { "profilePhoto", "educationalDocument","addressProof" }*/)
public class LearnerLicenseApplication extends BaseEntity {
	
	public LearnerLicenseApplication(String firstName, String middleName, String lastName, String mobileNumber,
			PostalAddress postalAddress, Gender gender, BloodGroup bloodGroup, LocalDate dateOfBirth,
			RtoOffice rtoOffice, Qualification qualification, byte[] profilePhoto, byte[] educationalDocument,
			byte[] addressProof, LocalDateTime entryTime, LocalDateTime approvalTime) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.postalAddress = postalAddress;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.dateOfBirth = dateOfBirth;
		this.rtoOffice = rtoOffice;
		this.qualification = qualification;
		this.profilePhoto = profilePhoto;
		this.educationalDocument = educationalDocument;
		this.addressProof = addressProof;
		this.entryTime = entryTime;
		this.approvalTime = approvalTime;
	}

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique=true)
	private User user;
	
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "last_name")
    private String lastName;
      
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postal_address_id", referencedColumnName = "id")
    private PostalAddress postalAddress;
    
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "blood_group")
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "rto_office")
    private RtoOffice rtoOffice;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "qualification")
    private Qualification qualification;
    
    @Lob
    @Column(name = "profile_photo")
    private byte[] profilePhoto;
    
    @Lob
    @Column(name = "educational_document")
    private byte[] educationalDocument;
    
    @Lob
    @Column(name = "address_proof")
    private byte[] addressProof;
    
    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "application_application_type",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "application_type_id"))
    private Set<ApplicationType> applicationTypes=new HashSet<>();
    
    @Column(name = "approval_time")
    private LocalDateTime approvalTime;
    
    public void addType(ApplicationType applicationType) {
        this.applicationTypes.add(applicationType);
        applicationType.getLearnerLicenseApplications().add(this);
    }
}
