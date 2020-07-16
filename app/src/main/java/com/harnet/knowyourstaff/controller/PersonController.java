package com.harnet.knowyourstaff.controller;

import com.harnet.knowyourstaff.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonController {
    private static PersonController instance;
    private List<Person> staff = new ArrayList<>();
    private WebContentController webContentController;

    private PersonController() {
        //TODO if a web page haven't been modified - retrieve staff list from local storage
        webContentController = new WebContentController();
        makeStaffList();
    }

    public static PersonController getInstance() {
        if(instance == null){
            instance = new PersonController();
        }
        return instance;
    }

    public List<Person> getStaff() {
        return staff;
    }

    // generate staff list from webController data
    public void makeStaffList(){
        for(int i = 0; i< webContentController.getStaffNames().size(); i++){
            addPersonToStaff(webContentController.getStaffNames().get(i), webContentController.getStaffPhotoLink().get(i));
        }
    }

    // add person to staff
    public void addPersonToStaff(String name, String photoLink){
        staff.add(new Person( name, photoLink));
    }

    // mark a person as guessed
    public Person getPersonByName(final String personName){
        Optional<Person> staffOpt = PersonController.getInstance().getStaff().stream()
                .filter(p -> p.getName().equals(personName))
                .findFirst();

        return staffOpt.orElse(null);
    }

    // argument null - staff which haven't shown, true - guessed, false - not guessed
    public List<Person> getStaffListByGuess(Boolean isGuessed){
        return staff.stream()
                .filter(p -> p.isGuessed() == isGuessed)
                .collect(Collectors.toList());
    }
}
