package acorn.admin.dto;

import java.sql.Date;
import acorn.admin.entity.Player;
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
public class PlayerDto {

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

    public static PlayerDto toDto(Player player) {
        return PlayerDto.builder()
                .playerId(player.getPlayerId())
                .playerBn(player.getPlayerBn())
                .playerImage(player.getPlayerImage())
                .playerName(player.getPlayerName())
                .playerBirth(player.getPlayerBirth())
                .playerPosition(player.getPlayerPosition())
                .playerNationality(player.getPlayerNationality())
                .playerPotential(player.getPlayerPotential())
                .contractStartDate(player.getContractStartDate())
                .contractEndDate(player.getContractEndDate())
                .career(player.getCareer())
                .build();
    }
}
