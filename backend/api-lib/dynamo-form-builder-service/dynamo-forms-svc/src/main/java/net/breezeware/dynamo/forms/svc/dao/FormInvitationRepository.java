package net.breezeware.dynamo.forms.svc.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import net.breezeware.dynamo.forms.svc.entity.FormInvitation;
import net.breezeware.dynamo.generics.crud.dao.GenericRepository;

/**
 * Repository interface for managing and accessing FormResponse entities.
 * Extends the GenericRepository interface for common CRUD operations and
 * defines a custom query methods.
 */
@Repository
public interface FormInvitationRepository extends GenericRepository<FormInvitation> {

    Optional<FormInvitation> findByEmailAndFormId(String email, long fromId);

}