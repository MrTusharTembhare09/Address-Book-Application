package com.addressbookapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookEntryDTO {
    private String name;
    private String email;
    private String phone;
}

