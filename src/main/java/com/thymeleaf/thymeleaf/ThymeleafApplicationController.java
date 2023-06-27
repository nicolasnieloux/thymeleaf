package com.thymeleaf.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafApplicationController {
    static List<Character> persons = new ArrayList<>();

    static {
        Character Perso1 = new Character(1, "Charly", Type.Magicien, 10);
        Character Perso2 = new Character(2, "Cedric", Type.Guerrier, 25);
        Character Perso3 = new Character(3, "Jerome", Type.Guerrier, 1);
        persons.add(Perso1);
        persons.add((Perso2));
        persons.add((Perso3));

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

        int id= characterForm.getId();
        String name = characterForm.getName();
        Type type = characterForm.getType();
        int lifePoint = characterForm.getLifePoint();


        Character newCharacter = new Character(id,name,type,lifePoint);
        persons.add(newCharacter);

        return "redirect:/list";
    }
}