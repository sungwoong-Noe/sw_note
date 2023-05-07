"use strict";

document.querySelector('#parentCategory').addEventListener('change', (event) => {
    console.log('hello');
    console.log(event.target.value);

    // /categories/child/{parentId}

    const parentCategoryId = event.target.value;
    const $select_childCategory = document.querySelector('#childCategory');

    const xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {
        if (xhr.readyState === xhr.DONE) {
            if (xhr.status === 200 || xhr.status === 201) {

                let $childCategoryOptions = '<option value="">==선택==</option>';

                const childCategories = xhr.response;
                childCategories.forEach(child => {
                    $childCategoryOptions += `<option value="${child.id}">${child.categoryName}</option>`
                });

                removeAllChild($select_childCategory);

                $select_childCategory.insertAdjacentHTML("beforeend",$childCategoryOptions);

                console.log(xhr.response);
            } else {
                console.error(xhr.responseText);
            }
        }
    }
    xhr.open('GET', `/categories/child/${parentCategoryId}`, true);
    xhr.responseType = 'json';
    xhr.send();
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

            const articleSeq = xhr.responseText;

            alert('등록되었습니다.');
            location.href = `/article/${articleSeq}`;

            console.log('response', xhr.responseText);
        } else {
            console.log('response', xhr.responseText);
            // throw new Error("Regist Ajax Error");
        }
    };

    xhr.send(JSON.stringify(formData));
});



const viewer = toastui.Editor.factory({
    el: document.querySelector('#viewer'),
    viewer: true,
    height: '500px',
    initialValue: "content",
})


function removeAllChild(tag) {
    while (tag.hasChildNodes()) {
        tag.removeChild(tag.firstChild);
    }
}
