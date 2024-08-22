const app = Vue.createApp({
    data() {
        return {
            players: [],
            playerForm: {
                playerBn: '',
                playerName: '',
                playerPosition: '',
                playerNationality: '',
                playerPotential: '',
                playerBirth: '',
                contractStartDate: '',
                contractEndDate: '',
                career: ''
            },
            editingPlayerId: null
        };
    },
    methods: {
        loadPlayers() {
            fetch('/players')
                .then(response => response.json())
                .then(data => {
                    this.players = data;
                })
                .catch(error => {
                    console.error("선수 데이터를 가져오는 데 실패했습니다:", error);
                });
        },
        openAddPlayerModal() {
            this.editingPlayerId = null;  // 새 선수 추가 모드로 전환
            this.playerForm = {
                playerBn: '',
                playerName: '',
                playerPosition: '',
                playerNationality: '',
                playerPotential: '',
                playerBirth: '',
                contractStartDate: '',
                contractEndDate: '',
                career: ''
            };
            const modal = new bootstrap.Modal(document.getElementById('playerModal'));
            modal.show();
        },
        editPlayer(player) {
            this.editingPlayerId = player.playerId;
            this.playerForm = { ...player };
            const modal = new bootstrap.Modal(document.getElementById('playerModal'));
            modal.show();
        },
        deletePlayer(playerId) {
            if (confirm("정말로 이 선수를 삭제하시겠습니까?")) {
                fetch(`/players/${playerId}`, {
                    method: 'DELETE'
                })
                    .then(() => {
                        alert('선수가 삭제되었습니다.');
                        this.loadPlayers(); // 성공 후 목록 새로고침
                    })
                    .catch(error => {
                        console.error("선수 삭제에 실패했습니다:", error);
                    });
            }
        },
        savePlayer() {
            const formData = new FormData();
            const playerImage = this.$refs.playerImage.files[0];
            
            if (playerImage) {
                formData.append('playerImage', playerImage);
            }

            const playerData = { ...this.playerForm };
            formData.append('playerData', new Blob([JSON.stringify(playerData)], { type: 'application/json' }));

            const method = this.editingPlayerId ? "PUT" : "POST";
            const url = this.editingPlayerId ? `/players/${this.editingPlayerId}` : "/players";

            fetch(url, {
                method: method,
                body: formData,
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errorInfo => Promise.reject(errorInfo));
                }
                return response.json();
            })
            .then(() => {
                alert('선수 정보가 저장되었습니다.');
                this.editingPlayerId = null;
                this.playerForm = {
                    playerBn: '',
                    playerName: '',
                    playerPosition: '',
                    playerNationality: '',
                    playerPotential: '',
                    playerBirth: '',
                    contractStartDate: '',
                    contractEndDate: '',
                    career: ''
                };
                const modal = bootstrap.Modal.getInstance(document.getElementById('playerModal'));
                modal.hide();
                location.reload();  // 페이지 새로고침
            })
            .catch(error => {
                console.error("선수 정보를 저장하는 데 실패했습니다:", error);
            });
        }
    },
    mounted() {
        this.loadPlayers();
    }
});


app.component('admin-header', HeaderComponent);
app.component('admin-sidebar', SidebarComponent);

app.mount('#app');
