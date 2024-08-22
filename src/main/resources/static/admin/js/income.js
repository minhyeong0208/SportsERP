const app = Vue.createApp({
    data() {
        return {
            incomes: [],
            incomeList: [], 
            selectedRows: [], 
            selectAll: false, 
            startDate: '',
            endDate: '',
            searchQuery: '', 
            editingField: null, 
            editingRow: null, 
            newIncome: {
                income_item: '',
                income_purpose: '',
                income_amount: '',
                income_date: ''
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
            let url = `http://localhost/incomes?page=${this.pageNumber}&size=${this.pageSize}`;
            if (this.isFiltered && this.startDate && this.endDate) {
                url = `http://localhost/incomes/${this.startDate}/${this.endDate}?page=${this.pageNumber}&size=${this.pageSize}`;
            } else if (this.searchQuery) {
                url = `http://localhost/incomes/search?query=${this.searchQuery}&page=${this.pageNumber}&size=${this.pageSize}`;
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
        performSearch() {
            this.pageNumber = 0; 
            this.fetchData();
        },
        toggleSelectAll() {
            this.selectedRows = this.selectAll ? this.incomes.map(income => income.income_id) : [];
        },
        editField(rowIndex, field) {
            this.editingField = field;
            this.editingRow = rowIndex;
        },
        saveEdit(rowIndex) {
            const editedIncome = this.incomes[rowIndex];
            const url = `http://localhost/incomes/${editedIncome.income_id}`;
            fetch(url, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(editedIncome)
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
        deleteSelectedIncomes() {
            const confirmed = confirm("선택한 항목들을 삭제하시겠습니까?");
            if (confirmed) {
                this.selectedRows.forEach(id => {
                    const url = `http://localhost/incomes/${id}`;
                    fetch(url, { method: 'DELETE' })
                        .then(response => {
                            if (!response.ok) throw new Error('AJAX Error');
                            this.incomes = this.incomes.filter(income => income.income_id !== id);
                        })
                        .catch(error => console.log('삭제 오류:', error));
                });
                this.selectedRows = [];
            }
        },
        openAddModal() {
            this.newIncome = { income_item: '', income_purpose: '', income_amount: '', income_date: '' };
            const modal = new bootstrap.Modal(document.getElementById('addModal'));
            modal.show();
        },
        addIncome() {
            if (!this.newIncome.income_item || !this.newIncome.income_purpose || !this.newIncome.income_amount || !this.newIncome.income_date) {
                alert("모든 값을 입력하세요.");
                return;
            }
            const url = `http://localhost/incomes`;
            fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.newIncome)
            })
                .then(response => {
                    if (!response.ok) throw new Error('AJAX Error');
                    return response.json();
                })
                .then(addedIncome => {
                    this.incomes.push(addedIncome);
                    const modal = bootstrap.Modal.getInstance(document.getElementById('addModal'));
                    modal.hide();
                })
                .catch(error => console.log('추가 오류:', error));
        },
        changePage(page) {
            this.pageNumber = page;
            this.fetchData();
        },
        fetchIncomeList() {
            fetch('http://localhost/incomelist')
                .then(response => response.json())
                .then(data => {
                    this.incomeList = data;
                })
                .catch(error => console.error('수입 항목 리스트를 가져오지 못했습니다.', error));
        }
    },
    mounted() {
        this.fetchData();
        this.fetchIncomeList();
    }
});

app.component('admin-header', HeaderComponent);
app.component('admin-sidebar', SidebarComponent);

app.mount('#income');
