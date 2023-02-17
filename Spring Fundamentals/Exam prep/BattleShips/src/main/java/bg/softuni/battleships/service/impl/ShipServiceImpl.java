package bg.softuni.battleships.service.impl;

import bg.softuni.battleships.model.dto.ShipDTO;
import bg.softuni.battleships.model.entity.Ship;
import bg.softuni.battleships.model.service.ShipServiceModel;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.security.CurrentUser;
import bg.softuni.battleships.service.CategoryService;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;

    public ShipServiceImpl(ShipRepository shipRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {
        Ship ship = modelMapper.map(shipServiceModel, Ship.class);
        ship.setUser(userService.findById(currentUser.getId()));
        ship.setCategory(categoryService.findByCategoryNameEnum(shipServiceModel.getCategory()));

        shipRepository.save(ship);
    }

    @Override
    public List<ShipDTO> getShipsOwnedBy(long ownerId) {
        return this.shipRepository.findByUserId(ownerId).stream()
                .map(ship -> modelMapper.map(ship, ShipDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShipDTO> getShipsNotOwnedBy(long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId).stream()
                .map(ship -> modelMapper.map(ship, ShipDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShipDTO> getAllSortedShips() {
        return this.shipRepository.findByOrderByHealthAscNameDescPowerAsc().stream()
                .map(ship -> modelMapper.map(ship, ShipDTO.class)).collect(Collectors.toList());
    }

}
