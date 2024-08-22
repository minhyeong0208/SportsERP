$(document).ready(function() {
	loadCoaches(); // 페이지 로드 시 코치 목록을 불러옵니다.

	// 수정 버튼 클릭 이벤트 바인딩
	$(document).on('click', '.edit-coach', function() {
	    const coachId = $(this).data('id');
	    $.ajax({
	        url: `/coaches/${coachId}`,
	        method: "GET",
	        success: function(coach) {
	            $('#coachImage').val(''); // 이미지를 다시 선택하도록 초기화
	            $('#coachName').val(coach.coachName);
	            $('#coachPosition').val(coach.coachPosition);
	            $('#coachCertification').val(coach.coachCertification);
	            $('#contractStartDate').val(coach.contractStartDate);
	            $('#contractEndDate').val(coach.contractEndDate);
	            $('#coachModal').data('coachId', coach.coachId).modal('show');
	        },
	        error: function(error) {
	            console.error("코치 정보를 불러오는 데 실패했습니다:", error);
	        }
	    });
	});


	// 삭제 버튼 클릭 이벤트 바인딩
	$(document).on('click', '.delete-coach', function() {
		const coachId = $(this).data('id');
		if (confirm("정말로 이 코치를 삭제하시겠습니까?")) {
			$.ajax({
				url: `/coaches/${coachId}`,
				method: "DELETE",
				success: function() {
					alert('코치가 삭제되었습니다.');
					loadCoaches(); // 코치 목록 다시 불러오기
				},
				error: function(error) {
					console.error("코치 삭제에 실패했습니다:", error);
				}
			});
		}
	});
});

function loadCoaches() {
	$.ajax({
		url: "/coaches",
		method: "GET",
		success: function(data) {
			var coachTableBody = $("#coachTableBody");
			coachTableBody.empty(); // 테이블 내용 비우기

			data.forEach(function(coach) {
				var row = `<tr>
                            <td><img src="${coach.coachImage}" alt="${coach.coachName}" style="width:50px;height:50px;"></td>
                            <td>${coach.coachName}</td>
                            <td>${coach.coachPosition}</td>
                            <td>${coach.coachCertification}</td>
                            <td>${coach.contractStartDate}</td>
                            <td>${coach.contractEndDate}</td>
                            <td>
                                <button class="btn btn-primary btn-sm edit-coach" data-id="${coach.coachId}">수정</button>
                                <button class="btn btn-danger btn-sm delete-coach" data-id="${coach.coachId}">삭제</button>
                            </td>
                        </tr>`;
				coachTableBody.append(row);
			});
		},
		error: function(error) {
			console.error("코치 데이터를 가져오는 데 실패했습니다:", error);
		}
	});
}

function saveCoach() {
	const form = $('#coachForm')[0];
	const formData = new FormData(form);

	// 나머지 코치 데이터를 JSON으로 변환하여 FormData에 추가
	const coachData = {
		coachName: form.coachName.value,
		coachPosition: form.coachPosition.value,
		coachCertification: form.coachCertification.value,
		contractStartDate: form.contractStartDate.value,
		contractEndDate: form.contractEndDate.value
	};
	formData.append('coachData', new Blob([JSON.stringify(coachData)], { type: 'application/json' }));

	const coachId = $('#coachModal').data('coachId');
	const method = coachId ? "PUT" : "POST";
	const url = coachId ? `/coaches/${coachId}` : "/coaches";

	$.ajax({
		url: url,
		method: method,
		enctype: 'multipart/form-data',
		data: formData,
		processData: false,
		contentType: false,
		success: function() {
			alert('코치 정보가 저장되었습니다.');
			$('#coachForm').trigger("reset");
			const modal = bootstrap.Modal.getInstance(document.getElementById('coachModal'));
			modal.hide();
			loadCoaches();
		},
		error: function(error) {
			console.error("코치 정보를 저장하는 데 실패했습니다:", error);
		}
	});
}