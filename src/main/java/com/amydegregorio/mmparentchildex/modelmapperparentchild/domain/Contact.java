/**
 * 
 */
package com.amydegregorio.mmparentchildex.modelmapperparentchild.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Represents a contact.
 * 
 * @author AMD
 */
@Entity
public class Contact {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private Long id;
   private String firstName;
   private String lastName;
   private String title;
   private String company;
   private String notes;
   @OneToMany(mappedBy="contact", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
   private List<PhoneNumber> phoneNumbers;
   
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
   public List<PhoneNumber> getPhoneNumbers() {
      if (phoneNumbers == null) {
         phoneNumbers = new ArrayList<PhoneNumber>();
      }
      return phoneNumbers;
   }
   
   /**
    * Sets phoneNumbers.
    * @param phoneNumbers the phoneNumbers to set
    */
   public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
      this.phoneNumbers = phoneNumbers;
   }
   
   public void addPhoneNumber(String type, String phoneNumber) {
      PhoneNumber newNumber = new PhoneNumber();
      newNumber.setType(type);
      newNumber.setNumber(phoneNumber);
      newNumber.setContact(this);
      if (phoneNumbers == null) {
         phoneNumbers = new ArrayList<PhoneNumber>();
      }
      phoneNumbers.add(newNumber);
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title
               + ", company=" + company + ", notes=" + notes + ", phoneNumbers=" + phoneNumbers.size() + "]";
   }
}
