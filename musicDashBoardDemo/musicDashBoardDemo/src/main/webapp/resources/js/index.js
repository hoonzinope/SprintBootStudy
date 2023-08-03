(function() {
  $(document).ready(function(){
    search.init();
  });

  let search = {
    init : function() {
      this.searchButtonClick();
    },
    searchButtonClick : function() {
      $(".searchButton").off("click").on("click", function() {
        let references = $(".selectTerm option:selected").val();
        let searchText = $(".searchTerm").val();

        let data = {
          "references" : references,
          "searchText" : searchText
        }
        search.sendSearchData(data);
      });
      
      $(".searchTerm").on("keyup", function(key) {
        if(key.keyCode == 13) {
          $(".searchButton").click();
        }
      });
    },
    sendSearchData : function(data) {
      location.href = "search?references="+data.references+"&searchText="+data.searchText;
    }
  };
})();