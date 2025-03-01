package com.addressbookapplication.controller;

import com.addressbookapplication.model.AddressBookEntry;
import com.addressbookapplication.repository.AddressBookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    private final AddressBookRepository repository;

    public AddressBookController(AddressBookRepository repository) {
        this.repository = repository;
    }

    // Create a new entry (POST)
    @PostMapping("/add")
    public ResponseEntity<AddressBookEntry> addEntry(@RequestBody AddressBookEntry entry) {
        AddressBookEntry savedEntry = repository.save(entry);
        return ResponseEntity.ok(savedEntry);
    }

    // Get all entries (GET)
    @GetMapping("/entries")
    public ResponseEntity<List<AddressBookEntry>> getAllEntries() {
        List<AddressBookEntry> entries = repository.findAll();
        return ResponseEntity.ok(entries);
    }

    // Get an entry by ID (GET)
    @GetMapping("/entries/{id}")
    public ResponseEntity<AddressBookEntry> getEntryById(@PathVariable Long id) {
        Optional<AddressBookEntry> entry = repository.findById(id);
        return entry.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an entry by ID (PUT)
    @PutMapping("/entries/{id}")
    public ResponseEntity<AddressBookEntry> updateEntry(@PathVariable Long id, @RequestBody AddressBookEntry updatedEntry) {
        return repository.findById(id)
                .map(existingEntry -> {
                    existingEntry.setName(updatedEntry.getName());
                    existingEntry.setPhone(updatedEntry.getPhone());
                    existingEntry.setEmail(updatedEntry.getEmail());
                    repository.save(existingEntry);
                    return ResponseEntity.ok(existingEntry);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an entry by ID (DELETE)
    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


