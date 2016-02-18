package com.karol.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karol.dao.PersonDAO;
import com.karol.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
     
	@Inject
    private PersonDAO personDAO;
 
//    public void setPersonDAO(PersonDAO personDAO) {
//        this.personDAO = personDAO;
//        
//        
//    }
 
    @Override
    @Transactional
    public void addPerson(Person p) {
        this.personDAO.addPerson(p);
    }
 
    @Override
    @Transactional
    public void updatePerson(Person p) {
        this.personDAO.updatePerson(p);
    }
 
    @Override
    @Transactional
    public List<Person> listPersons() {
        return this.personDAO.listPersons();
    }
 
    @Override
    @Transactional
    public Person getPersonById(int id) {
        return this.personDAO.getPersonById(id);
    }
 
    @Override
    @Transactional
    public void removePerson(int id) {
        this.personDAO.removePerson(id);
    }
 
}
