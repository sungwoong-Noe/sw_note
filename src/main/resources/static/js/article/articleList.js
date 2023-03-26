"use strict";

class Pageable {

    constructor(pageable) {
        this.page = pageable.number;
        this.first = pageable.first;
        this.last = pageable.last;
    }

    nextPage()  {
        return this.page + 1;
    }

    update(pageable) {

        this.page = pageable.number;
        this.first = pageable.first;
        this.last = pageable.last;
    }

    addButtonCheck() {
        if(this.last) {
            document.querySelector("#btn_moreArticle").remove();
        }
    }
}