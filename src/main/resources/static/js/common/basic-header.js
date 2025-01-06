document.addEventListener('DOMContentLoaded', () => {
    const dropdownToggle = document.querySelector('.dropdown-toggle');
    const dropdownMenu = document.querySelector('.dropdown-menu');

    // 로그인 상태 확인
    const cookies = document.cookie.split(';').reduce((acc, cookie) => {
        const [key, value] = cookie.trim().split('=');
        acc[key] = value;
        return acc;
    }, {});
    const userId = cookies['userId'];

    // 드롭다운 메뉴 동적 업데이트
    if (userId) {
        // 로그인 상태
        dropdownMenu.innerHTML = `
            <li><a href="/mypage">마이페이지</a></li>
            <li><a href="/logout" onclick="logout()">로그아웃</a></li>
        `;
    } else {
        // 비로그인 상태
        dropdownMenu.innerHTML = `
            <li><a href="/login">로그인</a></li>
            <li><a href="/register">회원가입</a></li>
        `;
    }

    // 드롭다운 메뉴 토글
    dropdownToggle.addEventListener('click', (e) => {
        e.stopPropagation(); // 클릭 이벤트 전파 방지
        dropdownMenu.classList.toggle('show');
    });

    // 드롭다운 메뉴 외부 클릭 시 닫기
    document.addEventListener('click', (e) => {
        if (!dropdownToggle.contains(e.target) && !dropdownMenu.contains(e.target)) {
            dropdownMenu.classList.remove('show');
        }
    });
});

// 로그아웃 처리
function logout() {
    fetch('/api/logout', {
        method: 'POST',
    })
        .then(response => {
            if (response.ok) {
                alert('로그아웃 성공');
                window.location.href = '/'; // 메인 페이지로 리다이렉트
            } else {
                alert('로그아웃 실패');
            }
        })
        .catch(error => {
            console.error('로그아웃 중 에러 발생:', error);
        });
}
