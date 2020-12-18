const newCommentField = document.querySelector(".new-comment-field");
const activePost = document.querySelector(".post.to-column.expanded");
newCommentField.focus();
activePost.scrollIntoView(false);
window.scrollBy(0, 150)