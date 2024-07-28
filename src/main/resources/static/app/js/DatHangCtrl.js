app.controller("datHangCtrl", function ($scope, $http) {
  $scope.items = [];
  $scope.item = {};

  //---------Phân trang-Hiển thị
  $scope.currentPage = 1;
  $scope.itemsPerPage = 2;
  $scope.totalItems;
  $scope.pages = [];

  $scope.setPage = (pageNo) => {
    $scope.currentPage = pageNo;
    $scope.pageChanged();
    $scope.message = "";
  };

  $scope.pageChanged = () => {
    var begin = ($scope.currentPage - 1) * $scope.itemsPerPage;
    var end = begin + $scope.itemsPerPage;
    if ($scope.totalItems % 2 !== 0) {
      if ($scope.totalItems < end) {
        var begin = ($scope.currentPage - 1) * $scope.itemsPerPage - 1;
      }
      if ($scope.totalItems === end) {
        $scope.currentPage += 0.5;
      }
    }
    $scope.pages = $scope.items.slice(begin, end);
  };

  $scope.load_all = () => {
    $http
      .get(`${host}/orders`)
      .then((resp) => {
        $scope.items = resp.data;
        $scope.totalItems = $scope.items.length;
        $scope.pageChanged();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  //---------Cập nhập trạng thái hóa đơn

  $scope.update = (id) => {
    $scope.item.orderStatus = 2;
    var obj = angular.copy($scope.item);
    $http
      .put(`${host}/orders/${id}`, obj)
      .then((resp) => {
        $scope.items.forEach((item) => {
          if (item.orderId === id) {
            item.orderStatus = 2;
          }
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  //---------Tìm kiếm gần theo theo ký tự

  $scope.searchText = "";

  $scope.search = (text) => {
    $scope.message = "";
    // Chuyển chuỗi tìm kiếm và tên item về chữ thường để tìm kiếm không phân biệt hoa thường
    var foundItems = $scope.items.filter((item) => {
      return item.customerName.toLowerCase().includes(text.toLowerCase());
    });

    if (foundItems.length === 0) {
      $scope.pages = [];
      $scope.message = "No items found";
    } else {
      $scope.pages = foundItems;
      console.log(foundItems);
      console.log($scope.pages);
    }
  };

  //---------hiển thị form chi tiết
  $scope.selectedRow = null;
  $scope.showDetails = function (row) {
    console.log(row);
    $scope.item = angular.copy(row);
    if ($scope.selectedRow === row) {
      $scope.selectedRow = null;
    } else {
      $scope.selectedRow = row;
    }
  };
  //End

  $scope.load_all();
});
