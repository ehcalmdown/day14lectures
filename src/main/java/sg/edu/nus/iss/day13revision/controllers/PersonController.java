package sg.edu.nus.iss.day13revision.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
}