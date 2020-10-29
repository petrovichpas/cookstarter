package ru.guteam.restaurantservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.guteam.restaurantservice.exception.ContactNotFoundException;
import ru.guteam.restaurantservice.model.Contact;
import ru.guteam.restaurantservice.repo.ContactRepo;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepo contactRepo;

    @Override
    @Transactional
    public void saveContact(Contact contact) {
        contactRepo.save(contact);
    }

    @Override
    public Contact getContactByRestaurantId(Long restaurant_id) {
        Contact contact = contactRepo.findByRestaurantId(restaurant_id).orElseThrow(ContactNotFoundException::new);
        return contact;
    }

    @Override
    @Transactional
    public void updateContact(Long restaurant_id, Contact contact) {
        Contact oldContact = getContactByRestaurantId(restaurant_id);
        contact.setId(oldContact.getId());
        saveContact(contact);
    }

    @Override
    @Transactional
    public void deleteContactByRestaurantId(Long restaurant_id) {
        contactRepo.deleteByRestaurantId(restaurant_id);
    }
}
