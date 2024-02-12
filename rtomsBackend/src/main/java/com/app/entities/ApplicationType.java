package com.app.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "application_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationType extends BaseEntity {
    @Column(name = "application_type")
    private String applicationType;
    

    @ManyToMany(mappedBy = "applicationTypes",cascade = CascadeType.ALL)
    private Set<LearnerLicenseApplication> learnerLicenseApplications=new HashSet<>();


	@Override
	public String toString() {
		return "ApplicationType [" + applicationType + "]";
	}
}
