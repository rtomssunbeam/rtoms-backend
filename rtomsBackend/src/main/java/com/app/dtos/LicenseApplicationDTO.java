package com.app.dtos;
import com.app.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
// Import the Status enum from your package
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LicenseApplicationDTO {
    private Integer id;
    private String name;
    private Status status;
}
