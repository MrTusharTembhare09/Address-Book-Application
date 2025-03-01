package com.addressbookapplication.service;

import com.addressbookapplication.dto.AddressBookEntryDTO;
import com.addressbookapplication.model.AddressBookEntry;
import com.addressbookapplication.util.AddressBookEntryConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private final List<AddressBookEntry> addressBookEntries = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Auto-incrementing ID

    public List<AddressBookEntryDTO> getAllEntries() {
        return addressBookEntries.stream()
                .map(AddressBookEntryConverter::toDTO)
                .collect(Collectors.toList());
    }

    public AddressBookEntryDTO getEntryById(Long id) {
        return addressBookEntries.stream()
                .filter(entry -> entry.getId().equals(id))
                .findFirst()
                .map(AddressBookEntryConverter::toDTO)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public AddressBookEntryDTO createEntry(AddressBookEntryDTO entryDTO) {
        AddressBookEntry entry = AddressBookEntryConverter.toEntity(entryDTO);
        entry.setId(idCounter.getAndIncrement()); // Assign unique ID
        addressBookEntries.add(entry);
        return AddressBookEntryConverter.toDTO(entry);
    }

    public AddressBookEntryDTO updateEntry(Long id, AddressBookEntryDTO entryDTO) {
        Optional<AddressBookEntry> existingEntryOpt = addressBookEntries.stream()
                .filter(entry -> entry.getId().equals(id))
                .findFirst();

        if (existingEntryOpt.isEmpty()) {
            throw new RuntimeException("Entry not found");
        }

        AddressBookEntry existingEntry = existingEntryOpt.get();
        existingEntry.setName(entryDTO.getName());
        existingEntry.setEmail(entryDTO.getEmail());
        existingEntry.setPhone(entryDTO.getPhone());

        return AddressBookEntryConverter.toDTO(existingEntry);
    }

    public void deleteEntry(Long id) {
        addressBookEntries.removeIf(entry -> entry.getId().equals(id));
    }
}



