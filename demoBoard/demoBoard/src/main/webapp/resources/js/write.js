(function() {

  $(document).ready(function() {
    writePost.init();
  });

  const random = (length = 8) => {
    return Math.random().toString(16).substring(2, length);
  };

  let writePost = {
    init : function() {
      this.userNameSetting();
      this.post();
    },
    userNameSetting : function() {
      $("#user").val("익명"+random());
    },
    post : function() {
      $("#submit").off("click").on("click", function(){
        let data = {
          "title" : $("#title").val(),
          "content" : $("#content").val(),
          "user" : $("#user").val(),
        };
  
        $.ajax({
          method : "POST",
          url : "writePost",
          data : JSON.stringify(data),
          contentType: 'application/json',
          dataType : "json",
        });
      });
    }
  }

})();