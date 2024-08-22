package acorn.admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import acorn.admin.dto.PlayerDto;
import acorn.admin.entity.Player;
import acorn.admin.repository.PlayerRepository; 

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final String IMAGE_DIR = Paths.get("src/main/resources/static/admin/img/").toAbsolutePath().toString();

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // 모든 플레이어를 반환하는 메서드
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // 특정 ID의 플레이어를 반환하는 메서드
    public Player getPlayerById(int playerId) {
        return playerRepository.findById(playerId).orElseThrow(() -> 
            new RuntimeException("선수를 찾을 수 없습니다."));
    }

    // 플레이어 저장 메서드
    public Player savePlayer(PlayerDto playerDto, MultipartFile playerImage) throws IOException {
        String imagePath = saveImage(playerImage);
        Player player = Player.toEntity(playerDto);
        player.setPlayerImage(imagePath);
        return playerRepository.save(player);
    }

    // 플레이어 업데이트 메서드
    public Player updatePlayer(int playerId, PlayerDto playerDto, MultipartFile playerImage) throws IOException {
        return playerRepository.findById(playerId)
                .map(existingPlayer -> {
                    try {
                        if (playerImage != null && !playerImage.isEmpty()) {
                            String imagePath = saveImage(playerImage);
                            existingPlayer.setPlayerImage(imagePath);
                        }
                        existingPlayer.updateFromDto(playerDto);
                        return playerRepository.save(existingPlayer);
                    } catch (IOException e) {
                        throw new RuntimeException("이미지를 업데이트하는 동안 오류가 발생했습니다.", e);
                    }
                })
                .orElseThrow(() -> new RuntimeException("선수를 찾을 수 없습니다."));
    }

    // 플레이어 삭제 메서드
    public void deletePlayer(int playerId) {
        playerRepository.deleteById(playerId);
    }

    // 이미지 저장 메서드
    private String saveImage(MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(IMAGE_DIR, fileName);

            // 디렉토리 존재 여부 확인 및 생성
            File directory = new File(IMAGE_DIR);
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IOException("디렉토리를 생성할 수 없습니다: " + directory.getAbsolutePath());
            }

            try {
                image.transferTo(filePath.toFile());

                // 이미지 URL에 타임스탬프 추가
                String timeStamp = String.valueOf(System.currentTimeMillis());
                return "/admin/img/" + fileName + "?t=" + timeStamp;
            } catch (IOException e) {
                throw new IOException("이미지를 저장할 수 없습니다: " + filePath.toString(), e);
            }
        }
        return null;
    }


}
