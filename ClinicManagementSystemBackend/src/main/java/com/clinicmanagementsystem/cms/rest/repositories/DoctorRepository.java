package com.clinicmanagementsystem.cms.rest.repositories;

import com.clinicmanagementsystem.cms.model.Doctor;
import com.clinicmanagementsystem.cms.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "doctor", path="doctor" )
public interface DoctorRepository extends MongoRepository<Doctor, String>{
    public Doctor findByPesel(String pesel);
}
