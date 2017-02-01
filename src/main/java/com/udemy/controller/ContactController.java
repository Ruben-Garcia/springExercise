package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.constant.ViewConstant;
import com.udemy.model.ContactModel;
import com.udemy.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Log log = LogFactory.getLog(ContactController.class);

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;

	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}

	@PreAuthorize("permitAll()")
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name = "id", required = false) int id, Model model) {
		ContactModel cm = new ContactModel();
		
		if (id != 0 ){
			cm = contactService.findContactModelById(id);
		}
		model.addAttribute("contactModel", cm);
		return ViewConstant.CONTACT_FORM;
	}

	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name = "contactModel") ContactModel cm, Model model) {
		log.info("METHOD: addContact --params: " + cm.toString());

		if (contactService.addContact(cm) != null) {
			model.addAttribute("result", 1);
			return "redirect:/contacts/showcontacts";
		} else {
			model.addAttribute("result", 0);
		}
		return "redirect:/contacts/showcontacts";
	}

	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		mav.addObject("contacts", contactService.listContacts());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		return mav;
	}

	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name = "id", required = true) int id) {
		contactService.removeContact(id);
		return showContacts();
	}
}
