package com.Prevozi.Taxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class VoznikController {
    @Autowired private VoznikService service;

    @GetMapping("/vozniki")
    public String showUserList(Model model){
        List<Voznik> listVoznik = service.listAll();
        model.addAttribute("listVoznik",listVoznik);
        return "vozniki";
    }

    @GetMapping("/vozniki/dodaj")
    public String showNewForm(Model model){
        model.addAttribute("voznik", new Voznik());
        model.addAttribute("pageTitle","DODAJ VOZNIKA");
        return "dodaj";
    }

    @PostMapping("/vozniki/shrani")
    public String saveVoznik(Voznik voznik, RedirectAttributes ra){
        service.save(voznik);
        ra.addFlashAttribute("message", "Uporabnik je bil uspešno dodan.");
        return "redirect:/vozniki";
    }

    @GetMapping("/vozniki/uredi/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, RedirectAttributes ra){
        try {
            Voznik voznik = service.get(id);
            model.addAttribute("voznik", voznik);
            model.addAttribute("pageTitle","UREDI VOZNIKA (ID: "+id+")");
            return "dodaj";
        }catch (VoznikNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/vozniki";
        }
    }

    @GetMapping("/vozniki/izbrisi/{id}")
    public String izbrisiVoznika(@PathVariable("id") int id, RedirectAttributes ra){
        //try {
            service.delete(id);
            ra.addFlashAttribute("message","Voznik z Id: "+ id + " izbrisan!");
        //}catch (VoznikNotFoundException e){
          //  ra.addFlashAttribute("message", e.getMessage());
        //}
        return "redirect:/vozniki";
    }
}
