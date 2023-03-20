"use strict";

const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    previewStyle: 'tab',
    height: '500px',
    initialValue: "내용을 입력해주세요"
});

document.querySelector('#btn_regist').addEventListener('click', () => {

    const formData = new FormData();

    if (formData.valid() !== null) {
        alert(formData.valid());
    }

    const xhr = new XMLHttpRequest();

    xhr.open("POST", "/article/regist", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            console.log('response', xhr.responseText);
        } else {
            console.log('response', xhr.responseText);
            // throw new Error("Regist Ajax Error");
        }
    };

    xhr.send(JSON.stringify(formData));
});

class FormData {

    constructor() {
        this.title = document.querySelector('#title').value;
        this.content = editor.getMarkdown();
    }

    valid() {
        if(!this.title) {
            return "제목을 입력해주세요";
        }

        if (!this.content) {
            return "내용을 입력해주세요";
        }

        return null;
    }
}

const viewer = toastui.Editor.factory({
    el: document.querySelector('#viewer'),
    viewer: true,
    height: '500px',
    initialValue: "content",
})