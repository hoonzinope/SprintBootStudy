(function() {
  $(document).ready(function() {
    postDetailSetting.init();
    commentTable.init();
    commentPost.init();
  });

  const random = (length = 8) => {
    return Math.random().toString(16).substring(2, length);
  };

  let postDetailSetting = {
    init : function() {
      this.postSetting();
    },
    postSetting : function() {
      $("#title").text(postDetail.title);
      $("#content").text(postDetail.content);
      $("#user").text(postDetail.user);
      $("#createAt").text(postDetail.createAt.replace("T"," "));
    },
  }

  let commentTable = {
    comments : null,
    init : function() {
      this.comments = comments;
      this.drawComments();
    },
    getComments : function() {
      let url = new URL(location.href);
      let postSeq = Number(url.searchParams.get("seq"));
      $.ajax({
        url : "getComments",
        method : "GET",
        contentType: 'application/json',
        dataType : "json",
        data : { "postSeq" : postSeq },
        success : function(data) {
          commentTable.comments = data;
          commentTable.drawComments();
        }
      })
    },
    orderComments : function(){
      this.comments.sort(function(a,b) {
        if(a["REF"] == b["REF"]) {
          if(a["REFORDER"] == b["REFORDER"]) {
            return a["parentSeq"] - b["parentSeq"];
          }
          return a["REFORDER"] - b["REFORDER"];
        }
        return a["REF"] - b["REF"];
      });

      let parents = [];
      this.comments.forEach((comment) => {
        if(comment.parentSeq == -1) parents.push(comment);
      });

      function dfs(orgComment, comments) {
        let result = [orgComment];
        for(let i = 0; i < comments.length; i++) {
          let comment = comments[i];
          if(orgComment.seq == comment.parentSeq) {
            // result.push(comment);
            let children = dfs(comment, comments.slice(i, comments.length));
            result = result.concat(children);
          }
        }
        return result;
      }

      let result = [];
      for(let comment of parents) {
        let children = dfs(comment, this.comments);
        result = result.concat(children);
      }
      this.comments = result;
    },
    drawComments : function() {
      $("#commentList").empty();
      // comments.sort(function(a,b) {
      //   if(a["REF"] == b["REF"]) {
      //     if(a["REFORDER"] == b["REFORDER"]) {
      //       return a["parentSeq"] - b["parentSeq"];
      //     }
      //     return a["REFORDER"] - b["REFORDER"];
      //   }
      //   return a["REF"] - b["REF"];
      // });
      this.orderComments();

      for(let comment of this.comments) {
        let comment_id = comment["seq"];
        let REF = comment["REF"];
        let REFORDER = comment["REFORDER"];
        let REFLEVEL = comment["REFLEVEL"];
        let depth = "&nbsp;&nbsp;&nbsp;&nbsp;".repeat(REFLEVEL);//↳
        
        let parentName = comment["parentName"];
        if(parentName!="") { parentName = "@"+parentName;}

        let user = comment.user;
        let content = comment.content;
        let createAt = comment.createAt.replace("T", " ");
        let content_dd = `<dd>${depth} ${parentName} ${content} // ${user} ${createAt}</dd>`;
        // let createAt_dd = `<dd>${createAt}</dd>`;

        let button_dd = `<dd class="reply" ref="${REF}" depth="${REFLEVEL}" parent_id=${comment_id}><input class="replyWrite" type="button" value="대댓 작성"></dd>`
        let replyList = `<dd class="replyList"></dd>`;
        let div = `<div id="${comment_id}">`
                  +content_dd
                  +button_dd
                  +replyList
                  +`</div>`;
        $("#commentList").append(div);  
        this.replyWrite();
      }
    },
    replyWrite : function() {
      $(".replyWrite").off("click").on("click", function() {
        let reply = $(this).parent();
        let parent_ref = $(reply).attr("ref");
        let parent_depth = $(reply).attr("depth");
        let parent_id = $(reply).attr("parent_id");
        commentTable.allCancelExcept(parent_ref);
        $(reply).empty();
        
        let div = `<div>`
                  +`<h6>대댓글 등록</h6>
                  <dt>작성자</dt>
                  <dd><input id="replyUser"/></dd>
                  <dt>대댓글</dt>
                  <dd><input id="replyContent"/><input id="replyREF" value="${parent_ref}" hidden></dd>
                  <dd><input type="button" class="replySubmit" parent_ref="${parent_ref}" parent_depth="${parent_depth}" parent_id = "${parent_id}"value="대댓글등록"/>
                  <input type="button" class="cancelReply" data_tab="${parent_ref}" value="취소"/></dd>`
                  +`</div>`;

        $(reply).append(div);

        $("#replyUser").val("익명"+random());

        commentTable.cancelReply();
        commentTable.replyPosting();
      });
    },
    cancelReply : function() {
      $(".cancelReply").off("click").on("click", function() {
        let ref = $(this).attr("data_tab");
        $(`.reply[ref="${ref}"]`).empty();
        let input = `<input class="replyWrite" type="button" value="대댓 작성">`;
        $(`.reply[ref="${ref}"]`).append(input);
        commentTable.replyWrite();
      });
    },
    allCancelExcept : function(reply_ref) {
      $(`.reply`).each(function(index, dd) {
        let ddObject = $(dd);
        let ref = ddObject.attr("reply_ref");
        if(ref == reply_ref){
          return;
        }
        else {
          $(ddObject).empty(); 
          let input = `<input class="replyWrite" type="button" value="대댓 작성">`;
          $(ddObject).append(input);
          commentTable.replyWrite();
        }
      });
    },
    replyPosting : function() {
      $(".replySubmit").off("click").on("click", function() {
        let url = new URL(location.href);
        let postSeq = Number(url.searchParams.get("seq"));

        let parent_ref = Number($(this).attr("parent_ref"));
        let parent_depth = Number($(this).attr("parent_depth"));
        let parent_id = Number($(this).attr("parent_id"));

        let replyUser = $("#replyUser").val();
        let replyContent = $("#replyContent").val();
        
        let data = {
          "ref" : parent_ref,
          "refOrder" : 0,
          "refLevel" : parent_depth+1,
          "user" : replyUser,
          "content" : replyContent,
          "parentSeq" : parent_id,
          "postSeq" : postSeq,
        }
        $.ajax({
          url : "commentInsert",
          method : "POST",
          contentType: 'application/json',
          // dataType : "json",
          data : JSON.stringify(data),
          success : function(data) {
            commentTable.getComments();
          }
        });
      });
    },
  }

  let commentPost = {
    init : function() {
      this.commentUserSetting();
      this.commentPosting();
    },
    commentUserSetting : function() {
      $("#commentUser").val("익명"+random());
    },
    commentPosting : function() {
      $("#commentSubmit").off("click").on("click", function() {
        let user = $("#commentUser").val();
        let content = $("#commentContent").val();

        let url = new URL(location.href);
        let postSeq = Number(url.searchParams.get("seq"));

        let REF_ = commentTable.comments.length;
        let REFORDER = 0;
        let REFLEVEL = 0;

        let data = {
          "user" : user,
          "content" : content,
          "postSeq" : postSeq,
          "ref" : Number(REF_),
          "refOrder" : Number(REFORDER),
          "refLevel" : Number(REFLEVEL)
        }
        $.ajax({
          url : "commentInsert",
          method : "POST",
          contentType: 'application/json',
          // dataType : "json",
          data : JSON.stringify(data),
          success : function(data) {
            location.reload();
          }
        });

      });
    },
  }
})();