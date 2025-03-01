package com.addressbookapplication.controller;

import com.addressbookapplication.dto.AddressBookEntryDTO;
import com.addressbookapplication.model.AddressBookEntry;
import com.addressbookapplication.service.AddressBookService;
import com.addressbookapplication.util.AddressBookEntryConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/address-book")
public class AddressBookController {

    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping
    public ResponseEntity<List<AddressBookEntryDTO>> getAllEntries() {
        List<AddressBookEntryDTO> entries = addressBookService.getAllEntries()
                .stream()
                .map(AddressBookEntryConverter::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntryDTO> getEntryById(@PathVariable Long id) {
        AddressBookEntry entry = addressBookService.getEntryById(id);
        return ResponseEntity.ok(AddressBookEntryConverter.toDTO(entry));
    }

    @PostMapping
    public ResponseEntity<AddressBookEntryDTO> createEntry(@RequestBody AddressBookEntryDTO entryDTO) {
        AddressBookEntry savedEntry = addressBookService.saveEntry(AddressBookEntryConverter.toEntity(entryDTO));
        return ResponseEntity.ok(AddressBookEntryConverter.toDTO(savedEntry));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBookEntryDTO> updateEntry(@PathVariable Long id, @RequestBody AddressBookEntryDTO entryDTO) {
        AddressBookEntry updatedEntry = addressBookService.updateEntry(id, AddressBookEntryConverter.toEntity(entryDTO));
        return ResponseEntity.ok(AddressBookEntryConverter.toDTO(updatedEntry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        addressBookService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}



