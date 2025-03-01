package com.addressbookapplication.service;

import com.addressbookapplication.model.AddressBookEntry;
import com.addressbookapplication.repository.AddressBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;

    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    public List<AddressBookEntry> getAllEntries() {
        return addressBookRepository.findAll();
    }

    public AddressBookEntry getEntryById(Long id) {
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public AddressBookEntry saveEntry(AddressBookEntry entry) {
        return addressBookRepository.save(entry);
    }

    public AddressBookEntry updateEntry(Long id, AddressBookEntry newEntry) {
        return addressBookRepository.findById(id)
                .map(existingEntry -> {
                    existingEntry.setName(newEntry.getName());
                    existingEntry.setEmail(newEntry.getEmail());
                    existingEntry.setPhone(newEntry.getPhone());
                    return addressBookRepository.save(existingEntry);
                }).orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public void deleteEntry(Long id) {
        addressBookRepository.deleteById(id);
    }
}

