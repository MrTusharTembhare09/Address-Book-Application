package com.addressbookapplication.util;

import com.addressbookapplication.dto.AddressBookEntryDTO;
import com.addressbookapplication.model.AddressBookEntry;

public class AddressBookEntryConverter {
    public static AddressBookEntry toEntity(AddressBookEntryDTO dto) {
        return new AddressBookEntry(null, dto.getName(), dto.getEmail(), dto.getPhone());
    }

    public static AddressBookEntryDTO toDTO(AddressBookEntry entry) {
        return new AddressBookEntryDTO(entry.getName(), entry.getEmail(), entry.getPhone());
    }
}

