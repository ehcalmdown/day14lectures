package sg.edu.nus.iss.day13revision.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.day13revision.models.PersonForm;
import sg.edu.nus.iss.day13revision.models.PersonModel;
import sg.edu.nus.iss.day13revision.services.PersonService;
import java.util.ArrayList;
import java.util.List;

@Controller

public class PersonController {
    private List<PersonModel> personList = new ArrayList<PersonModel>();


    @Autowired
    PersonService perSvc;


    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value={"/","/home","/index"} , method=RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("message", message);
        return "index";

    }
    @RequestMapping(value="/testRetrieve", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PersonModel> getAllPerson(){
        personList = perSvc.getPersons();

        return personList;
    }
    @RequestMapping(value="/personList", method=RequestMethod.GET)
    public String personList(Model model){
       personList = perSvc.getPersons();
        model.addAttribute("persons",personList);
        return "personList";
    }
    @RequestMapping(value = "/addPerson",method = RequestMethod.GET)
    public String showAddPerson(Model model){
        PersonForm pForm = new PersonForm();
        model.addAttribute("personForm", pForm);

        return "addPerson";
    }
    @RequestMapping(value="/addPerson", method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm){

        String fName = personForm.getFirstName();
        String lName = personForm.getLastName();

        if(fName != null && fName.length() > 0 && lName !=null && lName.length()>0){
            PersonModel newPerson = new PersonModel(fName, lName);
            perSvc.addPerson(newPerson);

            return "redirect:/personList";

        }
        model.addAttribute("errorMessage", errorMessage);

        return "addPerson";
    }
}
