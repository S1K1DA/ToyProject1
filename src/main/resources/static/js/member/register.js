// 기본 회원가입 정보 (아이디 중복 검사용)
const existingUsers = ['testuser', 'admin', 'sampleuser'];
let isUsernameAvailable = false;

// DOM 요소
const signupForm = document.getElementById('signup-form');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirm-password');
const nameInput = document.getElementById('name');
const usernameError = document.getElementById('username-error');
const usernameAvailable = document.getElementById('username-available');
const passwordError = document.getElementById('password-error');
const confirmPasswordError = document.getElementById('confirm-password-error');
const nameError = document.getElementById('name-error');

// 유효성 검사 함수
const validateUsername = (username) => /^[a-zA-Z0-9]{4,12}$/.test(username);
const validatePassword = (password) => /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/.test(password);
const validateName = (name) => name.trim() !== '';

// 아이디 중복 검사
const checkUsernameAvailability = () => {
    const username = usernameInput.value;
    if (!validateUsername(username)) {
        usernameError.textContent = '아이디는 영문과 숫자만 포함되어야 하며, 4~12자 사이여야 합니다.';
        usernameAvailable.textContent = '';
        isUsernameAvailable = false;
    } else if (existingUsers.includes(username)) {
        usernameError.textContent = '아이디가 이미 존재합니다.';
        usernameAvailable.textContent = '';
        isUsernameAvailable = false;
    } else {
        usernameError.textContent = '';
        usernameAvailable.textContent = '사용 가능한 아이디입니다.';
        isUsernameAvailable = true;
    }
};

// 비밀번호 확인
const checkPasswordMatch = () => {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;
    confirmPasswordError.textContent = password !== confirmPassword ? '비밀번호가 일치하지 않습니다.' : '';
};

// 회원가입 제출
const handleFormSubmit = (event) => {
    event.preventDefault();

    const username = usernameInput.value;
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;
    const name = nameInput.value;

    let isValid = true;

    // 아이디 유효성 검사
    if (!validateUsername(username)) {
        usernameError.textContent = '아이디는 영문과 숫자만 포함되어야 하며, 4~12자 사이여야 합니다.';
        isValid = false;
    } else if (!isUsernameAvailable) {
        alert('아이디 중복 확인을 해주세요.');
        isValid = false;
    }

    // 비밀번호 유효성 검사
    if (!validatePassword(password)) {
        passwordError.textContent = '비밀번호는 최소 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다.';
        isValid = false;
    }

    // 비밀번호 확인
    if (password !== confirmPassword) {
        confirmPasswordError.textContent = '비밀번호가 일치하지 않습니다.';
        isValid = false;
    }

    // 이름 유효성 검사
    if (!validateName(name)) {
        nameError.textContent = '이름을 입력해 주세요.';
        isValid = false;
    }

    if (isValid) {
        alert('회원가입이 완료되었습니다!');
        signupForm.reset();
        usernameAvailable.textContent = '';
        usernameError.textContent = '';
        location.href = '/login'; // 절대 경로로 수정
    }
};

// 이벤트 리스너 추가
document.getElementById('check-username').addEventListener('click', checkUsernameAvailability);
confirmPasswordInput.addEventListener('input', checkPasswordMatch);
signupForm.addEventListener('submit', handleFormSubmit);
