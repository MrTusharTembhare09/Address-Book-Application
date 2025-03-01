package com.addressbookapplication.controller;

import com.addressbookapplication.dto.AddressBookEntryDTO;
import com.addressbookapplication.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address-book")
public class AddressBookController {

    private final AddressBookService addressBookService;

    // Injecting Service Layer using @Autowired (recommended way via constructor)
    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping
    public ResponseEntity<List<AddressBookEntryDTO>> getAllEntries() {
        return ResponseEntity.ok(addressBookService.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntryDTO> getEntryById(@PathVariable Long id) {
        return ResponseEntity.ok(addressBookService.getEntryById(id));
    }

    @PostMapping
    public ResponseEntity<AddressBookEntryDTO> createEntry(@RequestBody AddressBookEntryDTO entryDTO) {
        return ResponseEntity.ok(addressBookService.createEntry(entryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBookEntryDTO> updateEntry(@PathVariable Long id, @RequestBody AddressBookEntryDTO entryDTO) {
        return ResponseEntity.ok(addressBookService.updateEntry(id, entryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        addressBookService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}




