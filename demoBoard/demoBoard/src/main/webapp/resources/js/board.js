(function() {
  $(document).ready(function() {
    postTable.init();
  });

  let postTable = {
    init : function() {
      this.drawTable();
    },
    drawTable : function() {
      this.drawBody();
    },
    drawBody : function() {
      let tbody = $("#post_table tbody");
      tbody.empty();
      for(let post of posts) {
        tbody.append(this.drawRow(post));
      }
    },
    drawRow : function(post) {
      let seq = post.seq;
      let title = post.title;
      let title_td = `<td><a href="postDetail?seq=${seq}">${title}</a></td>`;
      
      let createAt = post.createAt.replace("T"," ");
      let createAt_td = `<td>${createAt}</td>`;

      let user = post.user;
      let user_td = `<td>${user}</td>`;

      let tr = `<tr>`
                +title_td
                +user_td
                +createAt_td
                +`</tr>`;
      return tr;
    }
  }

})();