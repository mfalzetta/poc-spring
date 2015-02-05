package br.com.matta.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by Marlon on 05/02/2015.
 */
@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

    private final AppointmentBook appointmentBook;

    @Autowired
    public AppointmentsController(AppointmentBook appointmentBook) {
        this.appointmentBook = appointmentBook;
    }

    // spring-mvc-poc/appointments, sendo o request um GET.
    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Appointment> get() {
        return appointmentBook.getAppointmentsForToday();
    }

    // spring-mvc-poc/appointments/ALGUMA_DATA
    @RequestMapping(value = "/{day}", method = RequestMethod.GET)
    public Map<String, Appointment> getForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date day, Model model) {
        return appointmentBook.getAppointmentsForDay(day);
    }

    // // spring-mvc-poc/appointments/new
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public AppointmentForm getNewForm() {
        return new AppointmentForm();
    }

    // spring-mvc-poc/appointments, sendo o request um POST.
    // Validacao -> BindingResult
    @RequestMapping(method = RequestMethod.POST)
    public String add(@Validated AppointmentForm appointment, BindingResult result) {
        if (result.hasErrors()) {
            return "appointments/new";
        }
        appointmentBook.addAppointment(appointment);
        return "redirect:/appointments";
    }

    // URI Template Patterns
    @RequestMapping(value = "/owners/{ownerId}", method = RequestMethod.GET)
    public String findOwner(@PathVariable String ownerId, Model model) {
        Owner owner = ownerService.findOwner(ownerId);
        model.addAttribute("owner", owner);
        return "displayOwner";
    }



}