package com.addressbookapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBookEntry {
    private int id;
    private String name;
    private String phone;
    private String email;
}

