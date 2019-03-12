package hibernate.controller;


import hibernate.model.Person;
import hibernate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PersonController {

    @Autowired  //tworzy klase implementujac interface o tej samej nazwie/ zeby odnosic sie do bazy danych
    private PersonRepository personRepository;


    @GetMapping("/")
    public String show(ModelMap modelMap) {
        modelMap.put("person", new Person());
        // przekazujemy pusty string person form, by sie odniesc do klasy person
        // taka skladnia thymeleafa  tylko po to by stworzyc form, tylko i wlyacznie

        return "form";
    }

    @PostMapping("/")
    public String create(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
/*
binding results sprwadza czy mamy bledy czy nie
dlatego musi byc Valid, bo bez Valida bedzie zapisywac nam bledy,
a tak, tylko jesli wpiszemy finalnie dobre dane


Linijka Valid robi cos takiego:
Person person = new Person();
person.setAge(age);
person.setName(name);
czyli to samo co @Param ale krocej
bysmy musieli tworzyc Controllera

BidingResult jest to funkcja wbudowana w Spring, daje nam informacje, czy sa bledy czy nie,
jesli sa bledy to daje nam odnosik do html form i daje nam informacje, np obok "size must be between 2 and 30"


teraz bardziej spring/thyleaf way.
finalnie, wszystko jest

 */


        } else {
            personRepository.save(person);          //to daje nam informacje, ze zapisuje do bazy danych
            return "redirect:/all";
        }
    }

    @GetMapping("/all")
    public String findAll(ModelMap modelMap) {              //allSortById pod listy, normlanie byloby FindById
        modelMap.put("people", personRepository.findAllSortById());   //podaje nam wszystkie, wyniki w htmlu all
        return "all";
    }

    @GetMapping("people/{id}/delete")
    //jak damy {} to mozemy tam wpisac cokolwiek, wybralismy id, zeby nasz integer id, dawal odnosnik do usuwania ktorego numer id

    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) { //NIE MOZE BYC MODEL MAP BO,nie dziala  z redirect/ nie i chuj, tak ktos wykminil by mnie wkurwic
        //PatrgVariable daje nam mozliwosc, ze w url wychwyci liczbe w tym wypadku people/1/delete   i da mozliwosc usuniecia
        Person person = personRepository.findById(id).get(); //tworzymy odnosnik do klasy person, by moc pozniej wyswietlac go w komunikacie, za posrednikiem repository, znajdz po id
        personRepository.deleteById(id);
        //deletedById usuwa z bazy danych taka funkcja w Springu
        //redirect daje nam mozliwosc usuwania i odswiezania automatycznego, jest to opcja w Springu, wbudowana
        //redirect przechodzi do URL all i czysci parametry, czyli wpisze url i cofa do /all,
        //1 slash bo przy 2 odniesie sie do konkretnej strony, a tak tylko do koncowki /all

        redirectAttributes.addFlashAttribute("message", "usunieto zawodnika " + person.getName() + " z bazy danych");
        return "redirect:/all";                 //message, do tego sie odniesiemy  w html przy zmianie koloru,
    }


    @GetMapping("people/{id}/update")
    public String update(@PathVariable Integer id, ModelMap modelMap) {
        Person person = personRepository.findById(id).get();
        modelMap.put("person", person);
        return "form";
    }
    @GetMapping
    ("/eighteens")
    public String getEighteens(ModelMap modelMap){
        modelMap.put("people",personRepository.findByAgeEighteen(18));
        return "all";
    }
//postmaping zeby nir wyswietlalo kazdej akcji na strnir
@PostMapping("/people/search")
    public String search(@RequestParam String option,
                         ModelMap modelMap){
        try{
            Integer age = Integer.parseInt(option);
            modelMap.put("people",personRepository.FindByNameOrAge(option, age));

        }
            catch (NumberFormatException e){
            modelMap.put("people", personRepository.FindByNameOrAge(option, 0));
            }
    return "all";
    }


}
