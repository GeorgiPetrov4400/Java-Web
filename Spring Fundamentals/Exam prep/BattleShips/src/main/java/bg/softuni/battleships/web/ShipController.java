package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.ShipAddDTO;
import bg.softuni.battleships.model.service.ShipServiceModel;
import bg.softuni.battleships.service.ShipService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public ShipController(ShipService shipService, ModelMapper modelMapper) {
        this.shipService = shipService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public ShipAddDTO shipAddDTO() {
        return new ShipAddDTO();
    }

    @GetMapping("/add")
    public String add() {
        return "ship-add";
    }

    @PostMapping("/add")
    public String addShip(@Valid ShipAddDTO shipAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipAddDTO", shipAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.shipAddDTO",
                            bindingResult);

            return "redirect:add";
        }

        shipService.addShip(modelMapper.map(shipAddDTO, ShipServiceModel.class));

        return "redirect:/home";
    }
}
