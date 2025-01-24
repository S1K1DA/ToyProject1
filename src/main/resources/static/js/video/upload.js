document.addEventListener("DOMContentLoaded", () => {
  const addChapterButton = document.getElementById("add-chapter-button");
  const chapterList = document.getElementById("chapter-list");

  // 챕터 추가 버튼 클릭 이벤트
  addChapterButton.addEventListener("click", () => {
    const newChapter = document.createElement("div");
    newChapter.classList.add("chapter");

    // 새로운 챕터 HTML 구조
    newChapter.innerHTML = `
      <div class="chapter-controls">
          <button class="delete-chapter-button">삭제</button>
      </div>
      <label for="chapter-title">챕터명</label>
      <input type="text" placeholder="챕터명을 입력하세요" />
      <label for="chapter-description">챕터소개</label>
      <textarea placeholder="챕터 내용을 입력하세요"></textarea>
      <label for="chapter-video">동영상 업로드</label>
      <input type="file" accept="video/*" />
    `;

    chapterList.appendChild(newChapter);

    // 새로 추가된 삭제 버튼 이벤트 추가
    const deleteButton = newChapter.querySelector(".delete-chapter-button");
    deleteButton.addEventListener("click", () => {
      chapterList.removeChild(newChapter);
    });
  });

  // 초기 챕터 삭제 버튼 이벤트 추가
  const initialDeleteButton = chapterList.querySelector(".delete-chapter-button");
  if (initialDeleteButton) {
    initialDeleteButton.addEventListener("click", () => {
      const initialChapter = initialDeleteButton.closest(".chapter");
      chapterList.removeChild(initialChapter);
    });
  }
});
