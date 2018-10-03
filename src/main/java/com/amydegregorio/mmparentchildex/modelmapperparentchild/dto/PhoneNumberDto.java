/**
 * 
 */
package com.amydegregorio.mmparentchildex.modelmapperparentchild.dto;

/**
 * @author AMD
 *
 */
public class PhoneNumberDto {
   private Long id;
   private String type;
   private String phoneNumber;
   private boolean deleted;
   
   
   /**
    * Creates an empty PhoneNumberDto object.
    */
   public PhoneNumberDto() {
      
   }
   
   /**
    * Creates a PhoneNumberDto with the values provided.
    * 
    * @param id
    * @param type
    * @param phoneNumber
    */
   public PhoneNumberDto(Long id, String type, String phoneNumber) {
      this.id = id;
      this.type = type;
      this.phoneNumber = phoneNumber;
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
    * Gets phoneNumber.
    * @return the phoneNumber
    */
   public String getPhoneNumber() {
      return phoneNumber;
   }
   
   /**
    * Sets phoneNumber.
    * @param phoneNumber the phoneNumber to set
    */
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   
   /**
    * Gets deleted.
    * @return the deleted
    */
   public boolean isDeleted() {
      return deleted;
   }

   
   /**
    * Sets deleted.
    * @param deleted the deleted to set
    */
   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }
}
