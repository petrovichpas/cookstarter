package ru.guteam.restaurantservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.guteam.restaurantservice.model.Contact;
import ru.guteam.restaurantservice.service.ContactService;

import static ru.guteam.restaurantservice.util.RequestHeaders.JWT_HEADER;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity addContact(@RequestHeader(JWT_HEADER) String token,
                                     @RequestBody Contact contact) {
        contactService.saveContact(contact);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/get/{restaurant_id}")
    public ResponseEntity<Contact> getContact(@RequestHeader(JWT_HEADER) String token,
                                              @PathVariable Long restaurant_id) {
        Contact contact = contactService.getContactByRestaurantId(restaurant_id);
        return new ResponseEntity(contact, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("update/{restaurant_id}")
    public ResponseEntity updateContact(@RequestHeader(JWT_HEADER) String token,
                                        @PathVariable Long restaurant_id,
                                        @RequestBody Contact contact) {
        contactService.updateContact(restaurant_id, contact);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/delete/{restaurant_id}")
    public ResponseEntity deleteContact(@RequestHeader(JWT_HEADER) String token,
                                        @PathVariable Long restaurant_id) {
        contactService.deleteContactByRestaurantId(restaurant_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
