/**
 * 
 */
package com.amydegregorio.mmparentchildex.modelmapperparentchild.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Represents a phone number entry on a contact.
 * 
 * @author AMD
 */
@Entity
public class PhoneNumber {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private Long id;
   private String type;
   private String number;
   @ManyToOne
   @JoinColumn(name="contact_id")
   private Contact contact;
   
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
    * Gets type.
    * @return the type
    */
   public String getType() {
      return type;
   }
   
   /**
    * Sets type.
    * @param type the type to set
    */
   public void setType(String type) {
      this.type = type;
   }
   
   /**
    * Gets number.
    * @return the number
    */
   public String getNumber() {
      return number;
   }
   
   /**
    * Sets number.
    * @param number the number to set
    */
   public void setNumber(String number) {
      this.number = number;
   }

   /**
    * Gets contact.
    * @return the contact
    */
   public Contact getContact() {
      return contact;
   }

   /**
    * Sets contact.
    * @param contact the contact to set
    */
   public void setContact(Contact contact) {
      this.contact = contact;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return "PhoneNumber [id=" + id + ", type=" + type + ", number=" + number + ", contact=" + contact + "]";
   }

   
}
