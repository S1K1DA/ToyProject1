// 로그인 폼 제출 이벤트 처리
const loginForm = document.getElementById('login-form');

loginForm.addEventListener('submit', async (event) => {
    event.preventDefault(); // 기본 폼 제출 동작 방지

    const formData = new FormData(loginForm);
    const userId = formData.get('userId');
    const password = formData.get('password');

    try {
        const response = await fetch('/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userId, password }),
        });

        if (response.ok) {
            alert('로그인 성공!');
            location.href = '/'; // 메인 페이지로 이동
        } else {
            const errorMsg = await response.text();
            alert(`로그인 실패: ${errorMsg}`);
        }
    } catch (error) {
        console.error('로그인 요청 중 오류 발생:', error);
        alert('로그인 요청 중 문제가 발생했습니다.');
    }
});

// 쿠키 읽기 함수
function getCookie(name) {
    const cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        const [key, value] = cookie.trim().split('=');
        if (key === name) {
            return value;
        }
    }
    return null;
}

// 로그인 상태 확인
document.addEventListener('DOMContentLoaded', () => {
    const userId = getCookie('userId');
    if (userId) {
        console.log(`로그인된 사용자: ${userId}`);
        document.getElementById('login-container').style.display = 'none'; // 로그인 폼 숨기기
    } else {
        console.log('로그인되지 않은 사용자');
    }
});
