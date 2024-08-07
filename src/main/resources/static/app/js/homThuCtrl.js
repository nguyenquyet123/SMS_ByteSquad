app.controller("HomThuCtrl", function ($scope, $http) {
  // Khởi tạo biến để theo dõi số lượng email mới

  $scope.hasNewEmail = 0;
  $scope.items = [];
  $scope.item = {};
  $scope.searchText = "";
  $scope.selectedRow = null;

  $scope.load_all = () => {
    $http
      .get(`${host}/emails`)
      .then((resp) => {
        $scope.items = resp.data;
        $scope.totalItems = $scope.items.length;
        $scope.items.reverse();
        $scope.pageChanged();
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };
$scope.showDetails = function (row) {
  if($scope.selectedRow === row){
    $scope.selectedRow = null;
    $timeout(()=>{
      $scope.reloadPage();
     
    },0)
    return;
  }
  $scope.item = angular.copy(row);
  $scope.item.isRead = true;

  $scope.selectedRow = row;

  $http
  .put(`${host}/emails/${row.id}`,$scope.item)
  .then((resp)=>{
    $scope.items = $scope.items.map(item =>
      item.id === row.id ? {...item,isRead :true}:item
    );
    $scope.pageChanged();
    $scope.hasNewEmail -= 1;
  })
  .catch((error) =>{
    console.log(error)
  })

    
};


// function restoreState() {
//   const savedState = localStorage.getItem('pageState');
//   if (savedState) {
//       const state = JSON.parse(savedState);
//       if (state.selectedRowId) {
//           const selectedItem = $scope.pages.find(item => item.id === state.selectedRowId);
//           if (selectedItem) {
//               $scope.showDetails(selectedItem); // cais nay xoa cung dc anh

//           }
//       }
//   }
// }

// $scope.reloadPage = function () {
//   // window.location.reload(); // Tải lại trang
// };
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


  $scope.search = (text) => {
    $scope.message = "";
    // Chuyển chuỗi tìm kiếm và tên item về chữ thường để tìm kiếm không phân biệt hoa thường
    var foundItems = $scope.items.filter((item) => {
      return item.sender.toLowerCase().includes(text.toLowerCase());
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

  // tìm kiếm
  // $scope.searchText;
  // $scope.search = (text) => {
  //   $scope.message = ""; // Xóa thông báo lỗi trước đó
  //   // Lọc danh sách dựa trên văn bản tìm kiếm
  //   $scope.items = $scope.items.filter((item) => {
  //     return item.sender.toLowerCase().includes(text.toLowerCase());
  //   });
  //   // Nếu không tìm thấy kết quả, hiển thị thông báo
  //   if ($scope.items.length === 0) {
  //     $scope.message = "No items found";
  //   }
  // };

    //---------Phân trang-Hiển thị
    $scope.currentPage = 1;
    $scope.itemsPerPage = 10;
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
  

  $scope.checkForNewEmails();

  $scope.load_all();


$scope.reloadPage = function (){
  window.location.reload();
};



  // // Hàm để kiểm tra xem có email mới hay không
  // $scope.checkForNewEmails = function () {
  //   $http
  //     .get(`${host}/emails/unread`)
  //     .then(function (response) {
  //       // Giả sử response.data chứa số lượng email mới
  //       $scope.hasNewEmail = response.data;
  //     })
  //     .catch(function (error) {
  //       console.error("Lỗi khi kiểm tra email mới:", error);
  //     });
  // };

  // //---------hiển thị form chi tiết
  // $scope.selectedRow = null;
  // $scope.showDetails = function (row) {
  //   $scope.item = angular.copy(row);
  //   $scope.item.isRead = true;
  //   if ($scope.selectedRow === row) {
  //     $scope.selectedRow = null;
  //   } else {
  //     $scope.selectedRow = row;
  //   }
  //   $http
  //     .put(`${host}/emails/${row.id}`, $scope.item)
  //     .then((resp) => {
  //       $timeout(function () {
  //         $scope.items.forEach((item) => {
  //           if (item.orderId === row.id) {
  //             item.isRead = true;
  //           }
  //         });
  //         $scope.hasNewEmail -= 1;
  //       });
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // };


  // $scope.checkForNewEmails();



  // //---------hiển thị form chi tiết
  // $scope.selectedRow = null;
  // $scope.showDetails = function (row) {
  //   $scope.item = angular.copy(row);
  //   if ($scope.selectedRow === row) {
  //     $scope.selectedRow = null;
  //   } else {
  //     $scope.selectedRow = row;
  //   }
  // };
});
