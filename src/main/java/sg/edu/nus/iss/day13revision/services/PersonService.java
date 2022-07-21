package sg.edu.nus.iss.day13revision.services;


import org.springframework.stereotype.Service;
import sg.edu.nus.iss.day13revision.models.PersonModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<PersonModel> persons = new ArrayList<PersonModel>();

    public PersonService(){
        persons.add(new PersonModel("jimmy", "from bts"));
        persons.add(new PersonModel("yilon", "ma"));
    }

    public List<PersonModel> getPersons() {

        return this.persons;
    }
    public void addPerson(PersonModel p){
        persons.add(new PersonModel(p.getFirstName(), p.getLastName()));
    }
}
