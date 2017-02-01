package com.udemy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.udemy.component.ContactConverter;
import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;
import com.udemy.repository.ContactRepository;
import com.udemy.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel cm) {
		return contactConverter.convertContact2ContactModel(contactRepository.save(contactConverter.convertContactModel2Contact(cm)));
	}

	@Override
	public List<ContactModel> listContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactModels = new ArrayList<>();
		for (Contact contact : contacts){
			contactModels.add(contactConverter.convertContact2ContactModel(contact));
		}
		return contactModels;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}

	@Override
	public void removeContact(int id) {
		Contact c = findContactById(id);
		if (c != null)
			contactRepository.delete(c);
	}

	@Override
	public ContactModel findContactModelById(int id) {
		return contactConverter.convertContact2ContactModel(findContactById(id));
	}
	


}
