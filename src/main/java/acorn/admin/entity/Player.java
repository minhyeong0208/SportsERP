package acorn.admin.entity;

import java.sql.Date;
import acorn.admin.dto.PlayerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerId;

    private int playerBn;
    private int playerPotential;
    private String playerImage;
    private String playerName;
    private String playerPosition;
    private String playerNationality;
    private String career;
    private Date playerBirth;
    private Date contractStartDate;
    private Date contractEndDate;

    public static Player toEntity(PlayerDto playerDto) {
        return Player.builder()
                .playerId(playerDto.getPlayerId())
                .playerBn(playerDto.getPlayerBn())
                .playerName(playerDto.getPlayerName())
                .playerImage(playerDto.getPlayerImage())
                .playerPosition(playerDto.getPlayerPosition())
                .playerNationality(playerDto.getPlayerNationality())
                .playerPotential(playerDto.getPlayerPotential())
                .career(playerDto.getCareer())
                .playerBirth(playerDto.getPlayerBirth())
                .contractStartDate(playerDto.getContractStartDate())
                .contractEndDate(playerDto.getContractEndDate())
                .build();
    }

    public void updateFromDto(PlayerDto playerDto) {
        this.playerBn = playerDto.getPlayerBn();
        this.playerName = playerDto.getPlayerName();
        this.playerPosition = playerDto.getPlayerPosition();
        this.playerNationality = playerDto.getPlayerNationality();
        this.playerPotential = playerDto.getPlayerPotential();
        this.career = playerDto.getCareer();
        this.playerBirth = playerDto.getPlayerBirth();
        this.contractStartDate = playerDto.getContractStartDate();
        this.contractEndDate = playerDto.getContractEndDate();
    }
}
