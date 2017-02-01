package com.udemy.component;

import org.springframework.stereotype.Component;

import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {

	public Contact convertContactModel2Contact(ContactModel cm){
		Contact contact = new Contact();
		contact.setCity(cm.getCity());
		contact.setFirstname(cm.getFirstname());
		contact.setId(cm.getId());
		contact.setLastname(cm.getLastname());
		contact.setTelephone(cm.getTelephone());
		return contact;
	}
	
	public ContactModel convertContact2ContactModel(Contact c){
		ContactModel contactModel = new ContactModel();
		contactModel.setCity(c.getCity());
		contactModel.setFirstname(c.getFirstname());
		contactModel.setId(c.getId());
		contactModel.setLastname(c.getLastname());
		contactModel.setTelephone(c.getTelephone());
		return contactModel;
	}
}
