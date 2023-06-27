package com.thymeleaf.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ThymeleafApplicationController {
    private final List<Character> persons = new ArrayList<>();

    private ThymeleafApplicationController(){
        persons.add(new Character(1, "Charly", Type.Magicien, 10));
        persons.add(new Character(2, "Cedric", Type.Guerrier, 25));
        persons.add(new Character(3, "Jerome", Type.Guerrier, 1));
    }
    @GetMapping(path = ("/list"))
    public String getCharacter(Model model) {
        model.addAttribute("persons", persons);
        return "list";
    }

    @RequestMapping(value = {"/addCharacter"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        CharacterForm characterForm = new CharacterForm();
        Type[] type = new Type[]{Type.Guerrier, Type.Magicien};

        model.addAttribute("characterForm", characterForm);
        model.addAttribute("type", type);

        return "addCharacter";
    }

    @RequestMapping(value = {"/addCharacter"}, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("characterForm") CharacterForm characterForm) {

        int id = characterForm.getId();
        String name = characterForm.getName();
        Type type = characterForm.getType();
        int lifePoint = characterForm.getLifePoint();


        Character newCharacter = new Character(id, name, type, lifePoint);
        persons.add(newCharacter);

        return "redirect:/list";
    }

    @RequestMapping(value = "/updateCharacter/{id}", method = RequestMethod.GET)
    public String showUpdatePersonPage(@PathVariable("id") int id, Model model) {
        Character characterToUpdate = persons.stream()
                .filter(character -> character.getId() == id)
                .findFirst()
                .orElse(null);

        if (characterToUpdate == null) {
            return "redirect:/list";
        }

        CharacterForm characterForm = new CharacterForm();
        Type[] type = new Type[]{Type.Guerrier, Type.Magicien};

        characterForm.setId(characterToUpdate.getId());
        characterForm.setName(characterToUpdate.getName());
        characterForm.setType(characterToUpdate.getType());
        characterForm.setLifePoint(characterToUpdate.getLifePoint());


        model.addAttribute("characterForm", characterForm);
        model.addAttribute("type", type);

        return "updateCharacter";
    }

    @RequestMapping(value = "/updateCharacter", method = RequestMethod.POST)
    public String updatePerson(Model model, @ModelAttribute("characterForm") CharacterForm characterForm) {
        int id = characterForm.getId();
        String name = characterForm.getName();
        Type type = characterForm.getType();
        int lifePoint = characterForm.getLifePoint();

        Optional<Character> characterToUpdate = persons.stream()
                .filter(character -> character.getId() == id)
                .findFirst();

        if (characterToUpdate.isPresent()){
            Character character = characterToUpdate.get();
            character.setName(name);
            character.setLifePoint(lifePoint);
            character.setType(type);
        }



        return "redirect:/list";
    }

    @RequestMapping(value = "/detailedCharacter/{id}", method = RequestMethod.GET)
    public String showDetailledPersonPage(@PathVariable("id") int id, Model model) {
        Character characterDetailed = persons.stream()
                .filter(character -> character.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("characterDetailed" , characterDetailed);

        if (characterDetailed == null) {
            return "redirect:/list";
        }
        return "detailedCharacter";
    }


}