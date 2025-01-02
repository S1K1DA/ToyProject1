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
let isUsernameAvailable = false; // 아이디 사용 가능 여부

// 유효성 검사 함수
const validateUsername = (username) => /^[a-zA-Z0-9]{4,12}$/.test(username);
const validatePassword = (password) =>
  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/.test(password);
const validateName = (name) => name.trim() !== '';

// 아이디 중복 확인
const checkUsernameAvailability = async () => {
  const username = usernameInput.value;

  if (!validateUsername(username)) {
    usernameError.textContent = '아이디는 영문과 숫자만 포함되어야 하며, 4~12자 사이여야 합니다.';
    usernameAvailable.textContent = '';
    isUsernameAvailable = false;
    return;
  }

  try {
    // 서버로 중복 체크 요청
    const response = await fetch(`/api/check-username?userId=${username}`);
    if (response.ok) {
      const isAvailable = await response.json();
      if (isAvailable) {
        usernameError.textContent = '';
        usernameAvailable.textContent = '사용 가능한 아이디입니다.';
        isUsernameAvailable = true;
      } else {
        usernameError.textContent = '아이디가 이미 존재합니다.';
        usernameAvailable.textContent = '';
        isUsernameAvailable = false;
      }
    } else {
      console.error('서버 응답 오류:', response.status);
      usernameError.textContent = '아이디 중복 체크 중 문제가 발생했습니다.';
      usernameAvailable.textContent = '';
      isUsernameAvailable = false;
    }
  } catch (error) {
    console.error('아이디 중복 체크 오류:', error);
    usernameError.textContent = '아이디 중복 체크 중 문제가 발생했습니다.';
    usernameAvailable.textContent = '';
    isUsernameAvailable = false;
  }
};

// 비밀번호 확인
const checkPasswordMatch = () => {
  const password = passwordInput.value;
  const confirmPassword = confirmPasswordInput.value;
  confirmPasswordError.textContent =
    password !== confirmPassword ? '비밀번호가 일치하지 않습니다.' : '';
};

// 회원가입 제출 (Ajax 방식)
const handleFormSubmit = async (event) => {
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
    try {
      const response = await fetch('/api/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userId: username,
          password: password,
          confirmPassword: confirmPassword,
          userName: name,
        }),
      });

      if (response.ok) {
        alert('회원가입이 완료되었습니다!');
        location.href = '/login';
      } else {
        const errorMessage = await response.text();
        alert(`회원가입 실패: ${errorMessage}`);
      }
    } catch (error) {
      console.error('회원가입 중 에러 발생:', error);
      alert('회원가입 중 문제가 발생했습니다.');
    }
  }
};

// 이벤트 리스너 추가
document.getElementById('check-username').addEventListener('click', checkUsernameAvailability);
confirmPasswordInput.addEventListener('input', checkPasswordMatch);
signupForm.addEventListener('submit', handleFormSubmit);
