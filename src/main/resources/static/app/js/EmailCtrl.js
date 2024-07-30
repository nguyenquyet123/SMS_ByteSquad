app.controller("EmailController", function ($scope, $http, $timeout) {
  // Khởi tạo biến để theo dõi số lượng email mới
  $scope.hasNewEmail = 0;

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
      .get(`${host}/emails`)
      .then((resp) => {
        $scope.items = resp.data;
        $scope.totalItems = $scope.items.length;
        $scope.pageChanged();
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  // Hàm để kiểm tra xem có email mới hay không
  $scope.checkForNewEmails = function () {
    $http
      .get(`${host}/emails/unread`)
      .then(function (response) {
        // Giả sử response.data chứa số lượng email mới
        $scope.hasNewEmail = response.data;
      })
      .catch(function (error) {
        console.error("Lỗi khi kiểm tra email mới:", error);
      });
  };

  //---------hiển thị form chi tiết
  $scope.selectedRow = null;
  $scope.showDetails = function (row) {
    $scope.item = angular.copy(row);
    $scope.item.isRead = true;
    if ($scope.selectedRow === row) {
      $scope.selectedRow = null;
    } else {
      $scope.selectedRow = row;
    }
    $http
      .put(`${host}/emails/${row.id}`, $scope.item)
      .then((resp) => {
        $scope.$apply(() => {
          $scope.items.forEach((item) => {
            if (item.orderId === row.id) {
              item.isRead = true;
            }
          });
          $scope.hasNewEmail -= 1;
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };
  //End

  setInterval($scope.checkForNewEmails, 60000); // Kiểm tra email mới mỗi phút

  $scope.checkForNewEmails();

  $scope.load_all();
});
