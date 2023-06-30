package com.thymeleaf.thymeleaf;

import org.springframework.http.*;
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

    @RequestMapping(value = {"/addCharacter"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        CharacterForm characterForm = new CharacterForm();
        Type[] type = new Type[]{Type.Guerrier, Type.Magicien};

        model.addAttribute("characterForm", characterForm);
        model.addAttribute("type", type);

        return "addCharacter";
    }

    @RequestMapping(value = {"/addCharacter"}, method = RequestMethod.POST)
    public String savePerson(
                             @ModelAttribute("characterForm") CharacterForm characterForm) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String name = characterForm.getName();
        Type type = characterForm.getType();


        RestTemplate restTemplate = new RestTemplate();
        String urlList = "http://localhost:8081/personnage";

        Character characterNew = new Character(name,type);
        HttpEntity<Character> request = new HttpEntity<>(characterNew,headers);

        restTemplate.postForEntity(urlList, request, Character.class);

        return "redirect:/";
    }

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
//
//    @RequestMapping(value = "/updateCharacter", method = RequestMethod.POST)
//    public String updatePerson(Model model, @ModelAttribute("characterForm") CharacterForm characterForm) {
//
//        String name = characterForm.getName();
//        Type type = characterForm.getType();
//        int lifePoint = characterForm.getLifePoint();
//
//
//
//        return "redirect:/list";
//    }
@GetMapping("/updateCharacter/{id}")
public String showUpdateCharacterPage(@PathVariable("id") int id, Model model) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8081/personnage/" +id;

    Character character = restTemplate.getForObject(url, Character.class, id);

    if (character != null) {
        CharacterForm characterForm = new CharacterForm();
        Type[] type = new Type[]{Type.Guerrier, Type.Magicien};

        characterForm.setName(character.getName());
        characterForm.setType(character.getType());
        characterForm.setLifePoint(character.getLifePoint());
        characterForm.setId(character.getId());

        model.addAttribute("characterForm", characterForm);
        model.addAttribute("character", character);
        model.addAttribute("type", type);

        return "updateCharacter";
    } else {
        // Gérer le cas où le personnage n'est pas trouvé
        return "redirect:/";
    }
}

    @PutMapping("/updateCharacter/{id}")
    public String updateCharacter(@PathVariable ("id") int id,
                                     @ModelAttribute("characterForm") CharacterForm characterForm) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/personnage/"+id;

        CharacterForm character = new CharacterForm(characterForm.getName(), characterForm.getType(), characterForm.getLifePoint());
        HttpEntity<CharacterForm> request = new HttpEntity<>(character, headers);

        restTemplate.put(url, request);

        return "redirect:/";
    }

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