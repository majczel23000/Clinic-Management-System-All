package com.clinicmanagementsystem.cms.rest.repositories;

import com.clinicmanagementsystem.cms.model.Patient;
import com.clinicmanagementsystem.cms.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "patient", path="patient" )
public interface PatientRepository extends MongoRepository<Patient, String>{
    public Patient findByPesel(String pesel);
}
