$(document).ready(function() {
    loadPlayers();

    $(document).on('click', '.edit-player', function() {
        const playerId = $(this).data('id');
        $.ajax({
            url: `/players/${playerId}`,
            method: "GET",
            success: function(player) {
                $('#playerImage').val('');
                $('#playerBn').val(player.playerBn);
                $('#playerName').val(player.playerName);
                $('#playerPosition').val(player.playerPosition);
                $('#playerNationality').val(player.playerNationality);
                $('#playerPotential').val(player.playerPotential);
                $('#playerBirth').val(player.playerBirth);
                $('#contractStartDate').val(player.contractStartDate);
                $('#contractEndDate').val(player.contractEndDate);
                $('#career').val(player.career);
                $('#playerModal').data('playerId', player.playerId).modal('show');
            },
            error: function(error) {
                console.error("선수 정보를 불러오는 데 실패했습니다:", error);
            }
        });
    });

    $(document).on('click', '.delete-player', function() {
        const playerId = $(this).data('id');
        if (confirm("정말로 이 선수를 삭제하시겠습니까?")) {
            $.ajax({
                url: `/players/${playerId}`,
                method: "DELETE",
                success: function() {
                    alert('선수가 삭제되었습니다.');
                    location.reload(); // 성공 후 페이지 새로고침
                },
                error: function(error) {
                    console.error("선수 삭제에 실패했습니다:", error);
                }
            });
        }
    });
});

function loadPlayers() {
    $.ajax({
        url: "/players",
        method: "GET",
        success: function(data) {
            var playerTableBody = $("#playerTableBody");
            playerTableBody.empty();

            data.forEach(function(player) {
                var row = `<tr>
                    <td><img src="${player.playerImage}" alt="${player.playerName}" style="width:50px;height:50px;"></td>
                    <td>${player.playerBn}</td>
                    <td>${player.playerName}</td>
                    <td>${player.playerPosition}</td>
                    <td>${player.playerNationality}</td>
                    <td>${player.playerPotential}</td>
                    <td>${player.playerBirth}</td>
                    <td>${player.contractStartDate}</td>
                    <td>${player.contractEndDate}</td>
                    <td>${player.career}</td>
                    <td>
                        <button class="btn btn-primary btn-sm edit-player" data-id="${player.playerId}">수정</button>
                        <button class="btn btn-danger btn-sm delete-player" data-id="${player.playerId}">삭제</button>
                    </td>
                </tr>`;
                playerTableBody.append(row);
            });
        },
        error: function(error) {
            console.error("선수 데이터를 가져오는 데 실패했습니다:", error);
        }
    });
}

function savePlayer() {
    const form = $('#playerForm')[0];
    const formData = new FormData(form); 

    const playerData = {
        playerBn: form.playerBn.value,
        playerName: form.playerName.value,
        playerPosition: form.playerPosition.value,
        playerNationality: form.playerNationality.value,
        playerPotential: form.playerPotential.value,
        playerBirth: form.playerBirth.value,
        contractStartDate: form.contractStartDate.value,
        contractEndDate: form.contractEndDate.value,
        career: form.career.value
    };
    formData.append('playerData', new Blob([JSON.stringify(playerData)], { type: 'application/json' }));

    const playerId = $('#playerModal').data('playerId');
    const method = playerId ? "PUT" : "POST";
    const url = playerId ? `/players/${playerId}` : "/players";

    $.ajax({
        url: url,
        method: method,
        enctype: 'multipart/form-data',
        data: formData,
        processData: false,
        contentType: false,
        success: function() {
            alert('선수 정보가 저장되었습니다.');
            $('#playerForm').trigger("reset");
            const modal = bootstrap.Modal.getInstance(document.getElementById('playerModal'));
            modal.hide();
            location.reload(); // 성공 후 페이지 새로고침
        },
        error: function(error) {
            console.error("선수 정보를 저장하는 데 실패했습니다:", error);
        }
    });
}
