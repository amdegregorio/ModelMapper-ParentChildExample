/**
 * 
 */
package com.amydegregorio.mmparentchildex.modelmapperparentchild.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amydegregorio.mmparentchildex.modelmapperparentchild.domain.Contact;
import com.amydegregorio.mmparentchildex.modelmapperparentchild.domain.PhoneNumber;
import com.amydegregorio.mmparentchildex.modelmapperparentchild.dto.ContactDto;
import com.amydegregorio.mmparentchildex.modelmapperparentchild.dto.PhoneNumberDto;
import com.amydegregorio.mmparentchildex.modelmapperparentchild.repository.ContactRepository;

/**
 * MVC Controller for contacts.
 * 
 * @author AMD
 */
@Controller
public class ContactController {
   @Autowired
   private ContactRepository contactRepository;
   private ModelMapper modelMapper;
   
   /**
    * Constructs a new ContactController with the autowired ModelMapper provided.
    * <br>
    * Adds custom converters to the injected Model Mapper instance.
    * 
    * @param modelMapper
    */
   @Autowired
   public ContactController(ModelMapper modelMapper) {
      this.modelMapper = modelMapper;
      this.modelMapper.addConverter(populateExistingNumbers);
      this.modelMapper.addConverter(handlePhoneNumbersEntered);
   }
   
   /**
    * Lists all the contacts.
    * 
    * @param model
    * @return
    */
   @RequestMapping("/")
   public String listAll(Model model) {
      List<Contact> contacts = contactRepository.findAll();
      
      List<ContactDto> contactDtos = contacts.stream().map(contact -> modelMapper.map(contact, ContactDto.class)).collect(Collectors.toList());
      model.addAttribute("contacts", contactDtos);
      return "contact/list";
   }
   
   /**
    * Goes to the page for adding an contact.
    * 
    * @param contactDto
    * @param model
    * @return
    */
   @RequestMapping(value="/contact/add", method=RequestMethod.GET)
   public String addContact(ContactDto contactDto, Model model) {
      contactDto.getPhoneNumbers().add(new PhoneNumberDto());
      model.addAttribute("action", "contact/add");
      return "contact/entry";
   }
   
   /**
    * Saves a new contact if valid.
    * 
    * @param contactDto
    * @param bindingResult
    * @param model
    * @return
    */
   @RequestMapping(value="/contact/add",  params={"save"}, method=RequestMethod.POST)
   public String saveNewContact(@Valid ContactDto contactDto, BindingResult bindingResult, Model model) {
      if (bindingResult.hasErrors()) {
         model.addAttribute("action", "contact/add");
         return "contact/entry";
      }
      
      Contact contact = modelMapper.map(contactDto, Contact.class);
      contactRepository.save(contact);
      return "redirect:/";
   }
   
   /**
    * Cancels the add contact request and goes back to the list without saving.
    * 
    * @return
    */
   @RequestMapping(value="/contact/add", params={"cancel"}, method=RequestMethod.POST)
   public String cancelNewContact() {
      return "redirect:/";
   }
   
   @RequestMapping(value="/contact/edit", method=RequestMethod.GET)
   public String editContact(ContactDto contactDto, Model model, @RequestParam("id") Long id) {
      model.addAttribute("action", "contact/edit");
      Contact contact = contactRepository.getOne(id);
      System.out.println("Domain: " + contact.toString());
      contactDto = modelMapper.map(contact, ContactDto.class);
      contactDto.getPhoneNumbers().add(new PhoneNumberDto());
      model.addAttribute("contactDto", contactDto);
      return "contact/entry";
   }
   
   @RequestMapping(value="/contact/edit",  params={"save"}, method=RequestMethod.POST)
   public String saveContact(@Valid ContactDto contactDto, BindingResult bindingResult, Model model) {
      if (bindingResult.hasErrors()) {
         model.addAttribute("action", "contact/edit");
         return "contact/entry";
      }
      
      Contact contact = modelMapper.map(contactDto, Contact.class);
      contactRepository.save(contact);
      return "redirect:/";
   }
   
   @RequestMapping(value="/contact/edit", params={"cancel"}, method=RequestMethod.POST)
   public String cancelContact() {
      return "redirect:/";
   }
   
   Converter<Contact, ContactDto> populateExistingNumbers = new Converter<Contact, ContactDto>() {

      @Override
      public ContactDto convert(MappingContext<Contact, ContactDto> context) {
         //This custom converter replaces the one automatically created by ModelMapper,
         //So we have to map each of the contact fields as well.
         context.getDestination().setId(context.getSource().getId());
         context.getDestination().setFirstName(context.getSource().getFirstName());
         context.getDestination().setLastName(context.getSource().getLastName());
         context.getDestination().setTitle(context.getSource().getTitle());
         context.getDestination().setCompany(context.getSource().getCompany());
         context.getDestination().setNotes(context.getSource().getNotes());
         
         //Add an empty phone number for adding a new number
         context.getDestination().getPhoneNumbers().add(new PhoneNumberDto());
         
         for (PhoneNumber number : context.getSource().getPhoneNumbers()) {
            context.getDestination().getPhoneNumbers().add(new PhoneNumberDto(number.getId(), number.getType(), number.getNumber()));
         }
         return context.getDestination();
      }
   };
   
   Converter<ContactDto, Contact> handlePhoneNumbersEntered = new Converter<ContactDto, Contact>() {

      @Override
      public Contact convert(MappingContext<ContactDto, Contact> context) {
         //This custom converter replaces the one automatically created by ModelMapper,
         //So we have to map each of the contact fields as well.
         context.getDestination().setId(context.getSource().getId());
         context.getDestination().setFirstName(context.getSource().getFirstName());
         context.getDestination().setLastName(context.getSource().getLastName());
         context.getDestination().setTitle(context.getSource().getTitle());
         context.getDestination().setCompany(context.getSource().getCompany());
         context.getDestination().setNotes(context.getSource().getNotes());
         
         //if new, we just have to create phone numbers for each number, so deal with that first
         if (context.getSource().getId() == null) {
            for (PhoneNumberDto numberDto : context.getSource().getPhoneNumbers()) {
               context.getDestination().addPhoneNumber(numberDto.getType(), numberDto.getPhoneNumber());
            }
         } else {
            Contact existing = contactRepository.getOne(context.getSource().getId());
            context.getDestination().getPhoneNumbers().clear();
            
            for (PhoneNumberDto phoneNumDto : context.getSource().getPhoneNumbers()) {
               boolean found = false;
               //For each phone number coming in from the form, check the existing phone numbers
               //from the database.  If there's a match, update the phone number object and add it to the destination 
               //phone numbers collection, unless it's deleted and then we leave it out of the collection.
               for (PhoneNumber phoneNumber : existing.getPhoneNumbers()) {
                  if (phoneNumDto.getId() != null && phoneNumDto.getId().longValue() == phoneNumber.getId().longValue()) {
                     found = true;
                     if (!phoneNumDto.isDeleted()) {
                        phoneNumber.setType(phoneNumDto.getType());
                        phoneNumber.setNumber(phoneNumDto.getPhoneNumber());
                        context.getDestination().getPhoneNumbers().add(phoneNumber);
                     }
                     break;
                  }
               }
               //For input phone numbers that aren't in the database, add it to the destination.
               if (!found && !phoneNumDto.getPhoneNumber().isEmpty()) {
                  context.getDestination().addPhoneNumber(phoneNumDto.getType(), phoneNumDto.getPhoneNumber());
               }
            }
         }
         
         return context.getDestination();
      }
      
   };
}
