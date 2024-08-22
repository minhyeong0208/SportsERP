const app = Vue.createApp({
    data() {
        return {
            expenses: [], // 지출 목록
            expenseList: [], // 지출 항목 리스트
            selectedRows: [], 
            selectAll: false, 
            startDate: '',
            endDate: '',
            searchQuery: '', 
            editingField: null, 
            editingRow: null, 
            newExpense: { // 새로운 지출 추가용 데이터
                expense_item: '',
                expense_purpose: '',
                expense_amount: '',
                expense_date: ''
            },
            pageNumber: 0, 
            pageSize: 10, 
            totalPages: 0, 
            isFiltered: false, 
            maxPagesToShow: 10 
        };
    },
    computed: {
        startPage() {
            const half = Math.floor(this.maxPagesToShow / 2);
            let start = Math.max(0, this.pageNumber - half);
            if (start + this.maxPagesToShow > this.totalPages) {
                start = Math.max(0, this.totalPages - this.maxPagesToShow);
            }
            return start;
        },
        endPage() {
            return Math.min(this.totalPages, this.startPage + this.maxPagesToShow);
        },
        pagesToShow() {
            const pages = [];
            for (let i = this.startPage; i < this.endPage; i++) {
                pages.push(i);
            }
            return pages;
        }
    },
    methods: {
        fetchData() {
            let url = `http://localhost/expenses?page=${this.pageNumber}&size=${this.pageSize}`;
            if (this.isFiltered && this.startDate && this.endDate) {
                url = `http://localhost/expenses/${this.startDate}/${this.endDate}?page=${this.pageNumber}&size=${this.pageSize}`;
            } else if (this.searchQuery) {
                url = `http://localhost/expenses/search?query=${this.searchQuery}&page=${this.pageNumber}&size=${this.pageSize}`;
            }

            fetch(url)
                .then(response => response.json())
                .then(data => {
                    this.expenses = data.content;
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
        performSearch() {
            this.pageNumber = 0; 
            this.fetchData();
        },
        toggleSelectAll() {
            this.selectedRows = this.selectAll ? this.expenses.map(expense => expense.expense_id) : [];
        },
        editField(rowIndex, field) {
            this.editingField = field;
            this.editingRow = rowIndex;
        },
        saveEdit(rowIndex) {
            const editedExpense = this.expenses[rowIndex];
            const url = `http://localhost/expenses/${editedExpense.expense_id}`;
            fetch(url, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(editedExpense)
            })
                .then(response => {
                    if (!response.ok) throw new Error('AJAX Error');
                    return response.json();
                })
                .then(() => {
                    this.editingField = null;
                    this.editingRow = null;
                })
                .catch(error => console.log('수정 오류:', error));
        },
        deleteSelectedExpenses() {
            const confirmed = confirm("선택한 항목들을 삭제하시겠습니까?");
            if (confirmed) {
                this.selectedRows.forEach(id => {
                    const url = `http://localhost/expenses/${id}`;
                    fetch(url, { method: 'DELETE' })
                        .then(response => {
                            if (!response.ok) throw new Error('AJAX Error');
                            this.expenses = this.expenses.filter(expense => expense.expense_id !== id);
                        })
                        .catch(error => console.log('삭제 오류:', error));
                });
                this.selectedRows = [];
            }
        },
        openAddModal() {
            this.newExpense = { expense_item: '', expense_purpose: '', expense_amount: '', expense_date: '' };
            const modal = new bootstrap.Modal(document.getElementById('addModal'));
            modal.show();
        },
        addExpense() {
            if (!this.newExpense.expense_item || !this.newExpense.expense_purpose || !this.newExpense.expense_amount || !this.newExpense.expense_date) {
                alert("모든 값을 입력하세요.");
                return;
            }
            const url = `http://localhost/expenses`;
            fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.newExpense)
            })
                .then(response => {
                    if (!response.ok) throw new Error('AJAX Error');
                    return response.json();
                })
                .then(addedExpense => {
                    this.expenses.push(addedExpense);
                    const modal = bootstrap.Modal.getInstance(document.getElementById('addModal'));
                    modal.hide();
                })
                .catch(error => console.log('추가 오류:', error));
        },
        changePage(page) {
            this.pageNumber = page;
            this.fetchData();
        },
        fetchExpenseList() {
            fetch('http://localhost/expenselist')
                .then(response => response.json())
                .then(data => {
                    this.expenseList = data;
                })
                .catch(error => console.error('지출 항목 리스트를 가져오지 못했습니다.', error));
        }
    },
    mounted() {
        this.fetchData();
        this.fetchExpenseList();
    }
});

app.component('admin-header', HeaderComponent);
app.component('admin-sidebar', SidebarComponent);

app.mount('#expense');
