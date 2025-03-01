package com.addressbookapplication.controller;

import com.addressbookapplication.model.AddressBookEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {
    private final List<AddressBookEntry> addressBook = new ArrayList<>();

    // Creating a new entry
    @PostMapping("/add")
    public AddressBookEntry addEntry(@RequestBody AddressBookEntry entry) {
        entry.setId(addressBook.size() + 1);
        addressBook.add(entry);
        return entry;
    }

    // Retrieving all entries
    @GetMapping("/entries")
    public List<AddressBookEntry> getEntries() {
        return addressBook;
    }

    // Getting an entry by ID
    @GetMapping("/entries/{id}")
    public AddressBookEntry getEntry(@PathVariable int id) {
        return addressBook.stream()
                .filter(entry -> entry.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

