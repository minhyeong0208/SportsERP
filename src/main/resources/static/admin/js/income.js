const app = Vue.createApp({
	data() {
		return {
			incomes: [],
			incomeList: [], // income list from incomelist API
			startDate: '',
			endDate: '',
			editIncome: {},
			newIncome: {
				income_item: '',
				income_purpose: '',
				income_amount: '',
				income_date: ''
			},
			pageNumber: 0,
			pageSize: 10,
			totalPages: 0,
			isFiltered: false
		};
	},
	methods: {
		fetchData() {
			let url;

			if (this.isFiltered && this.startDate && this.endDate) {
				url = `http://localhost/incomes/${this.startDate}/${this.endDate}?page=${this.pageNumber}&size=${this.pageSize}`;
			} else {
				url = `http://localhost/incomes?page=${this.pageNumber}&size=${this.pageSize}`;
			}

			fetch(url)
				.then(response => response.json())
				.then(data => {
					this.incomes = data.content;
					this.totalPages = data.totalPages;
					this.pageNumber = data.number;
				})
				.catch(error => console.error(error));
		},
		fetchFilteredData() {
			if (this.startDate && this.endDate) {
				if (new Date(this.startDate) > new Date(this.endDate)) {
					alert("시작 날짜는 종료 날짜보다 이전이어야 합니다.");
					return;
				}
				this.pageNumber = 0;
				this.isFiltered = true;
				this.fetchData();
			} else {
				alert("시작 날짜와 종료 날짜를 입력해주세요.");
			}
		},
		changePage(page) {
			this.pageNumber = page;
			this.fetchData();
		},
		openEditModal(income) {
			this.editIncome = { ...income };
			const modal = new bootstrap.Modal(document.getElementById('editModal'));
			modal.show();
		},
		updateIncome() {
			const url = `http://localhost/incomes/${this.editIncome.income_id}`;
			fetch(url, {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(this.editIncome)
			})
				.then(response => {
					if (!response.ok) {
						throw new Error('AJAX Error');
					}
					return response.json();
				})
				.then(updatedIncome => {
					const index = this.incomes.findIndex(income => income.income_id === updatedIncome.income_id);
					if (index !== -1) {
						this.incomes[index] = updatedIncome;
					}
					const modal = bootstrap.Modal.getInstance(document.getElementById('editModal'));
					modal.hide();
				})
				.catch(error => console.log('수정 오류:', error));
		},
		deleteIncome(incomeId) {
			const url = `http://localhost/incomes/${incomeId}`;
			fetch(url, {
				method: 'DELETE'
			})
				.then(response => {
					if (!response.ok) {
						throw new Error('AJAX Error');
					}
					this.incomes = this.incomes.filter(income => income.income_id !== incomeId);
				})
				.catch(error => console.log('삭제 오류:', error));
		},
		openAddModal() {
			this.newIncome = { income_item: '', income_purpose: '', income_amount: '', income_date: '' };
			const modal = new bootstrap.Modal(document.getElementById('addModal'));
			modal.show();
		},
		addIncome() {
			if (!this.newIncome.income_item ||
				!this.newIncome.income_purpose ||
				!this.newIncome.income_amount ||
				!this.newIncome.income_date) {
				alert("값을 입력하세요.");
				return;
			}
			const url = `http://localhost/incomes`;
			fetch(url, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(this.newIncome)
			})
				.then(response => {
					if (!response.ok) {
						throw new Error('AJAX Error');
					}
					return response.json();
				})
				.then(addedIncome => {
					this.incomes.push(addedIncome);
					const modal = bootstrap.Modal.getInstance(document.getElementById('addModal'));
					modal.hide();
				})
				.catch(error => console.log('추가 오류:', error));
		},
		fetchIncomeList() {
			// Fetching income categories for select dropdown
			fetch('http://localhost/incomelist')
				.then(response => response.json())
				.then(data => {
					this.incomeList = data;
				})
				.catch(error => console.error('Income list fetch error:', error));
		}
	},
	mounted() {
		this.fetchData();
		this.fetchIncomeList(); // Fetch the income categories when the app is mounted
	}
});

app.component('admin-header', HeaderComponent);
app.component('admin-sidebar', SidebarComponent);

app.mount('#app');