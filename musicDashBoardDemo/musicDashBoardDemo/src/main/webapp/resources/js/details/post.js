(function() {
  $(document).ready(function() {
    posting.init();
  });

  let posting = {
    type : null,
    result : null,
    init : function() {
      this.result = resultInfo;
      this.type = new URL(location.href).searchParams.get("type");
      this.drawPost();
    },
    drawPost : function() {
      if(this.type == "artist"){
        this.drawArtist();
      }else if(this.type == "track") {
        this.drawTrack();
      }else if(this.type == "album") {
        this.drawAlbum();
      }
    },
    drawArtist : function() {
      let name = this.result.name;
      $("#name").text("name : "+name);

      let imgUrl = this.result.images.length > 0 ? this.result.images[0].url : "";
      $("#img").html(`<img src="${imgUrl}" width="300px" height="300px"/>`);

      let type = this.type;
      $("#type").text("type : "+type);

    },
    drawTrack : function() {

    },
    drawAlbum : function() {

    }
  }
})();