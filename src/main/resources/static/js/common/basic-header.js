document.addEventListener('DOMContentLoaded', () => {
    const dropdownToggle = document.querySelector('.dropdown-toggle');
    const dropdownMenu = document.querySelector('.dropdown-menu');

    // 드롭다운 메뉴 토글
    dropdownToggle.addEventListener('click', () => {
        dropdownMenu.classList.toggle('show');
    });

    // 드롭다운 메뉴 외부 클릭 시 닫기
    document.addEventListener('click', (e) => {
        if (!dropdownToggle.contains(e.target) && !dropdownMenu.contains(e.target)) {
            dropdownMenu.classList.remove('show');
        }
    });
});
