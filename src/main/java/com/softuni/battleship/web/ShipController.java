package com.softuni.battleship.web;

import com.softuni.battleship.model.dto.CreateShipDTO;
import com.softuni.battleship.model.dto.ShipBattleDTO;
import com.softuni.battleship.service.ShipService;
import com.softuni.battleship.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    private final UserService userService;


    public ShipController(ShipService shipService, UserService userService) {
        this.shipService = shipService;
        this.userService = userService;
    }

    @ModelAttribute("createShipDTO")
    public CreateShipDTO initShipModel() {
        return new CreateShipDTO();
    }

    @GetMapping("/add")
    public String addShip() {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        return "ship-add";
    }

    @PostMapping("/add")
    public String addShip(@Valid CreateShipDTO createShipDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.shipService.createShip(createShipDTO)) {
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO", bindingResult);
            return "redirect:/ships/add";
        }

        return "redirect:/users/home";
    }

    @PostMapping("/battle")
    public String loggedInIndex(@Valid ShipBattleDTO shipBattleDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/users/home";
        }

        this.shipService.battle(shipBattleDTO);

        return "redirect:/users/home";
    }
}
