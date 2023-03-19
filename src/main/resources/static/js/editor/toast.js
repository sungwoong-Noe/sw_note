
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

    console.log(formData);
    console.log(editor);
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