package com.thymeleaf.thymeleaf;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ThymeleafApplicationController {


    @GetMapping(path = ("/"))

    public  String getCharacter(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String urlList = "http://localhost:8081/personnage";
        Character[] response = restTemplate.getForObject(urlList, Character[].class);
        model.addAttribute("persons",response );
        return "list";
    }

//    @RequestMapping(value = {"/addCharacter"}, method = RequestMethod.GET)
//    public String showAddPersonPage(Model model) {
//
//        CharacterForm characterForm = new CharacterForm();
//        Type[] type = new Type[]{Type.Guerrier, Type.Magicien};
//
//        model.addAttribute("characterForm", characterForm);
//        model.addAttribute("type", type);
//
//        return "addCharacter";
//    }
//
//    @RequestMapping(value = {"/addCharacter"}, method = RequestMethod.POST)
//    public String savePerson(Model model, //
//                             @ModelAttribute("characterForm") CharacterForm characterForm) {
//
//        int id = characterForm.getId();
//        String name = characterForm.getName();
//        Type type = characterForm.getType();
//        int lifePoint = characterForm.getLifePoint();
//
//
//        Character newCharacter = new Character(id, name, type, lifePoint);
//        persons.add(newCharacter);
//
//        return "redirect:/";
//    }

//    @RequestMapping(value = "/updateCharacter/{id}", method = RequestMethod.GET)
//    public String showUpdatePersonPage(@PathVariable("id") int id, Model model) {
//        Character characterToUpdate = persons.stream()
//                .filter(character -> character.getId() == id)
//                .findFirst()
//                .orElse(null);
//
//        if (characterToUpdate == null) {
//            return "redirect:/list";
//        }
//
//        Type[] type = new Type[]{Type.Guerrier, Type.Magicien};
//
//        model.addAttribute("characterForm", characterToUpdate);
//        model.addAttribute("type", type);
//
//        return "updateCharacter";
//    }

//    @RequestMapping(value = "/updateCharacter", method = RequestMethod.POST)
//    public String updatePerson(Model model, @ModelAttribute("characterForm") CharacterForm characterForm) {
//        int id = characterForm.getId();
//        String name = characterForm.getName();
//        Type type = characterForm.getType();
//        int lifePoint = characterForm.getLifePoint();
//
//        Optional<Character> characterToUpdate = persons.stream()
//                .filter(character -> character.getId() == id)
//                .findFirst();
//
//        if (characterToUpdate.isPresent()) {
//            Character character = characterToUpdate.get();
//            character.setName(name);
//            character.setLifePoint(lifePoint);
//            character.setType(type);
//        }
//
//        return "redirect:/list";
//    }
//
    @RequestMapping(value = "/detailedCharacter/{id}", method = RequestMethod.GET)
    public String showDetailledPersonPage(@PathVariable("id") int id, Model model) {

        RestTemplate restTemplate = new RestTemplate();
        String urlList = "http://localhost:8081/personnage/"+id;
        Character response = restTemplate.getForObject(urlList, Character.class);
         model.addAttribute("characterDetailed", response);

        return "detailedCharacter";
    }

    @RequestMapping(value = "/removeCharacter/{id}", method = RequestMethod.GET)
    public String deletePersonPage(@PathVariable("id") int id) {

        RestTemplate restTemplate = new RestTemplate();
        String urlList = "http://localhost:8081/personnage/"+id;
        restTemplate.delete(urlList);

        return "redirect:/";
    }

}