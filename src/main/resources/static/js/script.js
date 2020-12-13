

const nodesOfPosts = document.querySelectorAll(".post");
const posts = [];

// TRANSFORMS HTML DIVS OF "POST" INTO ARRAY OF PROPER JS OBJECTS
for (let i = 0; i < nodesOfPosts.length; i++) {
  const postPragraphNode =
    nodesOfPosts[i].firstElementChild.firstElementChild.nextElementSibling
      .firstElementChild.lastElementChild.firstElementChild;

  const contentSection =
    nodesOfPosts[i].firstElementChild.firstElementChild.nextElementSibling;
  const contentSectionFooter = contentSection.lastElementChild;
  const btShowComments = contentSectionFooter.lastElementChild.lastElementChild;

  const commentsSection = nodesOfPosts[i].lastElementChild;
  const postBodyFullText = postPragraphNode.innerText;
  const postBodySlicedText =
    postBodyFullText.length > 150
      ? postBodyFullText.slice(0, 150) + "..."
      : postBodyFullText;
  const postExpandButtonNode = postPragraphNode.nextElementSibling;

  posts.push({
    id: i,
    container: nodesOfPosts[i],
    isExpanded: nodesOfPosts[i].classList.contains("expanded") ? true : false,
    isCommentsShown: commentsSection.classList.contains("not-displayed")
      ? false
      : true,
    paragraph: postPragraphNode,
    fullText: postBodyFullText,
    slicedText: postBodySlicedText,
    btExpand: postExpandButtonNode,
    btShowComments: btShowComments,
    comments: commentsSection,
  });
}

console.log(posts);

const expandPost = (post) => {
  if (!post.isExpanded) {
    post.isExpanded = true;
    post.container.classList.add("expanded");
    post.paragraph.classList.add("columns");
    post.paragraph.innerText = post.fullText;
    post.btExpand.firstElementChild.innerText = "SUSKLEISTI"
  }
};

const collapsePost = (post) => {
  if (post.isExpanded) {
    post.isExpanded = false;
    post.container.classList.remove("expanded");
    post.paragraph.classList.remove("columns");
    post.paragraph.innerText = post.slicedText;
    post.btExpand.firstElementChild.innerText = "SKAITYTI DAUGIAU"
  }
};

const collapseAllPostsExcept = (activePost) => {
  posts.forEach((post) => {
    if (post !== activePost && post.isExpanded) {
      collapsePost(post);
      hideComments(post);
      swapDownIfOdd(post);
    }
  });
};

const showComments = (post) => {
  if (!post.isCommentsShown) {
    post.isCommentsShown = true;
    post.comments.classList.remove("not-displayed");
    post.comments.firstElementChild.firstElementChild.focus();
  }
};

const hideComments = (post) => {
  if (post.isCommentsShown) {
    post.isCommentsShown = false;
    post.comments.classList.add("not-displayed");
  }
};

// APPLIES BEHAVIORAL UI TO ALL POSTS AND THEIR BUTTONS
posts.forEach((post) => {
  collapsePost(post); // ENSURES ALL THE POSTS TO BE COLLAPSED ON LOAD
  post.btExpand.addEventListener("click", () => {
    if (post.isExpanded) {
      console.log("COLLAPSING POST NO. " + post.id);
      collapsePost(post);
      hideComments(post);
      swapDownIfOdd(post);
    } else {
      console.log("EXPANDING POST NO. " + post.id);
      collapseAllPostsExcept(post);
      expandPost(post);
      swapUpIfOdd(post);
      console.log("FOCUSING ON BUTTON");
      console.log(post.btExpand);
      post.btExpand.focus();
    }
  });

  hideComments(post); // ENSURES ALL THE COMMENTS TO BE HIDDEN ON LOAD
  post.btShowComments.addEventListener("click", () => {
    console.log("Comments clicked on post No. " + post.id);
    if (post.isCommentsShown) {
      console.log("HIDING COMMENTS FOR POST No. " + post.id);
      hideComments(post);
    } else {
      console.log("REVEALING COMMENTS FOR POST No. " + post.id);
      console.log("EXPANDING POST NO. " + post.id);
      collapseAllPostsExcept(post);
      expandPost(post);
      swapUpIfOdd(post);
      showComments(post);
    }
  });
});

// IN ORDER TO ALWAYS HAVE AN "UNIFORM VIEW", OF POST FEED
// EVERY SECOND POST MUST BE TEMPORARILY SWAPPED UP WITH PREVIOUS ONE UPON EXPAND
// AND SWAPPED BACK DOWN UPON COLLAPSE
const swapUpIfOdd = (post) => {
  if (post.id % 2 !== 0) {
    const page = post.container.parentNode;
    const oddPost = post.container;
    const previousEvenPost = post.container.previousElementSibling;
    page.insertBefore(oddPost, previousEvenPost);
    console.log(
      "post No. " + post.id + " was indexed odd, and therefore temporarily swapped up"
    );
  }
};

const swapDownIfOdd = (post) => {
  if (post.id % 2 !== 0) {
    const page = post.container.parentNode;
    const oddPost = post.container;
    const nextEvenPost = post.container.nextElementSibling;
    page.insertBefore(nextEvenPost, oddPost);
    console.log(
      "post No. " + post.id + "was indexed odd, and therefore swapped back down"
    );
  }
};
