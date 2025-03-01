package com.addressbookapplication.service;

import com.addressbookapplication.dto.AddressBookEntryDTO;
import com.addressbookapplication.model.AddressBookEntry;
import com.addressbookapplication.repository.AddressBookRepository;
import com.addressbookapplication.util.AddressBookEntryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;

    // Injecting Repository using @Autowired (Constructor-based Dependency Injection)
    @Autowired
    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    public List<AddressBookEntryDTO> getAllEntries() {
        return addressBookRepository.findAll()
                .stream()
                .map(AddressBookEntryConverter::toDTO)
                .collect(Collectors.toList());
    }

    public AddressBookEntryDTO getEntryById(Long id) {
        AddressBookEntry entry = addressBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        return AddressBookEntryConverter.toDTO(entry);
    }

    public AddressBookEntryDTO createEntry(AddressBookEntryDTO entryDTO) {
        AddressBookEntry entry = AddressBookEntryConverter.toEntity(entryDTO);
        AddressBookEntry savedEntry = addressBookRepository.save(entry);
        return AddressBookEntryConverter.toDTO(savedEntry);
    }

    public AddressBookEntryDTO updateEntry(Long id, AddressBookEntryDTO entryDTO) {
        return addressBookRepository.findById(id)
                .map(existingEntry -> {
                    existingEntry.setName(entryDTO.getName());
                    existingEntry.setEmail(entryDTO.getEmail());
                    existingEntry.setPhone(entryDTO.getPhone());
                    AddressBookEntry updatedEntry = addressBookRepository.save(existingEntry);
                    return AddressBookEntryConverter.toDTO(updatedEntry);
                }).orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public void deleteEntry(Long id) {
        if (!addressBookRepository.existsById(id)) {
            throw new RuntimeException("Entry not found");
        }
        addressBookRepository.deleteById(id);
    }
}


