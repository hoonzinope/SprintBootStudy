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
    drawComments : function() {
      $("#commentList").empty();
      comments.sort(function(a,b) {
        if(a["REF"] == b["REF"]) {
          return a["REFORDER"] - b["REFORDER"];
        }
        return a["REF"] - b["REF"];
      });

      for(let comment of this.comments) {
        let REF = comment["REF"];
        let REFORDER = comment["REFORDER"];
        let REFLEVEL = comment["REFLEVEL"];
        let depth = " ".repeat(REFLEVEL);

        let user = comment.user;
        let content = comment.content;
        let createAt = comment.createAt.replace("T", " ");
        let content_dd = `<dd>${depth} ${user}: ${content} // ${createAt}</dd>`;
        // let createAt_dd = `<dd>${createAt}</dd>`;

        let button_dd = `<dd class="reply" ref="${REF}"><input class="replyWrite" type="button" value="대댓 작성"></dd>`
        let replyList = `<dd class="replyList"></dd>`;
        let div = `<div>`
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
        let reply_ref = $(reply).attr("ref");
        commentTable.allCancelExcept(reply_ref);
        $(reply).empty();
        
        let div = `<div>`
                  +`<h6>대댓글 등록</h6>
                  <dt>작성자</dt>
                  <dd><input id="replyUser"/></dd>
                  <dt>대댓글</dt>
                  <dd><input id="replyContent"/><input id="replyREF" value="${reply_ref}" hidden></dd>
                  <dd><input type="button" class="replySubmit" data_tab="${reply_ref}" value="대댓글등록"/>
                  <input type="button" class="cancelReply" data_tab="${reply_ref}" value="취소"/></dd>`
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
        let reply_ref = $(this).attr("data_tab");
        let replyUser = $("#replyUser").val();
        let replyContent = $("#replyContent").val();
        
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

        let REF_ = 0;//commentTable.comments.length;
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