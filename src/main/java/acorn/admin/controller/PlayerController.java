package acorn.admin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import acorn.admin.dto.PlayerDto;
import acorn.admin.entity.Player;
import acorn.admin.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    
    @GetMapping("/players")
    public String showPlayerPage() {
        return "redirect:/admin/player.html";
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers(); 
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") int playerId) {
        return playerService.getPlayerById(playerId);
    }

    @PostMapping
    public Player insertPlayer(@RequestPart("playerImage") MultipartFile playerImage, @RequestPart("playerData") PlayerDto playerDto) {
        try {
            return playerService.savePlayer(playerDto, playerImage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("플레이어를 저장하는 동안 오류가 발생했습니다.");
        }
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable("id") int playerId, @RequestPart("playerImage") MultipartFile playerImage, @RequestPart("playerData") PlayerDto playerDto) {
        try {
            return playerService.updatePlayer(playerId, playerDto, playerImage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("플레이어를 업데이트하는 동안 오류가 발생했습니다.");
        }
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") int playerId) {
        playerService.deletePlayer(playerId);
    }
}
