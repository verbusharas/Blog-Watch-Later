// const editCommentField = document.querySelector(".new-comment-field.w98");
// const activePost = document.querySelector(".post.to-column.expanded");
// editCommentField.focus();
// activePost.scrollIntoView(false);
// window.scrollBy(0, 150)


const titleField = document.querySelector("#tfTitle");
const imageUrlField = document.querySelector("#tfImageUrl");
const bodyField = document.querySelector("#tfBody");
const imagePreview = document.querySelector("#imagePreview");
const imageNotFoundUrl = imagePreview.src;
const remainingSymbols = document.querySelector("#remainingSymbols");
const errors = document.querySelector(".errors");

remainingSymbols.innerText = bodyField.value.length +"/950";
checkImage(imageUrlField.value, () => {
    imagePreview.src = imageUrlField.value;
    console.log('image found initially');
}, () => {
    imagePreview.src = imageNotFoundUrl;
    console.log('image not found initially');
})


if (errors.firstElementChild) {
    errors.firstElementChild.focus();
    window.scrollBy(0, 200)
} else {
    titleField.focus();
    window.scrollBy(0, 150)
}

bodyField.addEventListener("input", () => {
    remainingSymbols.innerText = bodyField.value.length +"/950";
})

imageUrlField.addEventListener("input", () => {
    checkImage(imageUrlField.value, () => {
        imagePreview.src = imageUrlField.value;
        console.log('image found');
    }, () => {
        imagePreview.src = imageNotFoundUrl;
        console.log('image not found');
    })
})


function checkImage(imageSrc, good, bad) {
    var img = new Image();
    img.onload = good;
    img.onerror = bad;
    img.src = imageSrc;
}


