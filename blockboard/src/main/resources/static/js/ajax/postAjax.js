/**
 * @author  Woohyeok Jun <woohyeok.jun@worksmobile.com>
 * @file    postAjax.js
 */


function insertPost(boardID, postTitle, postContent) {
    $.ajax({
        type: 'POST',
        url: "/boards/" + boardID + "/posts",
        data: {
            postTitle: postTitle,
            postContent: postContent
        },
        error: function (xhr) {
            errorFunction(xhr);
        },
        success: function () {
            refreshPostList();
        }
    });
}

function loadPost(boardID, postID) {
    var post_title = $('#post_title');
    var editor = $('#editor');
    $.ajax({
        type: 'GET',
        url: "/boards/" + boardID + "/posts/" + postID + "/editor",
        async: false,
        error: function (xhr) {
            errorFunction(xhr);
        },
        success: function (data) {
            addPostIdToEditor(postID);
            post_title.val(data.postTitle);
            editor.val(data.postContent);
        }
    });
}

function updatePost(boardID, postID, postTitle, postContent) {
    $.ajax({
        type: 'PUT',
        url: "/boards/" + boardID + "/posts/" + postID,
        data: {
            postTitle: postTitle,
            postContent: postContent
        },
        error: function (xhr) {
            errorFunction(xhr);
        },
        success: function () {
            refreshPostList();
        }
    });
}

function deletePost(boardID, postID) {
    $.ajax({
        type: 'DELETE',
        url: "/boards/" + boardID + "/posts/" + postID,
        error: function (xhr) {
            errorFunction(xhr);
        },
        success: function () {
            refreshPostList();
        }
    });
}

// 게시글 작성, 수정, 삭제 시 해당 게시판 refresh 하는 함수
function refreshPostList() {
    var boardID = getCurrentBoardID();
    postClear();
    getPostsAfterTabClick(boardID);
}

function searchPost(option, keyword) {
    var boardID = getCurrentBoardID();
    $.ajax({
        type: 'GET',
        url: '/boards/' + boardID + '/posts/search',
        data: {
            option: option,
            keyword: keyword.val()
        },
        dataType: 'JSON',
        error: function (xhr) {
            errorFunction(xhr);
        },
        success: function (data) {
            postClear(); // 게시글 조회 화면 Clear
            postsClear(); // 게시글 목록 화면 Clear
            keyword.val("");

            loadPostList(data);
        }
    });
}

function addPostIdToEditor(postID) {
    var source = $('#postid-template').html();
    var template = Handlebars.compile(source);
    var IDitem = { postID: postID };
    var itemList = template(IDitem);
    console.log(" = " + JSON.stringify(IDitem));
    $('#editorcontent-hidden').html(itemList);
}