package com.softuni.battleship.web;

import com.softuni.battleship.model.dto.ShipDTO;
import com.softuni.battleship.model.session.LoggedUser;
import com.softuni.battleship.service.ShipService;
import com.softuni.battleship.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final UserService userService;

    public HomeController(ShipService shipService, UserService userService) {
        this.shipService = shipService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String loggedOutIndex() {
        return "index";
    }

    @GetMapping("/users/home")
    public String loggedInIndex(Model model) {

        if (!this.userService.isLoggedIn()){
            return "redirect:/";
        }

        List<ShipDTO> allShips = this.shipService.allShips();
        model.addAttribute("allShips", allShips);

        Long loggedUserId = this.userService.getLoggedUserId();

        List<ShipDTO> allShipsWithoutLoggedUser = this.shipService.allShipsNotByUserId(loggedUserId);
        model.addAttribute("allShipsWithoutLoggedUser", allShipsWithoutLoggedUser);

        List<ShipDTO> allShipsLoggedUser = this.shipService.allShipsByUserId(loggedUserId);
        model.addAttribute("allShipsLoggedUser", allShipsLoggedUser);

        return "home";
    }

}
