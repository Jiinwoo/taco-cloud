package me.jung.tacocloud.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jung.tacocloud.Ingredient;
import me.jung.tacocloud.Ingredient.Type;
import me.jung.tacocloud.Order;
import me.jung.tacocloud.Taco;
import me.jung.tacocloud.User;
import me.jung.tacocloud.data.IngredientRepository;
import me.jung.tacocloud.data.TacoRepository;
import me.jung.tacocloud.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final UserRepository userRepository;
    private final TacoRepository tacoRepo;

    @ModelAttribute
    public void addIngredientToModel(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        String username= principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user",user);
        model.addAttribute("taco", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping
    public String processDesign(
            @Valid Taco design,
            Errors errors, @ModelAttribute Order order, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("design : " + design);
        Taco saved = tacoRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

}