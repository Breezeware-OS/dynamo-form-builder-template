package net.breezeware.dynamo.forms.svc.dao;

import org.springframework.stereotype.Repository;

import net.breezeware.dynamo.forms.svc.entity.FormResponse;
import net.breezeware.dynamo.generics.crud.dao.GenericRepository;

/**
 * Repository interface for managing and accessing FormResponse entities.
 * Extends the GenericRepository interface for common CRUD operations and
 * defines a custom query methods.
 */
@Repository
public interface FormResponseRepository extends GenericRepository<FormResponse> {

}