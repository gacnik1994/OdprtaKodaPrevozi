package com.Prevozi.Taxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class PrevoziController {
    @Autowired private PrevoziService service;
    @Autowired private VoznikService service2;

    @GetMapping("")
    public String prikaziPrevoze(Model model){
        List<Prevozi> listPrevozi = service.listAll();
        List<Voznik> listVozniki = service2.listAll();
        model.addAttribute("listPrevozi",listPrevozi);
        model.addAttribute("listVoznik",listVozniki);
        return "index";
    }
    @GetMapping("/dodaj")
    public String dodajPrevoz(Model model){
        model.addAttribute("prevozi", new Prevozi());
        return "dodajP";
    }

    @PostMapping("/dodaj/new")
    public String savePrevozi(Prevozi pr, RedirectAttributes ra){
        LocalTime lt = LocalTime.parse(pr.getCasString());
        pr.setCas(lt);
        LocalDate ld = LocalDate.parse(pr.getDatumString());
        pr.setDatum(ld);
        List<Voznik> v = service2.listAll();
        List<Prevozi> p = service.listAll();
        for(int i = 0; i<p.size();i++){
            if(p.get(i).getDatumString().equals(pr.getDatumString()) && p.get(i).getCasString().equals(pr.getCasString())){
                for(int j = 0; j<v.size();j++){
                    if(v.get(j).getId() != p.get(i).getVoznik() && p.get(i).getVoznik()<v.get(j).getId()){
                        pr.setVoznik(v.get(j).getId());
                        break;
                    } else if ((j-1)==v.size()) {
                        ra.addFlashAttribute("Na voljo ni voznika!");
                        return "redirect/";
                    }
                }
            } else if ((i-1) == p.size()) {
                pr.setVoznik(v.get(0).getId());
            }
        }
        //pr.setVoznik(v.get(0).getId());
        service.save(pr);
        ra.addFlashAttribute("message", "Prevoz je bil uspešno dodan.");
        return "redirect:/";
    }

    @GetMapping("dodaj/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, RedirectAttributes ra){
        try {
            Prevozi pr = service.get(id);
            model.addAttribute("prevozi", pr);
            return "dodajP";
        }catch (VoznikNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("izbrisi/{id}")
    public String izbrisiVoznika(@PathVariable("id") int id, RedirectAttributes ra){
        //try {
        service.delete(id);
        ra.addFlashAttribute("message","Prevoz z Id: "+ id + " izbrisan!");
        //}catch (VoznikNotFoundException e){
        //  ra.addFlashAttribute("message", e.getMessage());
        //}
        return "redirect:/";
    }
}
