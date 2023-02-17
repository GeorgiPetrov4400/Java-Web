package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.ShipDTO;
import bg.softuni.battleships.model.dto.StartBattleDTO;
import bg.softuni.battleships.security.CurrentUser;
import bg.softuni.battleships.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @ModelAttribute
    public StartBattleDTO startBattleDTO() {
        return new StartBattleDTO();
    }

    @GetMapping("/home")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "redirect:/";
        }

        long currentUserId = currentUser.getId();

        List<ShipDTO> ownShips = this.shipService.getShipsOwnedBy(currentUserId);
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(currentUserId);
        List<ShipDTO> sortedShips = this.shipService.getAllSortedShips();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }
}
