document.addEventListener("DOMContentLoaded", () => {
  const addLectureButton = document.getElementById("add-lecture-button");
  const lectureSections = document.getElementById("lecture-sections");

  // 강의 섹션 추가 버튼 클릭 이벤트
  let lectureCount = 1; // 강의 번호 카운터
  addLectureButton.addEventListener("click", () => {
    lectureCount++;
    const newLectureSection = document.createElement("div");
    newLectureSection.classList.add("lecture-section");

    // 새로운 강의 섹션 HTML
    newLectureSection.innerHTML = `
      <h3>강의 ${lectureCount}</h3>
      <div class="input-section">
        <label for="lecture-title-${lectureCount}">강의명</label>
        <input type="text" id="lecture-title-${lectureCount}" placeholder="강의명을 입력하세요" />
      </div>
      <div class="input-section">
        <label for="chapter-title-${lectureCount}">챕터명</label>
        <input type="text" id="chapter-title-${lectureCount}" placeholder="챕터명을 입력하세요" />
      </div>
      <div class="video-upload-section">
        <label for="video-title-${lectureCount}">동영상명</label>
        <div class="video-upload-wrapper">
          <input type="text" id="video-title-${lectureCount}" placeholder="동영상명을 입력하세요" />
          <button class="upload-button">동영상 올리기</button>
        </div>
        <div class="uploaded-videos">
          <p>아직 업로드된 동영상이 없습니다.</p>
        </div>
      </div>
    `;

    lectureSections.appendChild(newLectureSection);

    // 새로 추가된 동영상 업로드 버튼에 이벤트 추가
    const uploadButton = newLectureSection.querySelector(".upload-button");
    const videoTitleInput = newLectureSection.querySelector(`#video-title-${lectureCount}`);
    const uploadedVideos = newLectureSection.querySelector(".uploaded-videos");

    uploadButton.addEventListener("click", () => {
      const videoTitle = videoTitleInput.value.trim();

      if (!videoTitle) {
        alert("동영상명을 입력하세요!");
        return;
      }

      const videoItem = document.createElement("div");
      videoItem.classList.add("video-item");

      videoItem.innerHTML = `
        <span>${videoTitle}</span>
        <button class="delete-button">삭제</button>
      `;

      videoItem.querySelector(".delete-button").addEventListener("click", () => {
        uploadedVideos.removeChild(videoItem);
        if (uploadedVideos.children.length === 0) {
          uploadedVideos.innerHTML = `<p>아직 업로드된 동영상이 없습니다.</p>`;
        }
      });

      if (uploadedVideos.children[0]?.tagName === "P") {
        uploadedVideos.innerHTML = ""; // 안내 메시지 제거
      }
      uploadedVideos.appendChild(videoItem);

      videoTitleInput.value = "";
    });
  });
});
