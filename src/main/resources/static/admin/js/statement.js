const app = Vue.createApp({
	data() {
		return {
			activeTab: 'daily',  // 초기 탭 설정
			selectedDate: '',
			selectedMonth: '',
			incomeData: [],
			expenseData: [],
			totalIncome: 0,
			totalExpense: 0,
			maxRows: [],
			totalIncomeMonthly: 0,
			totalExpenseMonthly: 0,
			maxRowsMonthly: []
		};
	},
	methods: {
		fetchDailyData() {
			if (!this.selectedDate) {
				alert('날짜를 선택해주세요.');
				return;
			}

			// 수입 데이터 요청
			fetch(`http://localhost/incomes/${this.selectedDate}`)
				.then(response => response.json())
				.then(data => {
					this.incomeData = data;
					this.calculateTotalIncome();
					this.mergeData();
				})
				.catch(error => {
					console.error('수입 데이터를 불러오는 중 오류 발생:', error);
				});

			// 지출 데이터 요청
			fetch(`http://localhost/expenses/${this.selectedDate}`)
				.then(response => response.json())
				.then(data => {
					this.expenseData = data;
					this.calculateTotalExpense();
					this.mergeData();
				})
				.catch(error => {
					console.error('지출 데이터를 불러오는 중 오류 발생:', error);
				});
		},
		fetchMonthlyData() {
			if (!this.selectedMonth) {
				alert('월을 선택해주세요.');
				return;
			}

			const [year, month] = this.selectedMonth.split('-');

			// 수입 데이터 요청
			fetch(`http://localhost/incomes/month/${year}/${month}`)
				.then(response => response.json())
				.then(data => {
					this.incomeData = data;
					this.calculateTotalIncomeMonthly();
					this.mergeDataMonthly();
				})
				.catch(error => {
					console.error('수입 데이터를 불러오는 중 오류 발생:', error);
				});

			// 지출 데이터 요청
			fetch(`http://localhost/expenses/month/${year}/${month}`)
				.then(response => response.json())
				.then(data => {
					this.expenseData = data;
					this.calculateTotalExpenseMonthly();
					this.mergeDataMonthly();
				})
				.catch(error => {
					console.error('지출 데이터를 불러오는 중 오류 발생:', error);
				});
		},
		calculateTotalIncome() {
			// 일별 수입 총합 계산 (null 또는 undefined 처리)
			this.totalIncome = this.incomeData.reduce((total, income) => {
				return total + (income.income_amount || 0);
			}, 0);
		},
		calculateTotalExpense() {
			// 일별 지출 총합 계산 (null 또는 undefined 처리)
			this.totalExpense = this.expenseData.reduce((total, expense) => {
				return total + (Number(expense.expense_amount) || 0);
			}, 0);
		},
		calculateTotalIncomeMonthly() {
			// 월별 수입 총합 계산
			this.totalIncomeMonthly = this.incomeData.reduce((total, income) => {
				return total + (Number(income.total_amount) || 0);
			}, 0);
		},
		calculateTotalExpenseMonthly() {
			// 월별 지출 총합 계산
			this.totalExpenseMonthly = this.expenseData.reduce((total, expense) => {
				return total + (Number(expense.total_amount) || 0);
			}, 0);
		},
		mergeData() {
			const maxLength = Math.max(this.incomeData.length, this.expenseData.length);
			this.maxRows = [];

			for (let i = 0; i < maxLength; i++) {
				this.maxRows.push({
					income: this.incomeData[i] || null,
					expense: this.expenseData[i] || null
				});
			}
		},
		mergeDataMonthly() {
			const maxLength = Math.max(this.incomeData.length, this.expenseData.length);
			this.maxRowsMonthly = [];

			for (let i = 0; i < maxLength; i++) {
				this.maxRowsMonthly.push({
					income: this.incomeData[i] || null,
					expense: this.expenseData[i] || null
				});
			}
		}
	}
});

app.component('admin-header', HeaderComponent);
app.component('admin-sidebar', SidebarComponent);

app.mount('#income-expense');