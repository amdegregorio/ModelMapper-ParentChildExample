/**
 * 
 */
package com.amydegregorio.mmparentchildex.modelmapperparentchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amydegregorio.mmparentchildex.modelmapperparentchild.domain.Contact;

/**
 * @author AMD
 *
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
