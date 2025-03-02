package com.addressbookapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Generates Getters, Setters, toString, equals, hashCode
@AllArgsConstructor // Generates Constructor with All Fields
@NoArgsConstructor  // Generates Default Constructor
public class AddressBookEntryDTO {
    private String name;
    private String email;
    private String phone;
}



