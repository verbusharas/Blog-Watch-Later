const newCommentField = document.querySelector(".new-comment-field");
const anonymousUserWarning = document.querySelector(".warning");
const activePost = document.querySelector(".post.to-column.expanded");
newCommentField ? newCommentField.focus() : anonymousUserWarning.focus();
activePost.scrollIntoView(false);
window.scrollBy(0, 150)

warning