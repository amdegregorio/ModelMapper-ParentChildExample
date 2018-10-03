/**
 * 
 */
package com.amydegregorio.mmparentchildex.modelmapperparentchild.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for Contacts.
 * 
 * @author AMD
 */
public class ContactDto {
   private Long id;
   private String firstName;
   private String lastName;
   private String title;
   private String company;
   private String notes;
   private List<PhoneNumberDto> phoneNumbers;
   
   public ContactDto() {
      phoneNumbers = new ArrayList<PhoneNumberDto>();
   }
   
   /**
    * Gets id.
    * @return the id
    */
   public Long getId() {
      return id;
   }

   
   /**
    * Sets id.
    * @param id the id to set
    */
   public void setId(Long id) {
      this.id = id;
   }

   
   /**
    * Gets firstName.
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   
   /**
    * Sets firstName.
    * @param firstName the firstName to set
    */
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   
   /**
    * Gets lastName.
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   
   /**
    * Sets lastName.
    * @param lastName the lastName to set
    */
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   
   /**
    * Gets title.
    * @return the title
    */
   public String getTitle() {
      return title;
   }

   
   /**
    * Sets title.
    * @param title the title to set
    */
   public void setTitle(String title) {
      this.title = title;
   }

   
   /**
    * Gets company.
    * @return the company
    */
   public String getCompany() {
      return company;
   }

   
   /**
    * Sets company.
    * @param company the company to set
    */
   public void setCompany(String company) {
      this.company = company;
   }

   
   /**
    * Gets notes.
    * @return the notes
    */
   public String getNotes() {
      return notes;
   }

   
   /**
    * Sets notes.
    * @param notes the notes to set
    */
   public void setNotes(String notes) {
      this.notes = notes;
   }

   
   /**
    * Gets phoneNumbers.
    * @return the phoneNumbers
    */
   public List<PhoneNumberDto> getPhoneNumbers() {
      if (phoneNumbers == null) {
         phoneNumbers = new ArrayList<PhoneNumberDto>();
      }
      return phoneNumbers;
   }

   
   /**
    * Sets phoneNumbers.
    * @param phoneNumbers the phoneNumbers to set
    */
   public void setPhoneNumbers(List<PhoneNumberDto> phoneNumbers) {
      this.phoneNumbers = phoneNumbers;
   }


   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return "ContactDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title
               + ", company=" + company + ", notes=" + notes + ", phoneNumbers=" + phoneNumbers.size() + "]";
   }
}
