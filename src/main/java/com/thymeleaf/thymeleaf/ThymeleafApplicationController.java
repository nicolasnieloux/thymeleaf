package com.thymeleaf.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafApplicationController {
    @GetMapping(path = ("/"))
    public String getCharacter(Model model) {
        List<Character> persons = new ArrayList<>();
        Character Perso1 = new Character(1, "Charly", Type.Magicien, 10);
        Character Perso2 = new Character(2, "Cedric", Type.Guerrier, 25);
        Character Perso3 = new Character(3, "Jerome", Type.Guerrier, 1);
        persons.add(Perso1);
        persons.add((Perso2));
        persons.add((Perso3));
        model.addAttribute("persons", persons);
        return "list";
    }
}
