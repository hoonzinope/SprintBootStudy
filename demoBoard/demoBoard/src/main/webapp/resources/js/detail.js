(function() {
  $(document).ready(function() {
    postDetailSetting.init();
  });

  let postDetailSetting = {
    init : function() {
      this.postSetting();
    },
    postSetting : function() {
      console.log();
      $("#title").text(postDetail.title);
      $("#content").text(postDetail.content);
      $("#user").text(postDetail.user);
      $("#createAt").text(postDetail.createAt.replace("T"," "));
    },
  }
})();