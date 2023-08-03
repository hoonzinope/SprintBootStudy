(function() {
  $(document).ready(function() {
    search.init();
    searchResultTable.init();
  });
  let search = {
    init : function() {
      this.prevSearchTextSetting();
      this.searchButtonClick();
    },
    prevSearchTextSetting : function() {
      let searchText = new URL(location.href).searchParams.get("searchText");
      $(".searchTerm").val(searchText);
    },
    searchButtonClick : function() {
      $(".searchButton").off("click").on("click", function() {
        let references = $(".selectTerm option:selected").val();
        let searchText = $(".searchTerm").val();

        let data = {
          "references" : references,
          "searchText" : searchText
        }
        search.sendAlbumSearchData(data);
      });
      
      $(".searchTerm").on("keyup", function(key) {
        if(key.keyCode == 13) {
          $(".searchButton").click();
        }
      });
    },
    sendAlbumSearchData : function(data) {
      location.href = "search?references="+data.references+"&searchText="+data.searchText;
    }
  };

  let searchResultTable = {
    totalAlbumCount : 0,
    albums : null,
    limit : 10,
    init : function() {
      console.log(searchResult);
      // this.totalAlbumCount = searchResult.albums.total;
      // this.albums = searchResult.albums.items;
      // this.drawList();
    },
    drawList : function() {
      $("#album_list").empty();
      for(let album of this.albums){
        let section = this.drawRow(album);
        $("#album_list").append(section);
      }
      this.drawPagination();
    },
    drawRow : function(album) {
      let type = album.type;
      let name = album.name;
      let imageUrl = album.images[1].url;
      let totalTracks = album.total_tracks;
      let albumId = album.id;
      let artists = album.artists;
      let names = "";
      artists.forEach((artist, index) => {
        if(index != artists.length-1)
          names+= artist.name+",";
        else names += artist.name;
      });
      let releaseDate = album.release_date;

      let imgDiv = `<div class="left"><img src="${imageUrl}" width="100%" height="100%"></div>`
      let infoDiv = `<div class="right">
                      <ul class="info">
                        <li>name : ${name}</li>
                        <li>musician : ${names}</li>
                        <li>releaseDate : ${releaseDate}</li>
                        <li>totalTracks : ${totalTracks}</li>
                        <li><input type="button" value="critic"/></li>
                      </ul>
                    </div>`
      let section = `<section id=${albumId} type=${type} class="row">
                      ${imgDiv}
                      ${infoDiv}
                    </section>`;
      return section;
    },
    drawPagination : function() {
      let offset = new URL(location.href).searchParams.get("offset");
      if(offset == null) { offset = 0; }
      else { offset = Number(offset) / this.limit; }
      let page = offset + 1;
      let visiblePages = this.limit;
      let totalPages = this.totalAlbumCount % this.limit == 0 ?
      this.totalAlbumCount / this.limit : Math.floor(this.totalAlbumCount / this.limit)+1;
      if ($("#pagination_div").length != 0) {
          $("#pagination_div").remove();
          $("#album_list").before(
              '<div id="pagination_div" ></div>'
          );
          $('#pagination_div').twbsPagination({
              totalPages: totalPages,	// 총 페이지 번호 수
              visiblePages: 0,	// 하단에서 한번에 보여지는 페이지 번호 수
              startPage : page, // 시작시 표시되는 현재 페이지
              initiateStartPageClick: false,	// 플러그인이 시작시 페이지 버튼 클릭 여부 (default : true)
              first : false, //"첫 페이지",	// 페이지네이션 버튼중 처음으로 돌아가는 버튼에 쓰여 있는 텍스트
              prev : "prev",	// 이전 페이지 버튼에 쓰여있는 텍스트
              next : "next",	// 다음 페이지 버튼에 쓰여있는 텍스트
              last : false,//"마지막 페이지",	// 페이지네이션 버튼중 마지막으로 가는 버튼에 쓰여있는 텍스트
              nextClass : "page-item next",	// 이전 페이지 CSS class
              prevClass : "page-item prev",	// 다음 페이지 CSS class
              // firstClass : "page-item first",	// 첫 페이지 CSS class
              // lastClass : "page-item last",	// 마지막 페이지 CSS calss
              pageClass : "page-item",	// 페이지 버튼의 CSS class
              activeClass : "active",	// 클릭된 페이지 버튼의 CSS class
              disabledClass : "disabled",	// 클릭 안된 페이지 버튼의 CSS class
              anchorClass : "page-link",	//버튼 안의 앵커에 대한 CSS class
              onPageClick: function (event, page) {
                let searchParams = new URL(location.href).searchParams;
                let references = searchParams.get("references");
                let searchText = searchParams.get("searchText");
                let limit = Number(searchResultTable.limit);
                let offset = (page-1) * limit;
                location.href = "search?references="+references+"&searchText="+searchText+"&limit="+limit+"&offset="+offset;
              }
          });
      }
    }
  }
})();