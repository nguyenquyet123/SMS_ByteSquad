app.controller("chuyenHangCtrl", function ($scope, $http) {

    $scope.username = document.getElementById('username').textContent;
    $scope.items = [];
    $scope.form = {};

    $scope.currentPage = 1;
    $scope.itemsPerPage = 2;
    $scope.totalItems;

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
        $scope.page = $scope.items.slice(begin, end);
    };

    $scope.init = () => {
        $http.get(`http://localhost:8080/api/orders/chuyenHang`).then(resp => {
            $scope.items = resp.data;
            console.log("items:", $scope.items);
            $scope.totalItems = $scope.items.length;
            $scope.pageChanged();
        }).catch(error => {
            console.log("ErrorPro: ", error)
        });
    }

    $scope.init();

    //Hiển thị table
    $scope.selectedRow = null;
    $scope.showDetails = function (row) {
        $scope.form = angular.copy(row);
        $http.get(`${host}/order-details/OrderId/${$scope.form.orderId}`).then(resp => {
            $scope.orderDetails = resp.data;
        }).catch(error => {
            console.log("Error: ", error.message);
        })
        if ($scope.selectedRow === row) {
            $scope.selectedRow = null;
        } else {
            $scope.selectedRow = row;
        }
    };
    //End
    $scope.update = () => {
        var item = angular.copy($scope.form);
        $http.get(`${host}/order-details/OrderId/${item.orderId}`).then(resp => {
            item.orderDetails = resp.data;
            $scope.orderDetails.forEach(detail => {
                var product;
                $http.get(`${host}/products/${detail.product.productId}`).then(resp => {
                    //Product Trong cửa Hàng
                    product = resp.data;
                    if (item.orderType === "Đơn Nhập") {
                        product.giaNhap = detail.import_price;
                        product.unitPrice = detail.price;
                        product.quantity = product.quantity + detail.quantity;
                    } else {
                        product.quantity = product.quantity - detail.quantity;
                    }
                    // Cập Nhật lại product
                    $http.put(`${host}/products/${product.productId}`, product).then(reps => {
                        item.orderStatus = 3;
                        $http.put(`${host}/orders/updateTotal/${item.orderId}`, item).then(reps => {
                            $scope.items.forEach((item) => {
                                if (item.orderId === $scope.form.orderId) {
                                  item.orderStatus = 3;
                                }
                            });
                            alert("Cập Nhật Thành Công");
                        }).catch(error => {
                            console.log("Error OrderDetail: ", error.data.message)
                        });
                    }).catch(error => {
                        console.log("Error: ", error.message);
                    });
                }).catch(error => {
                    alert("Cập Nhật Thất bại");
                    console.log("Error: ", error.message);
                });
            });
        }).catch(error => {
            console.log("Error: ", error.message);
        })
    }
});
