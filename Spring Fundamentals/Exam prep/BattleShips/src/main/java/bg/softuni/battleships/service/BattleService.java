package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.StartBattleDTO;

public interface BattleService {

    void attack(StartBattleDTO attackData);
}
