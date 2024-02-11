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
@AllArgsConstructor
public class LearnerLicenseApplication extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
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

    @ManyToMany
    @JoinTable(name = "application_application_type",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "application_type_id"))
    private Set<ApplicationType> applicationTypes=new HashSet<>();
    
    @Column(name = "approval_time")
    private LocalDateTime approvalTime;
}
