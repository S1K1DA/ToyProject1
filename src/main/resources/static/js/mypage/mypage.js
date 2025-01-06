// mypage.js

document.addEventListener('DOMContentLoaded', () => {
    const tabButtons = document.querySelectorAll('.tab-btn');
    const sections = document.querySelectorAll('.content-section');

    tabButtons.forEach((button) => {
        button.addEventListener('click', () => {
            // 모든 섹션 숨기기
            sections.forEach((section) => section.classList.remove('active'));

            // 클릭된 버튼의 타겟 섹션 표시
            const target = button.getAttribute('data-target');
            document.getElementById(target).classList.add('active');
        });
    });
});
