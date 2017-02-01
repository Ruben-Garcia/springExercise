package com.udemy.service;

import java.util.List;

import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;

public interface ContactService {

	public abstract ContactModel addContact(ContactModel cm);
	
	public abstract List<ContactModel> listContacts();

	public abstract Contact findContactById(int id);
	
	public abstract ContactModel findContactModelById(int id);

	public abstract void removeContact(int id);
}
