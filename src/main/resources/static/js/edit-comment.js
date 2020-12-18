const editCommentField = document.querySelector(".new-comment-field.w98");
const activePost = document.querySelector(".post.to-column.expanded");
editCommentField.focus();
activePost.scrollIntoView(false);
window.scrollBy(0, 150)