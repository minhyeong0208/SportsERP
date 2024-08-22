// SidebarComponent 정의
const SidebarComponent = {
	template: `
    <!-- Sidebar -->
    <div id="sidebar">
        <!-- 프로필 섹션 -->
        <div id="profile-section">
            <img src="https://via.placeholder.com/80" alt="Profile Picture">
            <h5>관리자명</h5>
            <div class="profile-actions">
                <a href="#" class="nav-link">프로필 수정</a>
                <a href="#" class="nav-link text-danger">로그아웃</a>
            </div>
        </div>

        <div class="accordion" id="accordionSidebar">
            <!-- 대시보드 -->
            <div class="nav-item">
                <a class="nav-link" href="./dashboard.html">
                    <i class="bi bi-house-door"></i> 대시보드
                </a>
            </div>

            <!-- 인사관리 -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingHR">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseHR" aria-expanded="true" aria-controls="collapseHR">
                        <i class="bi bi-people"></i>&nbsp;인적자원
                    </button>
                </h2>
                <div id="collapseHR" class="accordion-collapse collapse show" aria-labelledby="headingHR">
                    <div class="accordion-body">
                        <a class="nav-link" href="./player.html">선수 관리</a>
                        <a class="nav-link" href="#">코치 관리</a>
                    </div>
                </div>
            </div>

            <!-- 재정관리 -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingFinance">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFinance" aria-expanded="true" aria-controls="collapseFinance">
                        <i class="bi bi-graph-up"></i>&nbsp;재정관리
                    </button>
                </h2>
                <div id="collapseFinance" class="accordion-collapse collapse show" aria-labelledby="headingFinance">
                    <div class="accordion-body">
                        <a class="nav-link" href="./income.html">수입</a>
                        <a class="nav-link" href="./expense.html">지출</a>
                        <a class="nav-link" href="./statement.html">손익계산</a>
                    </div>
                </div>
            </div>

            <!-- 물적자원관리 -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingResources">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseResources" aria-expanded="true" aria-controls="collapseResources">
                        <i class="bi bi-gear"></i>&nbsp;물적자원
                    </button>
                </h2>
                <div id="collapseResources" class="accordion-collapse collapse show" aria-labelledby="headingResources">
                    <div class="accordion-body">
                        <a class="nav-link" href="#">시설 관리</a>
                        <a class="nav-link" href="#">재고 관리</a>
                    </div>
                </div>
            </div>

            <!-- 구단 자원 관리 -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingClubResources">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseClubResources" aria-expanded="true" aria-controls="collapseClubResources">
                        <i class="bi bi-box-seam"></i>&nbsp;이커머스
                    </button>
                </h2>
                <div id="collapseClubResources" class="accordion-collapse collapse show" aria-labelledby="headingClubResources">
                    <div class="accordion-body">
                        <a class="nav-link" href="#">상품 관리</a>
                        <a class="nav-link" href="#">주문 관리</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `
};