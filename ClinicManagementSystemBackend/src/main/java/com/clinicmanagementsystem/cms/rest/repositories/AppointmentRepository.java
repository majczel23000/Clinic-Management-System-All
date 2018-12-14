package com.clinicmanagementsystem.cms.rest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "appointment", path="appointment" )
public interface AppointmentRepository extends MongoRepository<AppointmentRepository, String>{

}
