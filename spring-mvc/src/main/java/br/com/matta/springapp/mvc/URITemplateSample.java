package br.com.matta.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Marlon on 05/02/2015.
 */
@Controller
@RequestMapping("/uri-template")
public class URITemplateSample {

    // URI Template Patterns: Para nome de parametro do metodo igual ao da URI.
    @RequestMapping(value = "/owners/{ownerId}", method = RequestMethod.GET)
    public String findOwner(@PathVariable String ownerId, Model model) {
        // implementation omitted
    }

    // URI Template Patterns: Para nome de parametro do metodo deferente ao da URI.
    @RequestMapping(value = "/owners/{ownerId}", method = RequestMethod.GET)
    public String findOwner(@PathVariable("ownerId") String theOwner, Model model) {
        // implementation omitted
    }

    // URI Template Patterns: Varios Templates.
    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
    public String findPet(@PathVariable String ownerId, @PathVariable String petId, Model model) {
        Owner owner = ownerService.findOwner(ownerId);
        Pet pet = owner.getPet(petId);
        model.addAttribute("pet", pet);
        return "displayPet";
    }

    // URI Template Patterns:
    @Controller
    @RequestMapping("/owners/{ownerId}")
    public class RelativePathUriTemplateController {
        @RequestMapping("/pets/{petId}")
        public void findPet(@PathVariable String ownerId, @PathVariable String petId, Model model) {
            // implementation omitted
        }
    }

    /*
    @PathVariable argument can be of any simple type such as int, long, Date, etc. Spring automatically converts to the appropriate type or
throws a TypeMismatchException if it fails to do so. You can also register support for parsing additional data types. See the section called
“Method Parameters And Type Conversion” and the section called “Customizing WebDataBinder initialization”.
     */

    /*URI Template Patterns with Regular Expressions

    Sometimes you need more precision in defining URI template variables. Consider the URL "/spring‐web/spring‐web‐3.0.5.jar" . How
    do you break it down into multiple parts?*/

    @RequestMapping("/spring‐web/{symbolicName:[a‐z‐]}‐{version:\\d\\.\\d\\.\\d}{extension:\\.[a‐z]}")
    public void handle(@PathVariable String version, @PathVariable String extension) {
        // ...
    }

    // Matriz variavel. <mvc:annotation‐driven enable‐matrix‐variables="true"/>
    // GET /owners/42;q=11/pets/21;q=22
    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
    public void findPet(
            @MatrixVariable(value = "q", pathVar = "ownerId") int q1,
            @MatrixVariable(value = "q", pathVar = "petId") int q2) {
        // q1 == 11
        // q2 == 22
    }
}
