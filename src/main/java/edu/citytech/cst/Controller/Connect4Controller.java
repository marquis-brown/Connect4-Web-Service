package edu.citytech.cst.Controller;

import edu.citytech.cst.Service.Connect4Service;
import edu.citytech.cst.Service.Connect4Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Connect4Controller {

    @GetMapping("/game/connect4")
    public static Map<String, Object> getWinner(@RequestParam String moves){
        Map<String, Object> map = new HashMap<>();

        Connect4Service service = new Connect4Service();
        Connect4Status status = service.getStatus(moves);

        map.put("position", status.getPosition());
        map.put("whoWon", status.getWhoWon());
        map.put("Winner", status.getIsWinner());

        return map;
    }
}
